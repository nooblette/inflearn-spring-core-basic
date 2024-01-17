package hello.inflearnspringcorebasic.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonTest {
	@Test
	@DisplayName("빈이 싱글톤 스코프를 따르는 경우를 테스트 한다.")
	public void singletonBeanFind() {
		// given
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonBean.class);

		// when
		SingletonBean singletonBean1 = applicationContext.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = applicationContext.getBean(SingletonBean.class);
		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);

		// then
		assertThat(singletonBean1).isSameAs(singletonBean2);
		applicationContext.close(); //종료
	}

	@Scope("singleton")
	static class SingletonBean {
		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
