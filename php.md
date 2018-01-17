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

### 파일다운로드
``` php
<?php
function mb_basename($path) { return end(explode('/',$path)); } 
function utf2euc($str) { return iconv("UTF-8","cp949//IGNORE", $str); }
function is_ie() {
	if(!isset($_SERVER['HTTP_USER_AGENT']))return false;
	if(strpos($_SERVER['HTTP_USER_AGENT'], 'MSIE') !== false) return true; // IE8
	if(strpos($_SERVER['HTTP_USER_AGENT'], 'Windows NT 6.1') !== false) return true; // IE11
	return false;
}

$filepath = './헬로_월드.txt';
$filesize = filesize($filepath);
$filename = mb_basename($filepath);
if( is_ie() ) $filename = utf2euc($filename);

header("Pragma: public");
header("Expires: 0");
header("Content-Type: application/octet-stream");
header("Content-Disposition: attachment; filename=\"$filename\"");
header("Content-Transfer-Encoding: binary");
header("Content-Length: $filesize");

readfile($filepath);
```
### 파일 존재 확인
```php
if(file_exists($filepath)) {
	echo "있음";
}
else {
	echo "없음";
}
```
