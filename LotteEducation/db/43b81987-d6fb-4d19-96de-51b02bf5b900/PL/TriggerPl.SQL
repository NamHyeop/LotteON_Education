/**
  trigger : 촉발하다 == callback
  before, after 두 가지가 존재한다.

  delimiter $$
    create trigger 트리거명
        {before | after}        {insert | updater | delete}
        on 테이블명
        for each row
  delimiter ;
 */

-- 저장(검사) 테이블
create table deptUpdate(
  oldname varchar(30),
  newname varchar(30)
);

# 변경전 이름과 변경 후 이름을 deptUpdate에 저장
delimiter $$
create trigger trigger_test
    # 트리거를 통해 DB에서 Insert가 될때마다 캐치해서 아래 쿼리가 작동
    before update
    on departments
    for each row
begin
    insert into deptUpdate(oldname, newname) values (old.department_name, new.department_name);
end$$
delimiter ;

select * from deptUpdate;
update departments set department_name= '총괄본부' where department_id = 300;