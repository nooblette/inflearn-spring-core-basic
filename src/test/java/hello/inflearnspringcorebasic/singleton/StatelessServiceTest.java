package hello.inflearnspringcorebasic.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatelessServiceTest {

	@Test
	@DisplayName("싱글톤 객체는 무상태로 설계해야하 한다.")
	void statelessServiceSingleton(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			TestConfig.class);

		StatelessService statelessService1 = applicationContext.getBean(StatelessService.class);
		StatelessService statelessService2 = applicationContext.getBean(StatelessService.class);

		// ThreadA : A 사용자가 10000원 주문
		int priceOrUserA = statelessService1.order("userA", 10000);

		// ThreadB : B 사용자가 20000원 주문
		int priceOfUserB = statelessService2.order("userB", 20000);

		System.out.println("priceOrUserA = " + priceOrUserA);
		System.out.println("priceOfUserB = " + priceOfUserB);


		// then
		assertThat(priceOrUserA).isEqualTo(10000);
	}

	static class TestConfig {
		@Bean
		public StatelessService statelessService() {
			return new StatelessService();
		}
	}
}