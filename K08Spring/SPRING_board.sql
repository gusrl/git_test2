--������ �Խ��� �����

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

--������ ���� 
create sequence springboard_seq 
    increment by 1
    start with 1 
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
--������ �Խ��� ���� ������ �Է� (5~10) �� ���� 
insert into springboard values(springboard_seq.nextval, '��Ŭ', '�����Դϴ�. 1���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, 'Ʈ���̽�', '�����Դϴ�. 2���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '�ɽ�����', '�����Դϴ�. 3���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '��ź�ҳ��', '�����Դϴ�. 4���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '���ʿ�', '�����Դϴ�. 5���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '�����̰�', '�����Դϴ�. 6���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
insert into springboard values(springboard_seq.nextval, '�ҹ���', '�����Դϴ�. 7���Խù��Դϴ�.', '�����Դϴ�.', sysdate, 0, springboard_seq.nextval, 0, 0, '1234');
commit;


---------------------
--Ʈ����� ó���� ���� ���̺� ���� 

--Ƽ�� ������ ����ó�� ���̺� 

create table transaction_pay
(
    customerId varchar2(30),
    amount number
);

--Ƽ�� ������ ���Ű�� ���̺� 
create table transaction_ticket
(
    customerId varchar2(30),
    countNum number(4)
);

--�������� : Ƽ���� 4������� ���Ű��� ( 5 �� �̻� ���Ž� �������� ����� �����߻���)

alter table transaction_ticket
    add constraint ck_ticket_countnum 
        check (countNum <5 );
        
        insert into transaction_ticket values ('kosmo1',4);  --pass 
        insert into transaction_ticket values ('kosmo2',7);  --error üũ���� �� �ɸ��� 