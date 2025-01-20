INSERT INTO categories (name) VALUES ('Electronics');
INSERT INTO categories (name) VALUES ('Clothing');
INSERT INTO categories (name) VALUES ('Sports');
INSERT INTO categories (name) VALUES ('Furniture');
INSERT INTO categories (name) VALUES ('Books');
INSERT INTO categories (name) VALUES ('Laisure');
INSERT INTO categories (name) VALUES ('Food');
INSERT INTO categories (name) VALUES ('Healthcare');
INSERT INTO categories (name) VALUES ('Transport');
INSERT INTO categories (name) VALUES ('Garden');
COMMIT;

--------------------------------------------------------

INSERT INTO currencies (exchange_rate, symbol) VALUES (1.0, 'PLN'); -- Base currency
INSERT INTO currencies (exchange_rate, symbol) VALUES (4.0, 'USD'); -- 1 USD = 4 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (4.5, 'EUR'); -- 1 EUR = 4.5 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (5.2, 'GBP'); -- 1 GBP = 5.2 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (3.1, 'CAD'); -- 1 CAD = 3.1 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (0.035, 'JPY'); -- 1 JPY = 0.035 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (2.8, 'AUD'); -- 1 AUD = 2.8 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (4.3, 'CHF'); -- 1 CHF = 4.3 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (0.056, 'INR'); -- 1 INR = 0.056 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (0.5, 'HKD'); -- 1 HKD = 0.5 PLN
INSERT INTO currencies (exchange_rate, symbol) VALUES (3.4, 'SGD'); -- 1 SGD = 3.4 PLN
COMMIT;

--------------------------------------------------------

INSERT INTO groups (id, name) VALUES (1, 'Roommates');
INSERT INTO groups (id, name) VALUES (2, 'Family');
INSERT INTO groups (id, name) VALUES (3, 'Travel Buddies');
INSERT INTO groups (id, name) VALUES (4, 'Project Team');
INSERT INTO groups (id, name) VALUES (5, 'Friends Gathering');
COMMIT;

--------------------------------------------------------

-- John Doe is a 'Roommates' group member with a 'member' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 1, 1, 1);

-- Jane Smith is a 'Family' group member with a 'member' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (2, 2, 1, 2);

-- Michael Brown is a 'Travel Buddies' group member with an 'admin' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 3, 3, 3);

-- Emily Jones is a 'Project Team' group member with an 'editor' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 4, 2, 4);

-- Daniel Wilson is a 'Friends Gathering' group member with a 'viewer' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (5, 5, 2, 5);

-- Sophia Taylor is a 'Roommates' group member with an 'admin' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 6, 3, 6);

-- Liam Anderson is a 'Family' group member with a 'viewer' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (2, 7, 2, 7);

-- Olivia Martin is a 'Travel Buddies' group member with an 'editor' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 8, 2, 8);

-- Noah Moore is a 'Project Team' group member with a 'viewer' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 9, 1, 9);

-- Ava Thomas is a 'Friends Gathering' group member with a 'member' role
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (5, 10, 1, 10);

COMMIT;


--------------------------------------------------------

INSERT INTO methods_of_payment (name) VALUES ('card');
INSERT INTO methods_of_payment (name) VALUES ('cash');
INSERT INTO methods_of_payment (name) VALUES ('crypto');
COMMIT;

--------------------------------------------------------

INSERT INTO roles (name) VALUES ('member');
INSERT INTO roles (name) VALUES ('viewer');
INSERT INTO roles (name) VALUES ('admin');
COMMIT;

--------------------------------------------------------


INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 101, 'john.doe@example.com', 'John', 'password123', 'Doe');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 102, 'jane.smith@example.com', 'Jane', 'securepass456', 'Smith');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 103, 'michael.brown@example.com', 'Michael', 'mypassword789', 'Brown');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-18', 'YYYY-MM-DD'), 104, 'emily.jones@example.com', 'Emily', 'emilypassword', 'Jones');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-19', 'YYYY-MM-DD'), 105, 'daniel.wilson@example.com', 'Daniel', 'danielpass123', 'Wilson');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-20', 'YYYY-MM-DD'), 106, 'sophia.taylor@example.com', 'Sophia', 'sophiasecure789', 'Taylor');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-21', 'YYYY-MM-DD'), 107, 'liam.anderson@example.com', 'Liam', 'liamspassword', 'Anderson');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-22', 'YYYY-MM-DD'), 108, 'olivia.martin@example.com', 'Olivia', 'oliviapass456', 'Martin');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-23', 'YYYY-MM-DD'), 109, 'noah.moore@example.com', 'Noah', 'noahsecure123', 'Moore');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-24', 'YYYY-MM-DD'), 110, 'ava.thomas@example.com', 'Ava', 'avapass789', 'Thomas');

COMMIT;

