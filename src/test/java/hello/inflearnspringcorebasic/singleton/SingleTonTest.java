package hello.inflearnspringcorebasic.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest(){
		//new SingletonService();
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		// then
		// - isSameAs : `==` 과 동일한 연산
		// - isEqualTo : `Object.equals()`와 동일한 연산
		assertThat(singletonService1).isSameAs(singletonService2);

	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer(){
		// 스프링 컨테이너(ApplicationContext)로 객체 생성
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		//AppConfig appConfig = new AppConfig();

		// given
		// 1. 조회 : 호출할 떄마다 객체를 생성
		MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);

		// 2. 조회 : 호출할 떄마다 객체를 생성
		MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

		// 참조 값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		// then
		// 테스트는 개발자가 눈으로 확인(콘솔 출력)하는게 아니라 항상 자동화를 해야한다.
		assertThat(memberService1).isSameAs(memberService2);
	}
}
