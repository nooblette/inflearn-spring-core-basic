package hello.inflearnspringcorebasic.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AppConfig;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;
import hello.inflearnspringcorebasic.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
	@Test
	@DisplayName("각기 다른 두개의 빈이 생성되는지 확인")
	void configurationTest(){
		// given
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		// when
		// XxxImpl.class 타입으로 꺼내야 getMemberRepository() 메서드 호출 가능, 실무에선 구체 클래스로 꺼내는건 권고되지 않음(DIP 위반)
		MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

		System.out.println("memberService.getMemberRepository() = " + memberService.getMemberRepository());
		System.out.println("orderService.getMemberRepository() = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);

		// then
		assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository);
		assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository);
	}

	@Test
	@DisplayName("@Configuration 구현 로직을 확인한다.")
	void configurationDeep(){
		// AnnotationConfigApplicationContext 생성자는 매개변수로 받은 클래스(e.g. AppConfig.class)도 스프링 빈(Bean)으로 등록한다.
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		AppConfig bean = applicationContext.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
	}
}
