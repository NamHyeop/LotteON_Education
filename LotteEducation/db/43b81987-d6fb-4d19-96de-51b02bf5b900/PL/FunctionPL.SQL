/**
  function
  매개변수, return 값
  select 구문

  delimiter $$
  begin

  create function 함수명(매개변수 자료형) returns 리턴 자료형 -> return 자료형이 있다!
    select 쿼리처리
    return 값;
  end
  delimiter ;
 */

use mydb;
# 권한 부여 쿼리
set global log_bin_trust_function_creators = 1;
drop function if exists  func;
# delimiter $$
create function func(p_val double) returns double
begin
    declare v_val double;

    set v_val = p_val;
    set v_val = v_val + 1;
    return v_val;
end;
# delimiter ;
select func(11);

set @num = func(22);
select @num;

# 월급과 커미션을 합친 금액의 세금을 계산 15%
delimiter $$
create function tax(v_sal decimal(8,2), v_bonus decimal(2,2)) returns double
begin
    return ((v_sal + ifnull(v_bonus, 0) * v_sal) * 0.15);
end$$
delimiter ;

select first_name, salary + salary * ifnull(commission_pct, 0) as 실급여,
       salary * ifnull(commission_pct, 0) as 보너스,
       tax(salary, commission_pct) as 세금
from employees;

drop function getJobName;
# 사원명을 입력하면, 업무명을 취득할 수 있는 함수
delimiter $$
create function getJobName(v_empno int) returns varchar(35)
begin
    declare v_jobname varchar(35);

    select job_title into v_jobname
    from employees e, jobs j
    where e.job_id = j.job_id
        and employee_id = v_empno;
    return v_jobname;
end$$
delimiter ;

set @jobname = getJobName(103);
select @jobname;

select first_name, salary, getJobName(employee_id)
from employees;