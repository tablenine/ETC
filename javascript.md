### javascriptBase64Encoding
#### javascript encode
``` javascript
btoa(encodeURIComponent(name))
```
#### java decode
``` java
URLDecoder.decode(new String(org.apache.commons.codec.binary.Base64.decodeBase64(req.getParameter("name").getBytes())), "UTF-8");
```

### javascript 문자형 숫자 덧셈
``` javascript
var a = "100"
var b = 5
a+b // "1005"
+a+b // 105
a-0+b // 105
```

### 배열 index삭제
``` javascript
var array = [0,1,2,3,4,5,6];
array.splice(2,1);
[0,1,3,4,5,6]
```
