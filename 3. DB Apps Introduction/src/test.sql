SELECT * from users;

use diablo;
SELECT u.first_name, u.last_name, COUNT(*)
FROM users AS u
         JOIN users_games AS ug
              ON u.id = ug.user_id
WHERE u.user_name = 'chewyway'
GROUP BY ug.user_id