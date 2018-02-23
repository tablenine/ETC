### web.xml에 추가
``` xml
  <filter>
    <filter-name>httpHeaderSecurity</filter-name>
    <filter-class>org.apache.catalina.filters.HttpHeaderSecurityFilter</filter-class>
    <init-param>
        <param-name>antiClickJackingOption</param-name>
        <param-value>SAMEORIGIN</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>httpHeaderSecurity</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  
  <filter>
    <filter-name>HstsFilter</filter-name>
    <filter-class>com.sqisoft.ssbr.webfm.util.HSTSFilter</filter-class>
  <init-param>
    <param-name>maxAgeSeconds</param-name>
    <param-value>31536000</param-value>
  </init-param>
  </filter>
  <filter-mapping>
    <filter-name>HstsFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  
  <filter>
    <filter-name>CSRF</filter-name>
    <filter-class>org.apache.catalina.filters.CsrfPreventionFilter</filter-class>
    <init-param>
      <param-name>entryPoints</param-name>
      <param-value>/,/main.com,/user/logout.com</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CSRF</filter-name>
    <url-pattern>*.sqi</url-pattern>
  </filter-mapping> 
```

### class 추가
``` java
package com.tablenine.test.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HSTSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) res;
		if (req.isSecure()) {
			resp.setHeader("Strict-Transport-Security", "max-age=31622400; includeSubDomains=preload");
			resp.setHeader("Pragma","no-cache");   
			resp.setDateHeader("Expires",0);
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
			StringBuilder sb = new StringBuilder();
			sb.append("default-src 'self' wss://*/broadcasting;");
			sb.append("style-src 'self' 'sha256-tozDPMeDZRxokCo69CLTmppUYXEWFnJPmGrO25bmuD4=' 'sha256-Gzbi98F9fcz664bXDXDPSQNUz89lP850U3l2L8wpZ/Q=' 'sha256-G/XOkwTRhI7DoKoUP9p3K+IeZKzIxWSLeFjhnGT6IAo=' 'sha256-aEEaHen7rv/Tk7GmNPnEXAHC3y+tFGlgYjCr2DF5/mo=' 'sha256-qforv3HE1Wi4mevChc3NvAmJE4/eWjvGiF1wAXy9XfY=' 'sha256-Fc+o/CwifmefP24M+tlvo5QOud6KJwnPyY+QrNHCpnE=' 'sha256-sheLuxvL+rfkKJcsrZfft1IAaDDd30O9si3mLODBm2M=' 'sha256-h0tEt1/wncTJoG8MAlw41oI5+rekq19Ysx5iysLJquU=' 'sha256-AbpHGcgLb+kRsJGnwFEktk7uzpZOCcBY74+YBdrKVGs=' 'sha256-6+xWl6duNLoRLnCA7Xhje94OyHwo9h+9lJikxLV58mk=' 'sha256-urV0m0+KGKhLhxdqu2GnW+cH3bFjkymfgEHs3KgT8DQ=' 'sha256-wnf5xzGsufIqay+iwXQ6yJCK/egsaQXb1n2DYEcsQeA=' 'sha256-/9sRJrn0qBX3F5If9+h8fwqErzk2dBtGGecOeD7G/2E=' 'sha256-G1SB6E1WP3gWm49keWS3PZlgstaoWes0OMYd3PFq1KA=' 'sha256-RLTqVs8C/wbnTan6Z4y7IF0srKjLXddboDtoZDrueKE=' 'sha256-u2yC0RA7605PpK9fVSSiLoaWIsNj/nB+aifU/Md2fNQ=' 'sha256-k/jkVhX4sSwonSW178hntaPCoQ2vTtC+W6cDrixNPpI=' 'sha256-OXeoiSV/Fbj/oDGxUN4Uq/AQ01xB+0av+JUva13Nr0A=' 'sha256-iqKMhP1SJ636ALUvx3XlLdUNDMAdkz36OkZb4d2rT08=' 'sha256-TrK/QdnlvMYmLtkwMmmSWJ8OG363VFPItAzLjKb9yEo=' 'sha256-OXeoiSV/Fbj/oDGxUN4Uq/AQ01xB+0av+JUva13Nr0A=' 'sha256-Hbzv4EsvIRJRsxWCvj18D7SLTKztQIy1CkG1CPmGYAM=' 'sha256-yvNW9mXSxJC5BUg9f7UxiNSI9mpj1LyFXYLm0vB7qiU=' 'sha256-ZdHxw9eWtnxUb3mk6tBS+gIiVUPE3pGM470keHPDFlE=' 'sha256-HlPVT8KyW0/RW4QEMuOpc7xgngHoWROEqUhreZ7UkJI=' 'sha256-+iwJc+g0gktaTY23VySZtrMYbpbIbTZe1bnojY6G8ys=' 'sha256-aqNNdDLnnrDOnTNdkJpYlAxKVJtLt9CtFLklmInuUAE=' 'sha256-aqNNdDLnnrDOnTNdkJpYlAxKVJtLt9CtFLklmInuUAE=' 'sha256-2j+NsrE/qRlmhkADxLdqK0AALIC4Gcc77SVRgwXmYCc=' 'sha256-tNmK6nuFFIGOC/atTZBG+81yFe1HnIQDfKVWxoc/iwk=' 'nonce-aaafdfdeefafawefasdfji';");
			sb.append("script-src 'self' 'sha256-yRXGVpbyVvq8E51Oh9CsNq0EX64ZujAWUvwaTx3RKBM=' 'sha256-URjGspEO8APH9eR+6J+0rh7AvOWYYSI+3WO4a28GEb4=' 'sha256-hkBRlr1KVUJhkHWlxHENjWKbO/dH/Y3v0El1RHlEmpY=' 'sha256-BQhxoopkSSmc9evPGaVO/Vtzi/iDrH54VOF7Lrd/3bA=' 'sha256-WhWH0nsJ6fyQtVUa2BN1+2Ofmkije5Ua0X0NMfIiDTM=' 'sha256-WxRtEgYQps2tvkA/7F0Z/29U2u7UDcSTkl7pyaQJYrc=' 'sha256-6unAEOfToL2VJL0l8s1JzYb3pUTYQUB0ECNFDNRZVVI=' 'sha256-j54swt9bHgCXbOPhJHyE/FkM31Zhm4ILI7cCWGiDukw=' 'nonce-aaafdfdeefafawefasdfji';");
			sb.append("worker-src 'self' blob:;");
			resp.setHeader("Content-Security-Policy", sb.toString());
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// do Nothing
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// do Nothing
	}
}
```
