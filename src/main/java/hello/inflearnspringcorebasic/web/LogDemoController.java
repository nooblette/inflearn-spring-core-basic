package hello.inflearnspringcorebasic.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.inflearnspringcorebasic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final 키워드가 붙은 변수에 대해 생성자 자동 생성 및 @Autowired 자동 추가(생성자가 하나면 @Autowired 어노테이션은 생략 가능)
public class LogDemoController {
	private final LogDemoService logDemoService;
	// myLogger 빈을 주입하는게 아니라 myLogger 빈을 DL(Dependency Lookup)할 수 있는 Provider가 주입된다.
	// -> 스코프가 "request"인 빈(Bean)이 아닌 이러한 빈을 찾을(LookUp) Provider는 스프링 컨테이너 실행 시점에 주입할 수 있다.
	private final ObjectProvider<MyLogger> myLoggerProvider;

	@RequestMapping("log-demo")
	@ResponseBody // View 화면(템플릿 랜더링) 없이 문자를 그대로 반환
	// HttpServletRequest : java에서 제공하는 표준 servlet 규약에 의한 고객의 http 요청 정보를 받을 수 있음
	public String logDemo(HttpServletRequest request){
		StringBuffer requestURL = request.getRequestURL(); // getRequestURL() : 고객이 요쳥한 url 정보를 반환

		MyLogger myLogger = myLoggerProvider.getObject(); // getObject() : 고객의 http request 요청이 발생하여 생성된 myLogger 빈을 반환
		myLogger.setRequestURL(requestURL.toString()); // 고객이 요청한 url 정보를 MyLogger 클래스에 set

		myLogger.log("Controller test");
		logDemoService.logic("testId");
		return "OK";
	}
}
