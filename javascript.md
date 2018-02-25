### javascriptBase64Encoding
#### javascript encode
``` javascript
btoa(encodeURIComponent(name))
```
#### java decode
``` java
URLDecoder.decode(new String(org.apache.commons.codec.binary.Base64.decodeBase64(req.getParameter("name").getBytes())), "UTF-8");
```

### javascriptAESEncoding
#### javascript encode
``` javascript
<script src="/sys/js/outside/AesUtil.js"></script> 
<script src="/sys/js/outside/aes.js"></script> 
<script src="/sys/js/outside//pbkdf2.js"></script>
var keySize = 128;
var iterations = iterationCount = 10000;
var iv = "F27D5C9927726BCEFE7510B1BDD3D137";
var salt = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
var passPhrase = "passPhrase passPhrase aes encoding algorithm";

var aesUtil = new AesUtil(keySize, iterationCount)
var id = aesUtil.encrypt(salt, iv, passPhrase,  $("input[name=user_id]").val());
```
#### java decode
``` java
	private static final int KEY_SIZE = 128;
	private static final int ITERATION_COUNT = 10000;
	private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
	private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
	private static final String PASSPHRASE = "passPhrase passPhrase aes encoding algorithm";
	AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
	id = req.getParameter("user_id") != null ? util.decrypt(SALT, IV, PASSPHRASE, req.getParameter("user_id")) : "";
```
#### AesUtil
``` java

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.codec.binary.Base64;

public class AesUtil {
	private final int keySize;
	private final int iterationCount;
	private final Cipher cipher;

	public AesUtil(int keySize, int iterationCount) {
		this.keySize = keySize;
		this.iterationCount = iterationCount;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw fail(e);
		}
	}

	public String encrypt(String salt, String iv, String passphrase, String plaintext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
			return base64(encrypted);
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
			return new String(decrypted, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
		try {
			cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
			return cipher.doFinal(bytes);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw fail(e);
		}
	}

	private SecretKey generateKey(String salt, String passphrase) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
			SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return key;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw fail(e);
		}
	}

	public static String base64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}
	public static byte[] hex(String str) {
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException(e);
		}
	}

	private IllegalStateException fail(Exception e) {
		return new IllegalStateException(e);
	}

}
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
