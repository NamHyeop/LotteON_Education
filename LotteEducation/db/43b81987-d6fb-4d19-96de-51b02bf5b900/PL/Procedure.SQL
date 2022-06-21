use mydb;
/**
    PL => Procedural Language
    SQL => function

    SQL을 확장한 순차적 언어 -> Procedure(처리자), Function
                        , Trigger, Insert, Delete, Update, select, callback(자동호출)

 */

# autocommit 확인
select @@autocommit;

-- autocommit off
set autocommit = false;

# 가상테이블 dual을 사용하여 간단한 숫자 조회
select 1 from dual;

select 1; -- mysql만 가능

-- 변수
-- counter int;
set @counter = 1;
select @counter;

-- 문자열 저장
set @message = '문자열 변수 저장';
select @message;

-- 숫자 연산
set @height = 178.3 + 11;
select @height;

-- procedure
/**
    delimiter $$
    create procedure 프로시저명( IN 매개변수명 자료형, OUT 매개변수명 자료형)
    begin
        쿼리문 + 제어처리
    end$$
    delimiter ; // 문자 끝에 한 칸 띄어쓰기 필수
 */

-- procedure delete
drop procedure if exists  sp_hello_world;

DELIMITER $$
create procedure sp_hello_world()
begin
    declare message varchar (20);
    set message := ' hello PL ';
    select concat('message = ', message);
end$$
DELIMITER ;

drop procedure if exists  myproc;
create procedure myproc(in inum int, out onum int)
begin
    select inum;
    set onum = 333;
end;
-- procedure call(호출)
call sp_hello_world();
call myproc(123, @outnum);
select @outnum;

insert into departments(department_id, department_name, location_id) VALUES (300, '관리부', 1400);
# delimiter //
drop procedure if exists  add_dept;
create procedure add_dept(in deptno int, in deptname varchar(30), in deptloc int)
begin
    declare exit handler for 1365
    begin
        select  'insert fail';
    end;

    insert into departments(department_id, department_name, location_id) values(deptno, deptname, deptloc);
end;
# //

call add_dept(301, '관리부', 1400);
select *from departments;

# 사원ID를 활용하여 급여를 향상시키는 프로시저
delimiter //
create procedure updateSalary(in v_empno int)
begin
    declare exit handler for 1365
    begin
        select '수정에 실패했습니다';
    end;
    update employees set salary = salary * 1.3
    where employee_id = v_empno;
end;
//
delimiter ;

call updateSalary(150);
select * from employees;
rollback ;

-- 사원번호를 입력하면 이름, 입사일, 급여 정보를 select을 활용하여 변수에 담기
create procedure emp_info(in v_empno int)
begin
    declare emp_name varchar(20);
    declare emp_hiredate date;
    declare emp_sal decimal(8,2);

    select first_name, hire_date, salary into emp_name, emp_hiredate, emp_sal
    from employees
    where  employee_id = v_empno;

    select emp_name as 이름, emp_hiredate as 입사일, emp_sal as 월급;
end;

call emp_info(130);

-- 사원번호를 입력받고 그 사원의 급여에 1000을 더한 값으로 갱신(UPDATE)하라
-- 사원번호와 갱신된 급여를 출력하라
drop procedure if exists  emp_info_pratice;
create procedure emp_info_pratice(in v_empno int)
begin
    declare emp_sal decimal(8,2);

    select salary into emp_sal
    from employees
    where  employee_id = v_empno;

    update employees set salary = salary + 1000 where employee_id = v_empno;
    select employee_id, salary from employees where  employee_id = v_empno;
end;
call emp_info_pratice(130);


-- 제어문
-- if
select if(2 > 1, 'True', 'FALSE') as result;
select commission_pct, if(commission_pct is null, '없음', '있음') from employees;

-- 급여 상위레벨, 평균레벨 ,하위레벨
delimiter //
create procedure hiclass(in v_empno int)
begin
    declare emp_name varchar(20);
    declare emp_sal decimal(8, 2);

    select first_name, salary into emp_name, emp_sal
    from employees
    where employee_id = v_empno;
    if emp_sal >= 10000 then
        select "상위레벨: ", emp_sal;
    elseif emp_sal < 10000 and emp_sal > 600 then
        select "상위레벨: ", emp_sal;
    else
        select "하위벨:", emp_sal;
    end if;
end;
//
delimiter ;
call hiclass(100);
call hiclass(180);
call hiclass(200);


-- while,
drop table if exists testTable;
create table testTable(num int);

delimiter //
create procedure whileTest()
begin
    declare i int default 1; # int i  = 1;
    while(i <= 10) do
        insert into testTable(num)
            values (i);
            set i = i + 1;
        end while;
end;
//
delimiter ;

call whileTest();
select *from testTable;

-- loop
drop procedure if exists  loopTest;
drop table if exists testTable;
create table testTable(num int);
delimiter $$
create procedure loopTest()
begin
    declare x int;
    set x = 1;
    loop_label:Loop
        if x > 10 then
            leave loop_label;
        end if;

        insert into testTable(num) values(x);
        set x = x + 1;
    end Loop;
end;
delimiter ;
call loopTest();
select * from testTable;


delimiter //
create procedure test_cursor(in emp_name varchar(20))
begin
    declare cnt int default 0;
    declare v_first_name varchar(20);
    declare v_sal decimal(8, 2);

    declare endOfRow boolean default false;

    declare userCursor cursor for -- cursor 선언
        select first_name, salary from employees where first_name = emp_name;

    declare continue handler for not found set endOfRow = true;

    open userCursor;
    cloop: WHILE TRUE DO
        fetch  userCursor into v_first_name, v_sal;
        if endOfRow then
            leave cloop;
        end if;
        set cnt = cnt + 1;
        select concat(cnt, '번 ', v_first_name);
    end WHILE;
    close userCursor;

    update employees
    set salary = salary * 1.1
    where first_name = v_first_name;
end;
delimiter ;

call test_cursor('David');
rollback;

drop procedure if exists  expCursor_test;
-- 부서번호가 주어질 경우 해당 부서의 평균 월급을 커서로 구하기
delimiter //
create procedure expCursor_test(in v_deptno int)
begin
    declare v_dname varchar(30);
    declare emp_cnt double;
    declare sal_avg double;

    declare dept_avg cursor for
        select department_name, count(employee_id) cnt, round(avg(salary), 3) sal
        from employees e, departments d
        where e.department_id = d.department_id
            and e.department_id = v_deptno
        group by department_name;

    # 커서 사용하기
    open dept_avg;
        -- cursor fetch
        fetch dept_avg into v_dname, emp_cnt, sal_avg;
        select concat('부서명:', v_dname);
        select concat('사원수:', emp_cnt);
        select concat('평균월급:', sal_avg);
    close dept_avg;
end;
//
delimiter ;
call expCursor_test(60);

