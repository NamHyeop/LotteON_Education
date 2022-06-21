-- 1. 운동부 TABLE을 작성하라.

-- TEAM : 팀 아이디, 지역, 팀 명, 개설 날짜, 전화번호, 홈페이지
create table exercise_team
(
    team_id     int,
    team_region varchar(20),
    team_name   varchar(20),
    team_birth  date,
    team_phnum  long,
    team_URL    varchar(20),
    primary key (team_id)
);
drop table exercise_team;
-- PLAYER : 선수번호, 선수 명, 등록일, 포지션, 키, 팀 아이디
create table player_info(
    player_id int,
    player_name varchar(20),
    player_regis date,
    player_pos varchar(20),
    player_height long,
    team_id int,
    foreign key (team_id) references exercise_team(team_id),
    primary key (player_id)
);
drop table player_info;
delete from player_info;
# 3개의 TEAM을  등록합니다.
insert exercise_team values (1, "부산", "롯데", "2000-01-01", '123-4564-7890', "www.lotte.com");
insert exercise_team values (2, "구미", "LG", "2001-01-01", '123-4565-6697', "www.LG.com");
insert exercise_team values (3, "울산", "현대", "2002-01-01", '123-5555-6666', "www.Hyendai.com");
# 3 개의 TEAM에 선수를 3 ~ 9명을 등록(추가)합니다.
insert player_info values (1, "김진우", now(),"투수", 180, 1);
insert player_info values (2, "김주영", now(),"투수", 170, 1);
insert player_info values (3, "김우민", now(),"투수", 176, 1);
insert player_info values (4, "김수유", now(),"투수", 153, 1);
insert player_info values (5, "이지오", now(),"포수", 190, 1);
insert player_info values (6, "주오민", now(),"투수", 180, 1);
insert player_info values (7, "지우주", now(),"포수", 170, 1);
insert player_info values (8, "지호영", now(),"투수", 150, 1);
insert player_info values (9, "이수민", now(),"포수", 180, 1);

insert player_info values (10, "김호수", "2001-01-02","투수", 143, 2);
insert player_info values (11, "박명수", "2001-02-02","포수", 178, 2);
insert player_info values (12, "강백호", "2001-03-02","투수", 176, 2);
insert player_info values (13, "구지호", "2001-04-02","투수", 182, 2);
insert player_info values (14, "이호지", "2001-05-02","포수", 191, 2);
insert player_info values (15, "주민철", "2001-06-02","투수", 184, 2);
insert player_info values (16, "주아영", "2001-07-02","포수", 175, 2);
insert player_info values (17, "지영호", "2001-08-02","투수", 176, 2);
insert player_info values (18, "호영지", "2001-09-02","포수", 177, 2);

insert player_info values (19, "지오민", "2001-01-02","투수", 143, 3);
insert player_info values (20, "유석하", "2001-02-02","포수", 178, 3);
insert player_info values (21, "주영이", "2001-03-02","투수", 176, 3);
insert player_info values (22, "김원하", "2001-04-02","투수", 182, 3);
insert player_info values (23, "이주영", "2001-05-02","포수", 191, 3);
insert player_info values (24, "정지주", "2001-06-02","투수", 184, 3);
insert player_info values (25, "철주이", "2001-07-02","포수", 175, 3);
insert player_info values (26, "아오영", "2001-08-02","투수", 176, 3);
insert player_info values (27, "강주호", "2001-09-02","포수", 177, 3);
# 선수를 입력하면 그 선수의 팀 명과 전화번호, 홈페이지가 출력되도록 합니다.
select team_name, team_phnum, team_URL
from exercise_team e, player_info p
where e.team_id = p.team_id
and p.player_name = "지오민";

select *
from deft_emp_location_view
where employee_id = 103;
# JOIN 해 보도록 합니다.
select * from player_info p, exercise_team e where p.team_id = e.team_id;
# 팀명으로 팀원들이 출력되도록 합니다.
select p.player_name
from exercise_team e, player_info p
where e.team_id = p.team_id
  and e.team_name = "롯데";


