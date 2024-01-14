package hello.inflearnspringcorebasic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberRepository;

@Component // 컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈으로 등록
public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository;

	/**
	 * 클라이언트인 OrderServiceImpl 클래스는 DiscountPolicy 인터페이스와 FixDiscountPolicy(구현 객체) 클래스에 직접적으로 의존 (DIP 위반)
	 * 할인 정책을 변경하려면 클라이언트의 코드를 수정해야한다. (OCP 위반)
	 * OrderServiceImpl 클래스는 의존 관계 설정과 주문 생성까지 두 개의 역할을 수행한다. (SRP 위반)
	 * private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	 */

	/**
	 * 	DIP를 준수하기 위해 인터페이스에만 의존하도록 코드를 변경
	 * 	하지만 누군가(AppConfig.java)가 클라이언트인 OrderServiceImpl 클래스에 discountPolicy 인터페이스의 구현 객체를 대신 생성하고 넣어주어야 한다. (관심사의 분리)
 	 */

	private final DiscountPolicy discountPolicy;

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);

		// DiscountPolicy는 할인 관련 책임을 갖는 역할을 수행
		// 할인 정책이 바뀌더라도 OrderService는 영향을 받지 않으며 그 구현은 몰라도 된다(OCP 준수)
		int discountPrice = discountPolicy.discount(member, itemPrice); // command + option + b : 구현 클래스의 메서드 구현부로 이동

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// for test
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

	public DiscountPolicy getDiscountPolicy() {
		return discountPolicy;}
}
