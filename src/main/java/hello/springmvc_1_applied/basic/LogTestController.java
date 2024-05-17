package hello.springmvc_1_applied.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {

    String name = "Spring";

    log.trace("trace log={}", name);
    log.debug("debug log={}", name);
    log.info(" info log={}", name);
    log.warn(" warn log={}", name);
    log.error("error log={}", name);

    // 로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
    log.debug("String concat log=" + name);
    return "ok";

  }

  /**
   * @Controller: 반환값이 String 이면 뷰 이름으로 인식해서 뷰를 찾고 렌더링된다.
   * @RestController:
   *    반환값으로 뷰를 찾는 것이 아니라, Http MessageBody 에 바로 입력한다.
   *    따라서 실행 결과로 상태 메시지를 받을 수 있다.
   */

}
