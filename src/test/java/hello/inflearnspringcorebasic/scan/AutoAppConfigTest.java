package hello.inflearnspringcorebasic.scan;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AutoAppConfig;
import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.order.OrderServiceImpl;

public class AutoAppConfigTest {
	@Test
	@DisplayName("컴포넌트 스캔으로 자동 스프링 빈 등록을 확인한다.")
	void basicScan(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			AutoAppConfig.class);

		MemberService memberService = applicationContext.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);

		OrderServiceImpl orderService = applicationContext.getBean("orderServiceImpl", OrderServiceImpl.class);
		MemberRepository memberRepository = orderService.getMemberRepository();
		DiscountPolicy discountPolicy = orderService.getDiscountPolicy();
		System.out.println("memberRepository = " + memberRepository);
		System.out.println("discountPolicy = " + discountPolicy);
	}
}
