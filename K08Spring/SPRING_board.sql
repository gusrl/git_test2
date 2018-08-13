--스프링 게시판 만들기

create table springboard
(
    idx number primary key,
    name varchar2(30) not null,
    title varchar2(200) not null,
    contents varchar2(4000) not null,
    postdate date default sysdate,
    hits number default 0,
    bgroup number default 0,
    bstep number default 0 ,
    bindent number default 0,
    pass varchar2 (30)
);

--시퀀스 생성 
create sequence springboard_seq 
    increment by 1
    start with 1 
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
--스프링 게시판 더미 데이터 입력 (5~10) 개 정도 
insert into springboard values(springboard_seq.nextval, '핑클', '제목입니다. 1번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '트와이스', '제목입니다. 2번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '걸스데이', '제목입니다. 3번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '방탄소년단', '제목입니다. 4번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '워너원', '제목입니다. 5번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '오마이걸', '제목입니다. 6번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '소방차', '제목입니다. 7번게시물입니다.', '내용입니다.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
commit;


---------------------
--트랜잭션 처리를 위한 테이블 생성 

--티켓 구매후 결제처리 테이블 

create table transaction_pay
(
    customerId varchar2(30),
    amount number
);

--티켓 구매후 구매결과 테이블 
create table transaction_ticket
(
    customerId varchar2(30),
    countNum number(4)
);

--제약조건 : 티켓은 4장까지만 구매가능 ( 5 장 이상 구매시 제약조건 위배로 오류발생됨)

alter table transaction_ticket
    add constraint ck_ticket_countnum 
        check (countNum <5 );
        
        insert into transaction_ticket values ('kosmo1',4);  --pass 
        insert into transaction_ticket values ('kosmo2',7);  --error 체크조건 에 걸리지 