package hello.springmvc_1_applied.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username = {}, age = {}", username, age);
    response.getWriter().write("ok");
  }

  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
    log.info("username = {}, age = {}", memberName, memberAge);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(@RequestParam String username, @RequestParam int age) {
    // HTTP 파라미터 이름이 변수 이름과 같으면 파라미터명 생략 가능
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    // String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
          @RequestParam(required = true) String username,
          @RequestParam(required = false) Integer age) {
    log.info("username={}, age={}", username, age);
    return "ok";

    /**
     * @RequestParam(required = false) int age
     * 기본형 타입은 메모리의 할당된 공간에 직접 값을 저장하기 때문에, null 이 들어갈 수 없다. (500 예외 발생)
     *
     * 해결 방안
     *  1. 래퍼클래스인 Integer
     *  2. defaultValue 속성 추가
     */

  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
          @RequestParam(required = true, defaultValue = "guest") String username,
          @RequestParam(required = false, defaultValue = "-1") int age) {
    log.info("username={}, age={}", username, age);
    return "ok";
    /**
     *  빈 문자의 경우에도 설정한 기본 값이 적용된다.
     */
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam MultiValueMap<String, String> multiValueMap){
    // http://localhost:8080/request-param-map?username=ojg&age=32&age=31
    log.info("username={}, age={}", multiValueMap.get("username"), multiValueMap.get("age")); // username=[ojg], age=[32, 31]
    return "ok";
  }

  /**
   * MultiValueMap 이점
   * @RequestParam 내 속성 required, defaultValue 속성을 신경 쓸 필요없다.
   */

}
