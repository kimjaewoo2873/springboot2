INSERT INTO article(title, content) VALUES('가가가가', '1111');
INSERT INTO article(title, content) VALUES('나나나나', '2222');
INSERT INTO article(title, content) VALUES('다다다다', '3333');
INSERT INTO article(title, content) VALUES('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글 고고고');

INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Choi', '쇼생크 탈출');

INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Choi', '초밥');

INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Choi', '독서');

INSERT INTO pizza(name, price) VALUES('페퍼로니 피자', '25900');
INSERT INTO pizza(name, price) VALUES('불고기 피자', '29900');
INSERT INTO pizza(name, price) VALUES('고구마 피자', '30900');
INSERT INTO pizza(name, price) VALUES('포테이토 피자', '27900');
INSERT INTO pizza(name, price) VALUES('치즈 피자', '23900');

INSERT INTO user_group(group_name) VALUES('A');
INSERT INTO user_group(group_name) VALUES('B');
INSERT INTO user_group(group_name) VALUES('D');
INSERT INTO user_group(group_name) VALUES('E');


INSERT INTO team(group_id, country, image_Url) VALUES(1, 'Portugal', '/images/portugal.png');
INSERT INTO team(group_id, country, image_Url) VALUES(1, 'Ghana', '/images/ghana.png');
INSERT INTO team(group_id, country, image_Url) VALUES(1, 'Uruguay', '/images/uruguay.png');
INSERT INTO team(group_id, country, image_Url) VALUES(1, 'Korea Republic', '/images/korea.png');