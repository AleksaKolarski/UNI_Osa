INSERT INTO users (user_name, user_username, user_password) VALUES ('USER1', 'user1', 'pass1');
INSERT INTO users (user_name, user_username, user_password) VALUES ('USER2', 'user2', 'pass2');
INSERT INTO users (user_name, user_username, user_password) VALUES ('USER3', 'user3', 'pass3');

INSERT INTO posts (post_title, post_description, latitude, longitude, post_date, post_likes, post_dislikes, user_id) VALUES ('post1', 'Dugacak opis post 1 ovo je neki dugacak opis bla bla bla ima nesto ima svaceg svasta nesto', 36.32, 21.64, '2013-01-01', 1, 1, 1);
INSERT INTO posts (post_title, post_description, latitude, longitude, post_date, post_likes, post_dislikes, user_id) VALUES ('post2', 'opis2', 12.34, 12.34, '2014-01-01', 2, 2, 1);
INSERT INTO posts (post_title, post_description, latitude, longitude, post_date, post_likes, post_dislikes, user_id) VALUES ('post3', 'opis3', 23.45, 23.45, '2015-01-01', 3, 3, 1);
INSERT INTO posts (post_title, post_description, latitude, longitude, post_date, post_likes, post_dislikes, user_id) VALUES ('post4', 'opis4', 13.67, 13.74, '2016-01-01', 4, 4, 3);
INSERT INTO posts (post_title, post_description, latitude, longitude, post_date, post_likes, post_dislikes, user_id) VALUES ('post5', 'opis5', 32.12, 11.32, '2017-01-01', 5, 5, 3);

INSERT INTO tags (tag_name) VALUES ('tag1');
INSERT INTO tags (tag_name) VALUES ('tag2');
INSERT INTO tags (tag_name) VALUES ('tag3');
INSERT INTO tags (tag_name) VALUES ('tag4');

INSERT INTO post_tags (post_id, tag_id) VALUES (1, 1);
INSERT INTO post_tags (post_id, tag_id) VALUES (1, 3);
INSERT INTO post_tags (post_id, tag_id) VALUES (1, 4);
INSERT INTO post_tags (post_id, tag_id) VALUES (2, 2);
INSERT INTO post_tags (post_id, tag_id) VALUES (3, 1);

INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar1', 'sadrzaj1', '2013-01-01', 1, 1, 1, 1);
INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar2', 'sadrzaj2 bla bla dugacki komentar svasta nesto svasta nesto svasta nesto svasta nesto bla bla nesto svasta', '2014-01-01', 2, 2, 1, 1);
INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar3', 'sadrzaj3', '2015-01-01', 2, 2, 1, 2);
INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar4', 'sadrzaj4', '2015-01-01', 3, 3, 1, 2);
INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar5', 'sadrzaj5', '2015-01-01', 4, 4, 1, 2);
INSERT INTO comments (comment_title, comment_description, comment_date, comment_likes, comment_dislikes, post_id, user_id) VALUES ('komentar6', 'sadrzaj6', '2016-01-01', 2, 2, 2, 3);

