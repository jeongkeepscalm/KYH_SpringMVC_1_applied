package hello.springmvc_1_applied.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

  @RequestMapping("/headers")
  public String headers(HttpServletRequest request, HttpServletResponse response
                        , HttpMethod httpMethod
                        , Locale locale
                        , @RequestHeader MultiValueMap<String, String> headerMap
                        , @RequestHeader("host") String host
                        , @CookieValue(value = "myCookie", required = false) String cookie
                        ) {
    log.info("request={}", request);          // org.apache.catalina.connector.RequestFacade@14bede27
    log.info("response={}", response);        // org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleHttpServletResponse@403568ce
    log.info("httpMethod={}", httpMethod);    // GET
    log.info("locale={}", locale);            // ko_KR
    log.info("headerMap={}", headerMap);      // {host=[localhost:8080], connection=[keep-alive], sec-ch-ua=["Chromium";v="124", "Google Chrome";v="124", "Not-A.Brand";v="99"]... }
    log.info("header host={}", host);         // localhost:8080
    log.info("myCookie={}", cookie);          // null

    return "ok";

  }

  /**
   * @RequestHeader MultiValueMap<String, String> headerMap: 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
   * @RequestHeader("host") String host: http 헤더 안 host 정보만 조회한다.
   * @CookieValue(value = "myCookie", required = false) String cookie: 특정 쿠키를 조회한다.
   *
   * MultiValueMap: HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
   */

}
