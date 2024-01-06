package hello.inflearnspringcorebasic;

import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.discount.RateDiscountPolicy;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;
import hello.inflearnspringcorebasic.order.OrderService;
import hello.inflearnspringcorebasic.order.OrderServiceImpl;

/**
* 애플리케이션(App)의 전체 동작 방식을 구성(Config)
* "구현 객체를 생성"하고 "연결"하는 책임을 갖는 별도의 설정 클래스
*/
// 현재 AppConfig 클래스(구성 영역)는 어떤 객체가 어떤 객체들과 의존하고 있는지가 간단하게 한눈에 보이지 않으며, 코드 중복도 많이 존재한다.
// 구성 영역은 모든 객체들의 역할과 구현을 알고 있으며 당연히 변경이 있을떄 수정된다. 이때, 변경이 되더라도 사용 영역(MemberServiceImpl, MemberRepository, OrderServiceImpl, DiscountPolicy 등.. )의 코드는 수정을 가하지 않는다.
public class AppConfig {
	/**
	 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
	 * e.g. MemberServiceImpl, MemoryMemberRepository, OrderServiceImpl, FixDiscountPolicy ...
	 * */
	public MemberService memberService() {
		/**
		 * 객체 인스턴스의 참조(레퍼런스)를 생성자 주입(Injection) (= 생성자를 통해 구현 객체를 연결)
		 * MemverServiceImpl -> MemoryMemberRepository
		 */
		return new MemberServiceImpl(createMemberRepository());
	}

	public OrderService orderService(){
		/**
		 * 객체 인스턴스의 참조(레퍼런스)를 생성자 주입 (= 생성자를 통해 구현 객체를 연결)
		 * OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
		 * */
		return new OrderServiceImpl(createMemberRepository(), createDisCountPolicy());
	}

	// command + option + m : 중복 코드 메서드 추출(리팩토링)
	// 메서드 추출로 현재 MemberRepository 역할은 MemoryMemberRepository 구현 클래스가 수행한다는 것을 명확히 한눈에 드러낸다.
	// MemberRepository 인터페이스의 구현 객체가 변경되더라도 createMemberRepository() 메서드만 수정하여 영향 범위를 줄일 수 있다.
	private MemberRepository createMemberRepository() {
		return new MemoryMemberRepository();
	}

	// 메서드 추출로 현재 DiscountPolicy 역할은 FixDiscountPolicy 구현 클래스가 수행한다는 것을 명확히 한눈에 드러낸다.
	private DiscountPolicy createDisCountPolicy(){
		// return new FixDiscountPolicy();

		/* 정률 할인 정책으로 변경*/
		return new RateDiscountPolicy();
	}
}
