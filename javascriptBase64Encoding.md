#### javascript encode
``` javascript
btoa(encodeURIComponent(name))
```
#### java decode
``` java
URLDecoder.decode(new String(org.apache.commons.codec.binary.Base64.decodeBase64(req.getParameter("name").getBytes())), "UTF-8");
```
