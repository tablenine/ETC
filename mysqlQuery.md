#### mysql 정렬 시  null 또는 공백이 먼저 나오는 문제

``` mysql
-- ex) dept_rank에 공백값이 있음
order by dept_name asc, dept_rank = '' ASC, dept_rank asc, name asc;
```

#### mysql 시간대역 변경
``` mysql
set time_zone = 'Asia/Seoul';
```
