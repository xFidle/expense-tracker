-- list of users with their prefered currency and method of payment

SELECT
    u.name AS user_name,
    u.surname AS user_surname,
    mop.name AS preferred_payment_method,
    c.symbol AS preferred_currency
FROM
    users u
JOIN
    preferences p ON u.preferences_id = p.id
JOIN
    methods_of_payment mop ON p.method_id = mop.id
JOIN
    currencies c ON p.currency_id = c.id;


-- list of expenses with their category, method of payment and user who made the expense

SELECT
    e.title AS expense_title,
    e.price AS expense_price,
    c.name AS category_name,
    u.name || ' ' || u.surname AS user_full_name,
    mop.name AS method_of_payment
FROM
    expenses e
JOIN
    categories c ON e.category_id = c.id
JOIN
    memberships m ON e.membership_id = m.id
JOIN
    users u ON m.user_id = u.id
JOIN
    methods_of_payment mop ON e.method_id = mop.id;

-- list of the most active groups based on the number of members

SELECT
    g.name AS group_name,
    COUNT(m.id) AS member_count
FROM
    groups g
LEFT JOIN
    memberships m ON g.id = m.group_id
GROUP BY
    g.name
ORDER BY
    member_count DESC;

-- expenses devided by categories and users

SELECT
    u.name || ' ' || u.surname AS user_full_name,
    c.name AS category_name,
    SUM(e.price) AS total_spent
FROM
    expenses e
JOIN
    categories c ON e.category_id = c.id
JOIN
    memberships m ON e.membership_id = m.id
JOIN
    users u ON m.user_id = u.id
GROUP BY
    u.name, u.surname, c.name
ORDER BY
    total_spent DESC;

-- users without assigned preferences

SELECT
    u.name AS user_name,
    u.surname AS user_surname
FROM
    users u
LEFT JOIN
    preferences p ON u.preferences_id = p.id
WHERE
    p.id IS NULL;

-- average expenses per category

SELECT
    c.name AS category_name,
    AVG(e.price) AS average_expense
FROM
    expenses e
JOIN
    categories c ON e.category_id = c.id
GROUP BY
    c.name
ORDER BY
    average_expense DESC;

-- users who spent the most in a given group

SELECT
    u.name || ' ' || u.surname AS user_full_name,
    SUM(e.price) AS total_spent
FROM
    expenses e
JOIN
    memberships m ON e.membership_id = m.id
JOIN
    users u ON m.user_id = u.id
WHERE
    m.group_id = 1
GROUP BY
    u.name, u.surname;

-- users who do not belong to any group

SELECT
    u.name AS user_name,
    u.surname AS user_surname
FROM
    users u
LEFT JOIN
    memberships m ON u.id = m.user_id
WHERE
    m.id IS NULL;

-- all expenses made in a given currency

SELECT
    e.title AS expense_title,
    e.price AS expense_price,
    c.symbol AS currency_symbol
FROM
    expenses e
JOIN
    currencies c ON e.currency_id = c.id
WHERE
    c.symbol = 'PLN';

-- users with the biggest number of unique roles in groups

SELECT
    u.name || ' ' || u.surname AS user_full_name,
    COUNT(DISTINCT m.role_id) AS role_count
FROM
    users u
JOIN
    memberships m ON u.id = m.user_id
GROUP BY
    u.name, u.surname
ORDER BY
    role_count DESC;

-- expenses devided by users and groups

SELECT
    g.name AS group_name,
    u.name || ' ' || u.surname AS user_full_name,
    SUM(e.price) AS total_spent
FROM
    expenses e
JOIN
    memberships m ON e.membership_id = m.id
JOIN
    groups g ON m.group_id = g.id
JOIN
    users u ON m.user_id = u.id
GROUP BY
    g.name, u.name, u.surname
ORDER BY
    g.name, total_spent DESC;


-- users, who spent the most in every group, with their total expenses in USD

SELECT
    g.name AS group_name,
    u.name AS user_name,
    u.surname AS user_surname,
    SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 2)) AS total_spending_in_usd
FROM groups g
JOIN memberships m ON g.id = m.group_id
JOIN users u ON m.user_id = u.id
JOIN expenses e ON m.id = e.membership_id
GROUP BY g.name, u.name, u.surname
ORDER BY total_spending_in_usd DESC;

-- day, when expenses in all groups were the highest

SELECT
    e.expense_date,
    g.name AS group_name,
    SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 2)) AS total_spending_in_usd
FROM expenses e
JOIN memberships m ON e.membership_id = m.id
JOIN groups g ON m.group_id = g.id
GROUP BY e.expense_date, g.name
ORDER BY total_spending_in_usd DESC
FETCH FIRST 1 ROWS ONLY;

-- groups with the biggest number of members

SELECT
    g.name AS group_name,
    COUNT(m.user_id) AS total_members
FROM groups g
LEFT JOIN memberships m ON g.id = m.group_id
GROUP BY g.name
ORDER BY total_members DESC;

-- users, who have not made any expenses

SELECT
    g.name AS group_name,
    u.name AS user_name,
    u.surname AS user_surname
FROM groups g
JOIN memberships m ON g.id = m.group_id
JOIN users u ON m.user_id = u.id
LEFT JOIN expenses e ON m.id = e.membership_id
WHERE e.id IS NULL;

-- find groups, where the most money was spent in a given currency

SELECT
    g.name AS group_name,
    c.symbol AS currency_symbol,
    SUM(e.price) AS total_spending
FROM groups g
JOIN memberships m ON g.id = m.group_id
JOIN expenses e ON m.id = e.membership_id
JOIN currencies c ON e.currency_id = c.id
WHERE c.symbol = 'PLN'
GROUP BY g.name, c.symbol
ORDER BY total_spending DESC;

-- users, who are members of the group with the most money spent

SELECT
    u.name AS user_name,
    u.surname AS user_surname,
    g.name AS group_name,
    SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 1)) AS total_spending_in_usd
FROM groups g
JOIN memberships m ON g.id = m.group_id
JOIN users u ON m.user_id = u.id
JOIN expenses e ON m.id = e.membership_id
WHERE g.id = (
    SELECT g2.id
    FROM groups g2
    JOIN memberships m2 ON g2.id = m2.group_id
    JOIN expenses e2 ON m2.id = e2.membership_id
    GROUP BY g2.id
    ORDER BY SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e2.id, 1)) DESC
    FETCH FIRST 1 ROWS ONLY
)
GROUP BY u.name, u.surname, g.name
ORDER BY total_spending_in_usd DESC;

-- archived groups with the number of archived_memberships

SELECT
    ag.name AS archived_group_name,
    COUNT(am.id) AS archived_members_count
FROM archived_groups ag
LEFT JOIN archived_memberships am ON ag.id = am.group_id
GROUP BY ag.name
ORDER BY archived_members_count DESC;

-- users, who have spent the most in a group

SELECT
    g.id AS group_id,
    g.name AS group_name,
    MOST_SPENDING_USER_IN_GROUP(g.id) AS most_spending_user_id
FROM groups g;

-- date with the most spending in a group

SELECT
    g.id AS group_id,
    g.name AS group_name,
    GET_DATE_WITH_MOST_SPENDING(g.id) AS date_with_most_spending
FROM groups g;

-- users with the highest spendings in a group with their preferences

SELECT
    g.id AS group_id,
    g.name AS group_name,
    u.id AS user_id,
    u.name AS user_name,
    u.surname AS user_surname,
    c.symbol AS preferred_currency,
    mp.name AS preferred_payment_method
FROM groups g
JOIN users u ON u.id = MOST_SPENDING_USER_IN_GROUP(g.id)
LEFT JOIN preferences p ON u.preferences_id = p.id
LEFT JOIN currencies c ON p.currency_id = c.id
LEFT JOIN methods_of_payment mp ON p.method_id = mp.id;

-- comparison of expenses between groups based on the day with highest spendings

SELECT
    g.id AS group_id,
    g.name AS group_name,
    d.date_with_most_spending,
    (
        SELECT SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 1))
        FROM expenses e
        JOIN memberships m ON e.membership_id = m.id
        WHERE m.group_id = g.id
          AND e.expense_date = d.date_with_most_spending
    ) AS total_spending_on_date
FROM groups g
JOIN (
    SELECT
        g_inner.id AS group_id,
        GET_DATE_WITH_MOST_SPENDING(g_inner.id) AS date_with_most_spending
    FROM groups g_inner
) d ON g.id = d.group_id;

-- users with the highest spendings in a group with the sum of their expenses

SELECT
    g.name AS group_name,
    u.name AS user_name,
    u.surname AS user_surname,
    (
        SELECT SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 1))
        FROM expenses e
        JOIN memberships m ON e.membership_id = m.id
        WHERE m.user_id = MOST_SPENDING_USER_IN_GROUP(g.id)
          AND m.group_id = g.id
    ) AS total_spending
FROM groups g
JOIN users u ON u.id = MOST_SPENDING_USER_IN_GROUP(g.id);

-- order group by spendings in a day with the most spendings

SELECT
    g.name AS group_name,
    (
        SELECT SUM(GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 1))
        FROM expenses e
        JOIN memberships m ON e.membership_id = m.id
        WHERE m.group_id = g.id
          AND e.expense_date = GET_DATE_WITH_MOST_SPENDING(g.id)
    ) AS spending_on_best_day
FROM groups g
ORDER BY spending_on_best_day DESC;

-- find users who have made expenses in a day with the most spendings in a group

SELECT
    g.name AS group_name,
    u.name AS user_name,
    u.surname AS user_surname,
    e.expense_date,
    GET_EXPENSE_PRICE_IN_ANOTHER_CURR(e.id, 1) AS expense_in_usd
FROM groups g
JOIN memberships m ON g.id = m.group_id
JOIN users u ON m.user_id = u.id
JOIN expenses e ON m.id = e.membership_id
WHERE e.expense_date = GET_DATE_WITH_MOST_SPENDING(g.id)
ORDER BY g.name, e.expense_date;











