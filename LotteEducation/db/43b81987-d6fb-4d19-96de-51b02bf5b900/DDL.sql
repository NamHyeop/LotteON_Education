show databases;
-- create database mydb;
use mydb;
select @@autocommit;

set autocommit = true;
set autocommit = false;

commit;
rollback;

/**
자료형
	java	 oracle 							mysql
    int  	Integer, NUmber(6) 		INT, DECIMAL(5), DECIMAL(5,2)
    double	NUMBER					DOUBLE
    String	VARCHAR2
	Date	DATE					DATE
*/

-- table 생성
/**
	Row(행), Column(열)
	create table(
    )
*/

-- Table 정보조회 (스키마가 mydb)
select *
from INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'mydb';

# ===============================================================
# varchar형 예제
create table tb_varchar
(
    col1 varchar(10),
    col2 varchar(20),
    col3 varchar(30)
);

insert into tb_varchar(col1, col2, col3)
values ('abc', 'abc', 'abc');
insert into tb_varchar(col1, col2, col3)
values ('가나다', '가나다', '가나다');

# ===============================================================
# 정수형 예제
create table tb_integer
(
    col1 int,
    col2 integer
);

insert into tb_integer(col1, col2)
values (123, 456);
insert into tb_integer(col1, col2)
values (123, 456);
insert into tb_integer(col1, col2)
values ('1234', 456);

select *
from tb_integer;

# ===============================================================
# double형 예제
create table tb_decimal
(
    col1 decimal,
    col2 decimal(5),
    col3 decimal(5, 2)
);

# 테이블에서 설정한 자료형 정보를 기반으로 데이터 변경현상이 (반올림 현상 발생)
insert into tb_decimal(col1, col2, col3)
values (1234.5678, 12345.123, 123.456);

select *
from tb_decimal;

# ===============================================================
# date 예제
create table tb_date
(
    col1 date,
    col2 date
);

insert into tb_date(col1, col2)
values (now(), DATE_SUB(now(), INTERVAL 1 DAY));

select now();

select *
from tb_date;

select date_format(col1, ' %y %m %d '), col2
from tb_date;
select date_format(col1, '%y'), date_format(col1, '%m')
from tb_date;
# ===============================================================
# timestamp 예제
create table board
(
    num   INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(10),
    ymd   TIMESTAMP default now(),
    PRIMARY KEY (num)
);

# default로 num 설정시 auto_increment로 인해 자동값 할당
insert into board
values (default, '타이거즈', default);
insert into board
values (default, '라이언즈', default);
insert into board
values (default, 'LG', default);
insert into board
values (default, 'KIA', default);
insert into board
values (default, 'KIA', str_to_date('2022 10 12 1 12 11','%Y %m %d %H %i %s'));
insert into board
values (default, 'KIA', now());
select *
from board;

select num, title, date_format(ymd, '%y-%m-%d')
from board;
select date_format(ymd, '%y %m %d %h %i %s')
from board;
# 년도를 대문자로 할 경우 두 자리가 더 붙어서 나오는것을 확인 가능
select date_format(ymd, '%Y %m %d %h %i %s')
from board;
