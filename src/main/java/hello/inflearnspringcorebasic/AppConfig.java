package hello.inflearnspringcorebasic;

import hello.inflearnspringcorebasic.discount.FixDiscountPolicy;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;
import hello.inflearnspringcorebasic.order.OrderService;
import hello.inflearnspringcorebasic.order.OrderServiceImpl;

/**
* 애플리케이션(App)의 전체 동작 방식을 구성(Config)
* "구현 객체를 생성"하고 "연결"하는 책임을 갖는 별도의 설정 클래스
*/
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
		return new MemberServiceImpl(new MemoryMemberRepository());
	}

	public OrderService orderService(){
		/**
		 * 객체 인스턴스의 참조(레퍼런스)를 생성자 주입 (= 생성자를 통해 구현 객체를 연결)
		 * OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
		 * */
		return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
	}
}
