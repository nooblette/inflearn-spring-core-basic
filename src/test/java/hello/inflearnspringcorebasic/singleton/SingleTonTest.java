package hello.inflearnspringcorebasic.singleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.inflearnspringcorebasic.AppConfig;
import hello.inflearnspringcorebasic.member.MemberService;

public class SingleTonTest {
	@Test
	@DisplayName("스프링 없는 순수 DI 컨테이너")
	void pureContainer(){
		AppConfig appConfig = new AppConfig();

		// given
		// 1. 조회 : 호출할 떄마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회 : 호출할 떄마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조 값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		// then
		// 테스트는 개발자가 눈으로 확인(콘솔 출력)하는게 아니라 항상 자동화를 해야한다.
		assertThat(memberService1).isNotSameAs(memberService2);
	}
}
