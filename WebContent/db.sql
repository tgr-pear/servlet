drop table board;

create table board (
	board_idx 	  number(4) primary key,
	board_name 	  varchar2(20),
	board_title   varchar2(100),
	board_content varchar2(1000),
	board_date 	  date default sysdate,
	board_hit 	  number(5) default 0
);

drop sequence board_seq;

create sequence board_seq;

insert into board( board_idx, board_name, board_title, board_content, board_date ) 
values ( board_seq.nextval, '흥부', '글 제목 1', '글 내용 1', sysdate );
insert into board( board_idx, board_name, board_title, board_content, board_date ) 
values ( board_seq.nextval, '놀부', '글 제목 2', '글 내용 2', sysdate );
insert into board( board_idx, board_name, board_title, board_content, board_date ) 
values ( board_seq.nextval, '제비', '글 제목 3', '글 내용 3', sysdate );

<!-------------------------------------------------------------------------------->

drop table reply;

create table reply (
	reply_idx 	    number(4) primary key,
	reply_name 	    varchar2(20),
	reply_content   varchar2(300),
	reply_date 	    date default sysdate,
	reply_board_idx number(4)	 
);

drop sequence reply_board_seq;

create sequence reply_board_seq;

insert into reply( reply_idx, reply_name, reply_content, reply_date, reply_board_idx ) 
values ( reply_board_seq.nextval, '엄마', '댓글 내용 1', sysdate, 1 );
insert into reply( reply_idx, reply_name, reply_content, reply_date, reply_board_idx ) 
values ( reply_board_seq.nextval, '아빠', '댓글 내용 2', sysdate, 2 );
insert into reply( reply_idx, reply_name, reply_content, reply_date, reply_board_idx ) 
values ( reply_board_seq.nextval, '동생', '댓글 내용 3', sysdate, 3 );

