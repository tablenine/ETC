### mysql 정렬 시  null 또는 공백이 먼저 나오는 문제
``` mysql
-- ex) dept_rank에 공백값이 있음
order by dept_name asc, dept_rank = '' ASC, dept_rank asc, name asc;
```

### mysql 시간대역 변경
``` mysql
set time_zone = 'Asia/Seoul';
```

### 날짜비교
#### 한달 전
``` mysql
date_add(now(), interval -1 month)
```

#### 내일
``` mysql
date_add(now(), interval 1 day)
```

#### 한시간 전
``` mysql
date_add(now(), interval -1 hour)
```

### varchar형의 숫자 값 정렬
``` mysql
order by filedName+0
```
