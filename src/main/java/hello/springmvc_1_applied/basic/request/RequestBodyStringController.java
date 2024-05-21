package hello.springmvc_1_applied.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


/**
 * 메시지바디에 담은 데이터가 텍스트일 경우
 */

@Slf4j
@Controller
public class RequestBodyStringController {

  @PostMapping("/request-body-string-v1")
  public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("messageBody = {}", messageBody);
    response.getWriter().write("ok");
  }

  @PostMapping("/request-body-string-v2")
  public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
          throws IOException {
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("messageBody={}", messageBody);
    responseWriter.write("ok");
    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
  }

  @PostMapping("/request-body-string-v3")
  public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
    String messageBody = httpEntity.getBody();
    log.info("messageBody={}", messageBody);
    return new HttpEntity<>("ok");
    /**
     * 매개변수 HttpEntity 를 사용하여 HTTP header, body 정보를 편리하게 조회가능하다.
     * return new HttpEntity<>("ok");: 응답에도 HttpEntity 사용 가능하다.
     *
     * HttpEntity
     *    요청 HTTP header, message body 정보 조회
     *    응답 또한 가능
     *
     * RequestEntity, ResponseEntity 둘 다 HttpEntity 상속 받음
     *
     * RequestEntity
     *    HttpMethod, url 정보가 추가, 요청에서 사용
     * ResponseEntity
     *    HTTP 상태 코드 설정 가능, 응답에서 사용
     *    return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED)
     */
  }

  @ResponseBody
  @PostMapping("/request-body-string-v4")
  public String requestBodyStringV4(@RequestBody String messageBody) {
    log.info("messageBody={}", messageBody);
    return "ok";
  }

}
