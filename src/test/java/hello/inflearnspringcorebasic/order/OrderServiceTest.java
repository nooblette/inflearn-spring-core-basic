package hello.inflearnspringcorebasic.order;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;

public class OrderServiceTest {
	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	@Test
	void createOrder(){
		// given
		Long memberId = 1L;
		Member member = new Member(memberId, "nooblette", Grade.VIP);

		// when
		memberService.join(member); // member 저장
		Order order = orderService.createOrder(memberId, "itemA", 10000); // 주문 생성

		// then
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 실제 할인 금액이 1000원인지 확인

	}

}
