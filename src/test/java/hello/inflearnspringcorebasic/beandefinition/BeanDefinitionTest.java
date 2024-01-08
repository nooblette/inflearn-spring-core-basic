package hello.inflearnspringcorebasic.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AppConfig;

public class BeanDefinitionTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 설정 메타정보(Bean Definition) 확인")
	void findApplicationBean(){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

			// ROLE_APPLICATION(개발자가 직접 등록한 애플리케이션 빈)만 출력
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
				System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
			}
		}
	}
}
