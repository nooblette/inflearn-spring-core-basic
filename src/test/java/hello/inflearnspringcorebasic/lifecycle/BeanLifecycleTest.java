package hello.inflearnspringcorebasic.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {
	@Test
	@DisplayName("빈 생명주기 콜백(빈이 생성되기 전후로 실행되는 메서드)을 테스트 한다.")
	public void lifeCycleTest(){
		ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

		NetworkClient bean = applicationContext.getBean(NetworkClient.class);
		applicationContext.close();
	}

	@Configuration
	static class LifeCycleConfig{
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://hello.spring.dev");
			return networkClient;
		}
	}
}
