### on change 이벤트 함수 내 사용하는 경우
``` javascript
$("#select_id").on("change", function(){
	// value 값으로 선택
	$(this).val("1").prop("selected", true);
 
	// OR option 순서값으로 선택
	$(this).find("option:eq(0)").prop("selected", true);
});
```
### 외부 스크립트에서 select의 값을 변경하는 경우
``` javascript
// value 값으로 선택
$("#select_id").val("1").prop("selected", true);
 
// OR option 순서값으로 선택
$("#select_id option:eq(0)").prop("selected", true);
```
+ attr은 스크립트로 추가된 엘리먼트는 컨트롤 할 수 없습니다.
때문에 prop을 통해 컨트롤 하시는 것을 권장.

+ [출처](http://blog.freezner.com/archives/1139)
