/**
  Procedure which removes expired refresh tokens from the database, it is scheduled to run every day at midnight.
 */
CREATE OR REPLACE PROCEDURE REMOVE_EXPIRED_TOKENS
AS
BEGIN
    DELETE FROM REFRESH_TOKENS
    WHERE EXPIRY_DATE < SYSTIMESTAMP;
END;
/

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            JOB_NAME => 'REMOVE_EXPIRED_TOKENS',
            JOB_TYPE => 'PLSQL_BLOCK',
            JOB_ACTION => 'BEGIN REMOVE_EXPIRED_TOKENS; END;',
            START_DATE => TRUNC(SYSDATE) + 1,
            REPEAT_INTERVAL => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0',
            ENABLED => TRUE,
            COMMENTS => 'Job to remove expired tokens every day at midnight'
    );
END;
/

/**
  Procedure which calculates total expenses of each group in given currency.
 */
CREATE OR REPLACE PROCEDURE CALCULATE_GROUP_EXPENSES(CURR_ID CURRENCIES.ID%TYPE)
AS
    CURSOR CR IS
        SELECT G.NAME, ROUND(SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(E.ID, CURR_ID)), 2) FROM EXPENSES E
        JOIN MEMBERSHIPS M ON (E.MEMBERSHIP_ID = M.ID)
        JOIN GROUPS G ON (M.GROUP_ID = G.ID)
        GROUP BY G.ID, G.NAME
        ORDER BY SUM(E.PRICE) DESC;
    V_NAME VARCHAR2(30);
    V_SUM NUMBER;
    V_CODE VARCHAR2(30);
BEGIN
    SELECT SYMBOL INTO V_CODE FROM CURRENCIES WHERE CURR_ID = ID;
    OPEN CR;
    LOOP
        FETCH CR INTO V_NAME, V_SUM;
        EXIT WHEN CR%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Group named ' || V_NAME || ' has total expenses equal to ' || V_SUM || ' ' || V_CODE);
    END LOOP;
    CLOSE CR;
END;
/
