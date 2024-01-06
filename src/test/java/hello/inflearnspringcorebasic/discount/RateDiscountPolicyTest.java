package hello.inflearnspringcorebasic.discount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;

class RateDiscountPolicyTest {

	// RateDiscountPolicy가 정말 10% 할인을 하는지 테스트
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다.")
	void vip_percent_discount_success(){
		// given
		Member member = new Member(1L, "vipMember", Grade.VIP);

		// when
		int discountedPrice = discountPolicy.discount(member, 10000);

		// then
		assertThat(discountedPrice).isEqualTo( 1000);
	}

	// 성공 테스트도 중요하지만 실패테스트도 꼭 만들어주어야 한다.
	@Test
	@DisplayName("VIP아니면 할인이 적용되지 않아야 한다.")
	void vip_percent_discount_fail(){
		// given
		Member member = new Member(2L, "basicMember", Grade.BASIC);

		// when
		int discountedPrice = discountPolicy.discount(member, 10000);

		// then
		assertThat(discountedPrice).isEqualTo( 0);
	}
}