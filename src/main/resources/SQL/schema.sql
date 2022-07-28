CREATE TABLE tbl_board(
    num long auto_increment,
    title varchar (50) not null,
    content varchar (500) not null,
    name varchar (30) not null,
    primary key(num)
);

INSERT INTO tbl_board( title, content, name) VALUES ('제목1', '내용1', '이름1');
INSERT INTO tbl_board( title, content, name) VALUES ('제목2', '내용2', '이름2');
INSERT INTO tbl_board( title, content, name) VALUES ('제목3', '내용3', '이름3');
INSERT INTO tbl_board( title, content, name) VALUES ('제목4', '내용4', '이름4');


SELECT count(num) AS cnt FROM tbl_board;

SELECT * FROM TBL_BOARD;