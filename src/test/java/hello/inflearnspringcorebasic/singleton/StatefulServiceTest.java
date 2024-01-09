package hello.inflearnspringcorebasic.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	@DisplayName("상태를 유지 싱글톤 객체의 문제점을 확인한다.")
	void statefulServiceSingleton(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			TestConfig.class);

		StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
		StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

		// ThreadA : A 사용자가 10000원 주문
		statefulService1.order("userA", 10000);

		// ThreadB : B 사용자가 20000원 주문
		statefulService2.order("userB", 20000);

		// ThreadA : 사용자 A의 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("사용자 A의 주문 금액 price = " + price);

		// then
		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}