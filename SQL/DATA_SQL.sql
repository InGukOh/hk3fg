create database hk3fg;

drop database hk3fg;
use hk3fg;
create table board(
id bigint not null auto_increment,
writer varchar(20) not null,
title varchar(50) not null,
content varchar(1000) not null,
created_date timestamp not null default current_timestamp,
modified_date timestamp not null default current_timestamp,
primary key(id)
);

insert into board(writer, title, content, created_date, modified_date) values ("이름1","제목1","내용1", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름2","제목2","내용2", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름3","제목3","내용3", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름4","제목4","내용4", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름5","제목5","내용5", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름6","제목6","내용6", default, default);
insert into board(writer, title, content, created_date, modified_date) values ("이름7","제목7","내용7", default, default);


insert into board(writer, title, content, created_date, modified_date)(select writer, title, content, created_date, modified_date from board);
insert into board(writer, title, content,created_Date,modified_Date)(select writer, title, content,created_Date,modified_Date from board);
insert into board(writer, title, content,created_Date,modified_Date)(select writer, title, content,created_Date,modified_Date from board);

select * from board;
drop table board;
