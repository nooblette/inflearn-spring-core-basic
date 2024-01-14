package hello.inflearnspringcorebasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.discount.RateDiscountPolicy;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;
import hello.inflearnspringcorebasic.order.OrderService;
import hello.inflearnspringcorebasic.order.OrderServiceImpl;

/**
 * AppConfig를 스프링 기반으로 변경
 */
@Configuration
public class AppConfig {
	/**
	 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
	 * e.g. MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy ...
	 * */
	@Bean // Bean 어노테이션 : 해당 객체를 스프링 컨테이너에 등록 (이때 객체명은 메서드 이름으로 등록된다)
	public MemberService memberService() {
		/**
		 * 객체 인스턴스의 참조(레퍼런스)를 생성자 주입(Injection) (= 생성자를 통해 구현 객체를 연결)
		 * MemverServiceImpl -> MemoryMemberRepository
		 */
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService(){
		/**
		 * 객체 인스턴스의 참조(레퍼런스)를 생성자 주입 (= 생성자를 통해 구현 객체를 연결)
		 * OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
		 * */
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	// command + option + m : 중복 코드 메서드 추출(리팩토링)
	// 메서드 추출로 현재 MemberRepository 역할은 MemoryMemberRepository 구현 클래스가 수행한다는 것을 명확히 한눈에 드러낸다.
	// MemberRepository 인터페이스의 구현 객체가 변경되더라도 createMemberRepository() 메서드만 수정하여 영향 범위를 줄일 수 있다.
	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	// 메서드 추출로 현재 DiscountPolicy 역할은 FixDiscountPolicy 구현 클래스가 수행한다는 것을 명확히 한눈에 드러낸다.
	@Bean
	public DiscountPolicy discountPolicy(){
		// return new FixDiscountPolicy();

		/* 정률 할인 정책으로 변경*/
		return new RateDiscountPolicy();
	}
}
