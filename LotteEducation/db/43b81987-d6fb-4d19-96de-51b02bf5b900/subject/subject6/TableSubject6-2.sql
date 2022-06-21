-- 2. 온라인 마켓 TABLE을 작성하라.
-- PRODUCT(상품) : 상품번호, 상품명, 상품가격, 상품설명
create table product_table
(
    product_id bigint AUTO_INCREMENT not null,
    product_name varchar(255),
    product_price  int,
    product_descript  varchar(255),
    primary key (product_id)
);
# drop table product_table;
-- CONSUMER(소비자) : 소비자 ID, 이름, 나이
create table consumer_table
(
    consumer_id     bigint Auto_Increment not null,
    consumer_name varchar(255),
    consumer_age  bigint,
    primary key (consumer_id)
);
drop table consumer_table;
-- CART(장바구니) : 장바구니 번호, 소비자 ID, 구입일, 상품번호, 수량
create table cart_table
(
    cart_id     bigint AUTO_INCREMENT not null,
    consumer_id bigint(255),
    orderday  date,
    product_id bigint,
    cart_num bigint,
    foreign key (consumer_id) references consumer_table(consumer_id),
    foreign key (product_id) references product_table(product_id),
    primary key (cart_id)
);

-- 상품 테이블에 상품을 등록합니다(개수는 5가지이상).
insert into product_table values (default, "홈런볼", 500, "과자 입니다");
insert into product_table values (default, "마우스", 500, "마우수 입니다");
insert into product_table values (default, "객체 지향의 사실과 오해", 10000, "책 입니다");
insert into product_table values (default, "노트북", 200000, "노트북 입니다");
insert into product_table values (default, "스마트폰", 2000000, "스마트폰 입니다");
-- 소비자를 등록합니다.
-- (10명이상)
insert into consumer_table values (default, "김주원", 25);
insert into consumer_table values (default, "김이하", 26);
insert into consumer_table values (default, "유지호", 27);
insert into consumer_table values (default, "이하늘", 28);
insert into consumer_table values (default, "이구름", 29);
insert into consumer_table values (default, "박수호", 12);
insert into consumer_table values (default, "지이서", 33);
insert into consumer_table values (default, "이말하", 45);
insert into consumer_table values (default, "이주호", 23);
insert into consumer_table values (default, "주호민", 12);
-- 소비자가 쇼핑한 상품을 추가합니다.
insert into cart_table values (6, 1, now(), 1, 10);
insert into cart_table values (7, 2, now(), 3, 5);
insert into cart_table values (8, 3, now(), 2, 1);
insert into cart_table values (9, 4, now(), 4, 7);
insert into cart_table values (10, 9, now(), 5, 7);
insert into cart_table values (11, 9, now(), 1, 7);
-- 쇼핑한 상품을 출력합니다.
select p.product_name from cart_table c, product_table p where c.product_id = p.product_id;
# 소비자와 기간을 입력하면, 그 기간동안 구입한 물품이 출력됩니다.
select  * from consumer_table consumer, cart_table cart where consumer.consumer_id = cart.consumer_id and consumer_name = "이주호" and date("2022-06-20") <= now();