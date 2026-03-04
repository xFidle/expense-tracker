#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

CONTAINER_NAME="oracle-expense-db"
ORACLE_PASSWORD="password"
ORACLE_IMAGE="gvenzl/oracle-xe:slim"
SCHEMA_FILE="$SCRIPT_DIR/database/create_schema.sql"

start_oracle() {
    if docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        echo "Oracle container '${CONTAINER_NAME}' already exists."
        if ! docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
            echo "Starting existing container..."
            docker start "$CONTAINER_NAME"
        else
            echo "Container is already running."
        fi
    else
        echo "Creating new Oracle container '${CONTAINER_NAME}'..."
        docker run -d \
            --name "$CONTAINER_NAME" \
            -p 1521:1521 \
            -e ORACLE_PASSWORD="$ORACLE_PASSWORD" \
            "$ORACLE_IMAGE"

        echo -n "Waiting for Oracle to be ready..."
        until docker exec "$CONTAINER_NAME" healthcheck.sh >/dev/null 2>&1; do
            printf '.'
            sleep 3
        done
        echo -e "\nOracle is up."

        echo "Running schema script..."
        docker cp "$SCHEMA_FILE" "$CONTAINER_NAME":/tmp/create_schema.sql
        echo "EXIT;" | docker exec -i "$CONTAINER_NAME" sqlplus system/"$ORACLE_PASSWORD"@XEPDB1 @/tmp/create_schema.sql
        echo "Schema applied."
    fi
}

stop_oracle() {
    echo "Stopping Oracle container..."
    docker stop "$CONTAINER_NAME"
}

start_oracle

cd "$SCRIPT_DIR/ExpenseAPI" || exit
chmod +x ./mvnw
./mvnw clean spring-boot:run >>/dev/null &
BACKEND_PID=$!

echo -n "Waiting for backend server to start..."
until curl --output /dev/null --silent --head http://localhost:8080; do
    printf '.'
    sleep 1
done
echo -e "\nBackend server is up. Starting frontend..."

cd "$SCRIPT_DIR/frontend" || exit
chmod +x ./gradlew
./gradlew run

kill $BACKEND_PID
stop_oracle
