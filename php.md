### 날짜비교
``` php
$timenow = date("Y-m-d H:i:s"); 
$timetarget = "2017-03-15 00:00:00";

$str_now = strtotime($timenow);
$str_target = strtotime($timetarget);

if($str_now > $str_target) {
  echo "비교할 시간이 현재 시간보다 이전입니다.";

} elseif($str_now == $str_target) {
  echo "비교할 시간이 현재시간과 같습니다.";
} else {
  echo "비교할 시간이 현재시간보다 작습니다.";
}
```
출처:  [ywlee861009](http://marlboroyw.tistory.com/421)
