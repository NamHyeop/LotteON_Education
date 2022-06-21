use mydb;
create or replace view ub_test01
as
select job_id, job_title, min_salary
from jobs;

insert into ub_test01(job_id, job_title, min_salary) values ('DEVELOPER', '개발자', '10000');
select * from jobs;

delete from jobs where job_id = "DEVELOPER";

create table emp
as
select employee_id, first_name, salary
from employees;

-- view를 생성
create or replace view ub_view(empno, ename, sal)
as
select employee_id, first_name, salary
from emp;

insert into ub_view(empno, ename, sal) values (300, '정수동', 11000);
select * from ub_view;
select * from emp;

# 조인을 여러번 할 필요없이 미리 3개의 테이블을 엮은 view를 사용하면 쉽게 조회가능
create or replace view deft_emp_location_view
as
select employee_id, first_name, d.department_id, department_name, l.city
from employees e, departments d, locations l
where e.department_id = d.department_id
    and d.location_id = l.location_id;

select *
from deft_emp_location_view
where employee_id = 103;