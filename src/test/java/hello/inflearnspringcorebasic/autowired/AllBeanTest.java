package hello.inflearnspringcorebasic.autowired;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import hello.inflearnspringcorebasic.AutoAppConfig;
import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;

public class AllBeanTest {
	@Test
	@DisplayName("컨테이너에 특정 타입으로 정의된 빈이 모두 필요한 경우를 테스트한다.")
	void findAllBean(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			AutoAppConfig.class, DiscountService.class);

		DiscountService discountService = applicationContext.getBean(DiscountService.class);
		Member member = new Member(1L, "userA", Grade.VIP);
		int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(fixDiscountPrice).isEqualTo(1000);

		int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
		assertThat(rateDiscountPrice).isEqualTo(2000);

	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policyList;

		// @Autowired // 생성자가 한 개면 생략 가능
		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
			this.policyMap = policyMap;
			this.policyList = policyList;

			System.out.println("policyMap = " + policyMap);
			System.out.println("policyList = " + policyList);
		}

		public int discount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			return discountPolicy.discount(member, price);
		}
	}
}
