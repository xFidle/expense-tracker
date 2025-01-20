INSERT INTO archived_groups (id, name) VALUES (1, 'Old Roommates');
INSERT INTO archived_groups (id, name) VALUES (2, 'Previous Family');
INSERT INTO archived_groups (id, name) VALUES (3, 'Past Travel Buddies');
COMMIT;

--------------------------------------------------------

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

INSERT INTO methods_of_payment (name) VALUES ('card');
INSERT INTO methods_of_payment (name) VALUES ('cash');
INSERT INTO methods_of_payment (name) VALUES ('crypto');
COMMIT;

--------------------------------------------------------

INSERT INTO roles (name) VALUES ('member');
INSERT INTO roles (name) VALUES ('viewer');
INSERT INTO roles (name) VALUES ('admin');
INSERT INTO roles (name) VALUES ('editor');
COMMIT;

--------------------------------------------------------

-- User 1 (John Doe)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 1, 'English'); -- PLN, Card, English

-- User 2 (Jane Smith)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 2, 'English'); -- PLN, Cash, English

-- User 3 (Michael Brown)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (2, 3, 'English'); -- USD, Crypto, English

-- User 4 (Emily Jones)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 1, 'English'); -- PLN, Card, English

-- User 5 (Daniel Wilson)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 2, 'English'); -- PLN, Cash, English

-- User 6 (Sophia Taylor)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (2, 1, 'English'); -- USD, Card, English

-- User 7 (Liam Anderson)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 3, 'English'); -- PLN, Crypto, English

-- User 8 (Olivia Martin)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (3, 2, 'English'); -- EUR, Cash, English

-- User 9 (Noah Moore)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (3, 1, 'English'); -- EUR, Card, English

-- User 10 (Ava Thomas)
INSERT INTO preferences (currency_id, method_id, language)
VALUES (1, 2, 'English'); -- PLN, Cash, English

COMMIT;

--------------------------------------------------------



INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 1, 'john.doe@example.com', 'John', 'password123', 'Doe');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 2, 'jane.smith@example.com', 'Jane', 'securepass456', 'Smith');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 3, 'michael.brown@example.com', 'Michael', 'mypassword789', 'Brown');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-18', 'YYYY-MM-DD'), 4, 'emily.jones@example.com', 'Emily', 'emilypassword', 'Jones');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-19', 'YYYY-MM-DD'), 5, 'daniel.wilson@example.com', 'Daniel', 'danielpass123', 'Wilson');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-20', 'YYYY-MM-DD'), 6, 'sophia.taylor@example.com', 'Sophia', 'sophiasecure789', 'Taylor');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-21', 'YYYY-MM-DD'), 7, 'liam.anderson@example.com', 'Liam', 'liamspassword', 'Anderson');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-22', 'YYYY-MM-DD'), 8, 'olivia.martin@example.com', 'Olivia', 'oliviapass456', 'Martin');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-23', 'YYYY-MM-DD'), 9, 'noah.moore@example.com', 'Noah', 'noahsecure123', 'Moore');

INSERT INTO users (creation_date, preferences_id, email, name, password, surname)
VALUES (TO_DATE('2025-01-24', 'YYYY-MM-DD'), 10, 'ava.thomas@example.com', 'Ava', 'avapass789', 'Thomas');

COMMIT;

--------------------------------------------------------

-- John Doe: Old Roommates (member) and Previous Family (admin)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (1, 1, 1, 1);  -- Old Roommates (member)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (2, 2, 3, 1);  -- Previous Family (admin)

-- Jane Smith: Old Roommates (viewer) and Past Travel Buddies (member)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (1, 3, 2, 2);  -- Old Roommates (viewer)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (3, 4, 1, 2);  -- Past Travel Buddies (member)

-- Michael Brown: Previous Family (admin) and Past Travel Buddies (viewer)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (2, 5, 3, 3);  -- Previous Family (admin)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (3, 6, 2, 3);  -- Past Travel Buddies (viewer)

-- Emily Jones: Past Travel Buddies (editor) and Previous Family (member)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (3, 7, 2, 4);  -- Past Travel Buddies (editor)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (2, 8, 1, 4);  -- Previous Family (member)

-- Daniel Wilson: Old Roommates (member) and Past Travel Buddies (viewer)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (1, 9, 1, 5);  -- Old Roommates (member)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (3, 10, 2, 5);  -- Past Travel Buddies (viewer)

-- Sophia Taylor: Previous Family (editor) and Old Roommates (admin)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (2, 11, 2, 6);  -- Previous Family (editor)
INSERT INTO archived_memberships (group_id, id, role_id, user_id) VALUES (1, 12, 3, 6);  -- Old Roommates (admin)

COMMIT;


--------------------------------------------------------

-- John Doe: Roommates (member) and Family (admin)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 1, 1, 1);  -- Roommates (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (2, 2, 3, 1);  -- Family (admin)

-- Jane Smith: Roommates (viewer) and Travel Buddies (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 3, 2, 2);  -- Roommates (viewer)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 4, 1, 2);  -- Travel Buddies (member)

-- Michael Brown: Project Team (admin) and Friends Gathering (viewer)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 5, 3, 3);  -- Project Team (admin)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (5, 6, 2, 3);  -- Friends Gathering (viewer)

-- Emily Jones: Travel Buddies (editor) and Friends Gathering (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 7, 2, 4);  -- Travel Buddies (editor)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (5, 8, 1, 4);  -- Friends Gathering (member)

-- Daniel Wilson: Roommates (member) and Family (viewer)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 9, 1, 5);  -- Roommates (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (2, 10, 2, 5);  -- Family (viewer)

-- Sophia Taylor: Project Team (editor) and Roommates (admin)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 11, 2, 6);  -- Project Team (editor)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 12, 3, 6);  -- Roommates (admin)

-- Liam Anderson: Travel Buddies (viewer) and Project Team (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 13, 2, 7);  -- Travel Buddies (viewer)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 14, 1, 7);  -- Project Team (member)

-- Olivia Martin: Roommates (member) and Family (admin)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 15, 1, 8);  -- Roommates (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (2, 16, 3, 8);  -- Family (admin)

-- Noah Moore: Friends Gathering (member) and Travel Buddies (admin)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (5, 17, 1, 9);  -- Friends Gathering (member)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (3, 18, 3, 9);  -- Travel Buddies (admin)

-- Ava Thomas: Project Team (viewer) and Roommates (editor)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (4, 19, 2, 10);  -- Project Team (viewer)
INSERT INTO memberships (group_id, id, role_id, user_id) VALUES (1, 20, 2, 10);  -- Roommates (editor)

COMMIT;


--------------------------------------------------------

-- Refresh Token for User 1 (John Doe)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 1, 'token_john_doe_1234567890');

-- Refresh Token for User 2 (Jane Smith)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 2, 'token_jane_smith_1234567890');

-- Refresh Token for User 3 (Michael Brown)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 3, 'token_michael_brown_1234567890');

-- Refresh Token for User 4 (Emily Jones)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 4, 'token_emily_jones_1234567890');

-- Refresh Token for User 5 (Daniel Wilson)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 5, 'token_daniel_wilson_1234567890');

-- Refresh Token for User 6 (Sophia Taylor)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 6, 'token_sophia_taylor_1234567890');

-- Refresh Token for User 7 (Liam Anderson)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 7, 'token_liam_anderson_1234567890');

-- Refresh Token for User 8 (Olivia Martin)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 8, 'token_olivia_martin_1234567890');

-- Refresh Token for User 9 (Noah Moore)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 9, 'token_noah_moore_1234567890');

-- Refresh Token for User 10 (Ava Thomas)
INSERT INTO refresh_tokens (expiry_date, user_id, token)
VALUES (TO_TIMESTAMP_TZ('2025-02-15 23:59:59 UTC', 'YYYY-MM-DD HH24:MI:SS TZR'), 10, 'token_ava_thomas_1234567890');

COMMIT;


--------------------------------------------------------

-- John Doe invites Jane Smith to join 'Roommates' as a 'member'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (1, 1, 1, 2);

-- Michael Brown invites Emily Jones to join 'Family' as an 'admin'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (2, 3, 3, 4);

-- Emily Jones invites Daniel Wilson to join 'Travel Buddies' as a 'viewer'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (3, 2, 4, 5);

-- Jane Smith invites Liam Anderson to join 'Roommates' as a 'member'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (1, 1, 2, 7);

-- Daniel Wilson invites Olivia Martin to join 'Family' as a 'member'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (2, 1, 5, 8);

-- Sophia Taylor invites Noah Moore to join 'Travel Buddies' as an 'admin'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (3, 3, 6, 9);

-- John Doe invites Ava Thomas to join 'Project Team' as a 'viewer'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (4, 2, 1, 10);

-- Michael Brown invites Sophia Taylor to join 'Friends Gathering' as a 'viewer'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (5, 2, 3, 6);

-- Olivia Martin invites Liam Anderson to join 'Roommates' as a 'viewer'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (1, 2, 8, 7);

-- Noah Moore invites Emily Jones to join 'Project Team' as an 'editor'
INSERT INTO temporary_memberships (group_id, role_id, sender_id, user_id) VALUES (4, 2, 9, 4);

COMMIT;

--------------------------------------------------------

SET DEFINE OFF;

-- Roommates Group (Group ID: 1)
-- Expense 1: Rent Payment
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 500.00, 1, 1, 1, 1, 'Rent Payment');

-- Expense 2: Utilities Payment
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 150.00, 2, 1, 1, 2, 'Utilities Payment');

-- Expense 3: Grocery Shopping
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 200.00, 3, 1, 1, 1, 'Grocery Shopping');

-- Family Group (Group ID: 2)
-- Expense 1: Family Dinner
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 100.00, 1, 1, 2, 2, 'Family Dinner');

-- Expense 2: Movie Tickets
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 80.00, 2, 1, 2, 1, 'Movie Tickets');

-- Expense 3: Shopping
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 150.00, 3, 1, 2, 3, 'Shopping');

-- Travel Buddies Group (Group ID: 3)
-- Expense 1: Hotel Stay
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 300.00, 1, 1, 3, 1, 'Hotel Stay');

-- Expense 2: Plane Tickets
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 400.00, 2, 1, 3, 2, 'Plane Tickets');

-- Expense 3: Dinner at Local Restaurant
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 120.00, 3, 1, 3, 1, 'Dinner at Local Restaurant');

-- Project Team Group (Group ID: 4)
-- Expense 1: Office Supplies
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 50.00, 1, 1, 4, 3, 'Office Supplies');

-- Expense 2: Conference Fees
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 250.00, 2, 1, 4, 1, 'Conference Fees');

-- Expense 3: Team Lunch
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 90.00, 3, 1, 4, 2, 'Team Lunch');

-- Friends Gathering Group (Group ID: 5)
-- Expense 1: Party Supplies
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-15', 'YYYY-MM-DD'), 60.00, 1, 1, 5, 1, 'Party Supplies');

-- Expense 2: Snacks & Drinks
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-16', 'YYYY-MM-DD'), 80.00, 2, 1, 5, 2, 'Snacks & Drinks');

-- Expense 3: Event Tickets
INSERT INTO expenses (expense_date, price, category_id, currency_id, membership_id, method_id, title)
VALUES (TO_DATE('2025-01-17', 'YYYY-MM-DD'), 150.00, 3, 1, 5, 3, 'Event Tickets');

-- Commit the changes
COMMIT;

--------------------------------------------------------

