package hello.inflearnspringcorebasic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.inflearnspringcorebasic.AppConfig;
import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;

public class OrderServiceTest {
	MemberService memberService;
	OrderService orderService;

	@BeforeEach // 테스트를 수행하기 전에 무조건 수행할 로직을 작성
	public void beforeEach(){
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	void createOrder(){
		// given
		Long memberId = 1L;
		Member member = new Member(memberId, "nooblette", Grade.VIP);

		// when
		memberService.join(member); // member 저장
		Order order = orderService.createOrder(memberId, "itemA", 20000); // 주문 생성

		// then
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000); // 실제 할인 금액이 1000원인지 확인

	}

}
