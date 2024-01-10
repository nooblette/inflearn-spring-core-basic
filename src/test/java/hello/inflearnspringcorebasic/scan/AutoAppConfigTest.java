package hello.inflearnspringcorebasic.scan;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AutoAppConfig;
import hello.inflearnspringcorebasic.member.MemberService;

public class AutoAppConfigTest {
	@Test
	@DisplayName("컴포넌트 스캔으로 자동 스프링 빈 등록을 확인한다.")
	void basicScan(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			AutoAppConfig.class);

		MemberService memberService = applicationContext.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
