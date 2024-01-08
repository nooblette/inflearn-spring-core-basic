package hello.inflearnspringcorebasic.xml;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.inflearnspringcorebasic.member.MemberService;

public class XmlApplicationContext {
	@Test
	@DisplayName("xml 파일로 스프링 설정")
	void xmlApplicationContext(){
		// given
		GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext("appConfig.xml");

		// when
		MemberService memberService = genericXmlApplicationContext.getBean("memberService", MemberService.class);

		// then
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
