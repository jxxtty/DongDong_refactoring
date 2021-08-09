// 사용자
create user dong IDENTIFIED by dong;
grant connect, resource to dong;

//member 테이블
create table member
(
userid varchar2(20) primary key,
passwd varchar2(20) not null,
userName varchar2(20) not null,

nickName varchar2(20) not null,
addr varchar2(500) not null,
phone varchar2(11) not null,
email1 varchar2(20) not null,
email2 varchar2(20) not null,

userImage varchar2(100) not null
);


// 게시글
CREATE SEQUENCE post_num;

CREATE TABLE post(
        pNum NUMBER(10) PRIMARY KEY,
        userid VARCHAR2(20) NOT NULL,
        addr VARCHAR2(10) NOT NULL,
        pCategory VARCHAR2(5) NOT NULL,
        pTitle VARCHAR2(100) NOT NULL,
        pContent VARCHAR2(800) NOT NULL,
        pPrice NUMBER(10) NOT NULL,
        pImage VARCHAR2(500) NOT NULL,
        pHit NUMBER(5) NOT NULL,
        pDate DATE DEFAULT SYSDATE,
        pStatus VARCHAR2(1) DEFAULT '0',
        pPull VARCHAR2(3) DEFAULT '3'
);
ALTER TABLE post
ADD CONSTRAINT post_userid_fk FOREIGN KEY(userid)
REFERENCES member(userid) ON DELETE CASCADE;

ALTER TABLE post ADD (pStatus VARCHAR2(1));
update post set pstatus=0;

//Favorite, Purchase, Sale

CREATE table Favorite(
    pnum number(10),
    userid varchar2(20),

    CONSTRAINT FAVORITE_PNUM_FK FOREIGN KEY (pnum) REFERENCES POST (pnum) ON DELETE CASCADE, 
    CONSTRAINT FAVORITE_USERID_FK FOREIGN KEY (userid) REFERENCES member (userid) ON DELETE CASCADE, 
    CONSTRAINT FAVORITE_PK Primary Key(pnum,userid)
);

CREATE table Purchase(
    pnum number(10),
    userid varchar2(20),

    CONSTRAINT PURCHASE_PNUM_FK FOREIGN KEY (pnum) REFERENCES POST (pnum) ON DELETE CASCADE, 
    CONSTRAINT PURCHASE_USERID_FK FOREIGN KEY (userid) REFERENCES member (userid) ON DELETE CASCADE, 
    CONSTRAINT PURCHASE_PK Primary Key(pnum,userid)
);

CREATE table Sale(
    pnum number(10),
    userid varchar2(20),

    CONSTRAINT SALE_PNUM_FK FOREIGN KEY (pnum) REFERENCES POST (pnum) ON DELETE CASCADE, 
    CONSTRAINT SALE_USERID_FK FOREIGN KEY (userid) REFERENCES member (userid) ON DELETE CASCADE,
    CONSTRAINT SALE_PK Primary Key(pnum,userid)
);


주문서
create table orderSheet
(
oNUM varchar2(20) primary key,
pNum NUMBER(10) not null,
sUserid varchar2(20) not null,
bUserid varchar2(20) not null,
oAddr varchar2(50) not null,
oPrice NUMBER(10) not null,
oMessage varchar(200) not null,
oDate DATE DEFAULT SYSDATE
);
ALTER TABLE orderSheet
ADD CONSTRAINT orderSheet_pNum_fk FOREIGN KEY(pNum)
REFERENCES post(pNum) ON DELETE CASCADE;

Create SEQUENCE ORDERSHEET_NUM;

알림
CREATE SEQUENCE alarm_num;

CREATE TABLE alarm(
        aNum NUMBER(10) PRIMARY KEY,
        sender VARCHAR2(20) NOT NULL,
        receiver VARCHAR2(10) NOT NULL,
        type VARCHAR2(5) NOT NULL,
        info VARCHAR2(10) NOT NULL,
        detail VARCHAR2(100) NOT NULL,
        isRead NUMBER(2) NOT NULL,
        aDate DATE NOT NULL
);

ALTER TABLE alarm
ADD CONSTRAINT alarm_receiver_fk FOREIGN KEY(receiver)
REFERENCES member(userid) ON DELETE CASCADE;

ALTER TABLE alarm
ADD CONSTRAINT alarm_sender_fk FOREIGN KEY(sender)
REFERENCES member(userid) ON DELETE CASCADE;

신고
CREATE SEQUENCE COMPLAINT_NUM;      -- complaint-coNum

CREATE TABLE complaint(
    coNum NUMBER(10) PRIMARY KEY,           -- 신고 번호 PK
    coType NUMBER(1) NOT NULL,              -- 신고 타입 / 1:회원. 2:게시글. 3:댓글
    coTarget VARCHAR2(20)NOT NULL,          -- 신고 타입에 따라 회원번호or게시글번호or댓글번호
    targetUserid VARCHAR2(20) NOT NULL,    -- 신고대상 userid
    targetTitle VARCHAR2(100),             -- 신고된 글의 제목 or 신고된 유저의 nickname
    targetContent VARCHAR2(800),        -- 신고된 글의 내용 or 신고된 댓글의 내용
    targetImage VARCHAR2(500),        -- 신고된 글의 이미지 or 신고된 유저의 프로필사진
    userid VARCHAR2(20) NOT NULL,           -- 신고한 유저 번호 FK
    coContent VARCHAR2(500) NOT NULL,       -- 신고 내용
    endContent varchar2(500),               -- 신고 처리 내용
    coYn VARCHAR2(1) DEFAULT 'n' NOT NULL,  -- 신고 처리 유무
    createDate DATE NOT NULL,               -- 신고 날짜
    endDate DATE,                           -- 신고 처리 날짜

    CONSTRAINT COMPLAINT_PNUM_FK FOREIGN KEY(userid) REFERENCES member on DELETE CASCADE
);

제재내역
CREATE TABLE sanction(
    saNum NUMBER(10) PRIMARY KEY,
    coNum NUMBER(10) NOT NULL,
    userid VARCHAR2(20) NOT NULL,
    saType number(1) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE,

    CONSTRAINT SANCTION_CONUM_FK FOREIGN KEY(coNum) REFERENCES complaint on DELETE CASCADE,
    CONSTRAINT SANCTION_USERID_FK FOREIGN KEY(userid) REFERENCES member on DELETE CASCADE
 );

채팅
create table chatRoom
(
chatId varchar(100) primary key,
bUserid varchar(20) not null,
sUserid varchar(20) not null,
chatHistory varchar(100) not null
);

댓글
Create SEQUENCE COMMENTS_NUM;

CREATE TABLE comments
(
    pNum number(10) NOT NULL, -- 게시글 번호 fk
    cNum number(10) NOT NULL, -- 댓글 번호, pk
    userid VARCHAR2(20) NOT NULL, -- 유저 id fk
    cContent VARCHAR2(300) NOT NULL, -- 댓글 내용
    createDate date NOT NULL, -- 댓글 등록 일자
    updateDate date DEFAULT NULL, -- 댓글 수정 일자
    -- 계층형 쿼리의 구현을 위한 컬럼
    parentNum number(10) default 0 NOT NULL, -- 부모의 댓글 번호
    cLevel number(10) DEFAULT 1 Not NULL, -- 댓글의 깊이

    constraint COMMENT_PNUM_FK foreign key(pNum) REFERENCES post on DELETE CASCADE,
    constraint COMMENT_USERID_FK foreign key(userid) references member on DELETE CASCADE,
    constraint COMMENT_PK primary key(cNum)
);