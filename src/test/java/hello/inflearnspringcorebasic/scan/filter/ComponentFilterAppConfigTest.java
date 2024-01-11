package hello.inflearnspringcorebasic.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.FilterType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ComponentFilterAppConfigTest {
	@Test
	@DisplayName("컴포넌트 스캔의 MyIncludeComponent 필터 기능을 테스트 한다.")
	void includeFilterScan(){
		// given
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

		// when
		BeanA beanA = applicationContext.getBean("beanA", BeanA.class);

		// then - @MyIncludeComponent 어노테이션이 붙은 BeanA 클래스는 컴포넌트 스캔 대상에 포함되어 빈으로 등록되어야 한다.
		assertThat(beanA).isNotNull();
	}

	@Test
	@DisplayName("컴포넌트 스캔의 MyExcludeComponent 필터 기능을 테스트 한다.")
	void excludeFilterScan(){
		// given
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

		// then - @MyExcludeComponent 어노테이션이 붙은 BeanB 클래스는 컴포넌트 스캔 대사에 제외되어 빈으로 등록되지 말아야 한다.
		assertThrows(NoSuchBeanDefinitionException.class,
			// when
			() -> applicationContext.getBean("beanB", BeanB.class));
	}

	@Configuration
	// @ComponentScan : 기본적으로 클래스 레벨에 @Component 어노테이션이 붙은 모든 클래스를 스프링 빈으로 등록한다.
	@ComponentScan(
		/**
		 * @ComponentScan.Filter(type = ) : type 으로 지정된 정보를 기반으로 컴포넌트 스캔 필터를 생성
		 * 	- type = ANNOTATION : 어노테이션(MyIncludeComponent 어노테이션과 MyExcludeComponent 어노테이션)으로 컴포넌트 스캔 대상 필터링
		 * 	- classes = MyIncludeComponent.class(or MyExcludeComponent.class) : 어노테이션을 정의한 java class 객체 기입
 		 */
		includeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyIncludeComponent.class), // 컴포넌트 스캔 대상에 포함하는 필터
		excludeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = MyExcludeComponent.class) // 컴포넌트 스캔 대상에 제외하는 필터
	)
	static class ComponentFilterAppConfig {

	}
}
