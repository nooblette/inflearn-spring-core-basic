package hello.inflearnspringcorebasic.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.inflearnspringcorebasic.discount.DiscountPolicy;
import hello.inflearnspringcorebasic.discount.FixDiscountPolicy;
import hello.inflearnspringcorebasic.discount.RateDiscountPolicy;

class ApplicationContextExtendsFindTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
	void findBeanByParentTypeDuplicate(){
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> applicationContext.getBean(DiscountPolicy.class));
	}


	@Test
	@DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 이름을 지정해줘야 한다.")
	void findBeanByParentTypeBeanName(){
		DiscountPolicy bean = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}


	@Test
	@DisplayName("특정 하위 타입으로 조회(DIP를 위반하기 때문에 좋지는 않은 방법)")
	void findBeanByImplementType(){
		RateDiscountPolicy bean = applicationContext.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
		assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모든 자식 객체를 조회")
	void findAllBeanByParentType(){
		Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);

		for (String key : beansOfType.keySet()) {
			// 실제 테스트 코드 작성시에는 콘솔로 출력하지 않는다(지금은 빈이 잘 생성되었는지 단순 확인하기 위함)
			// 디버깅이나 기능 개발 중에는 로직 이해를 위해 콘솔 출력을 할 수는 있지만, 테스트 코드는 테스트 프레임워크와 시스템이 테스트를 검증해야지 개발자가 눈으로 통과 여부를 검증해선 안된다.
			System.out.println("key = " + key + " / value = " + beansOfType.get(key));
		}
	}

	@Test
	@DisplayName("최상위 Object 타입으로 모든 자식 객체를 조회")
	void findAllBeanByObjectType(){
		Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " / value = " + beansOfType.get(key));
		}
	}
	@Configuration
	static class TestConfig {
		@Bean
		public DiscountPolicy rateDiscountPolicy(){
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy(){
			return new FixDiscountPolicy();
		}
	}
}
