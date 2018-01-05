### 같은 IP에서 Port만 다른 어플리케이션이 서로 로그인 할 경우 세션끊어지는 증상
+ JSESSIONID 라는 쿠키 이름의 충돌 때문이다. (IP가 같기때문)
+ 해결방법은 server.xml의 context에 다음을 추가
``` 
sessionCookieName="ADMIN_JSESSIONID"
```

### 세션cookiename 변경
``` xml
<Context docBase="" path="/" reloadable="false" sessionCookieName="WFM_INT">
```
