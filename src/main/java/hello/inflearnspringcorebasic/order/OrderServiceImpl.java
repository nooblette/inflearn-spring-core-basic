package hello.inflearnspringcorebasic.order;

import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.discount.FixDiscountPolicy;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberRepository;
import hello.inflearnspringcorebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);

		// DiscountPolicy는 할인 관련 책임을 갖는 역할을 수행
		// 할인 정책이 바뀌더라도 OrderService는 영향을 받지 않으며 그 구현은 몰라도 된다(OCP 준수)
		int discountPrice = discountPolicy.discount(member, itemPrice); // command + option + b : 구현 클래스의 메서드 구현부로 이동

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
