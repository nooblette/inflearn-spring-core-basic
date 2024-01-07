package hello.inflearnspringcorebasic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.AppConfig;

// junit 5 이상 버전부터는 메서드와 클래스에 접근제한자 설정 필요 x
class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = applicationContext.getBean(beanDefinitionName);
			System.out.println("name = " + beanDefinitionName + " / object = " + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			// Bean 하나하나에 대한 메타데이터 정보 반환
			BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

			/**
			 * Bean 객체의 Role 조회
			 * 	Role ROLE_APPLICATION : 개발자가 직접 등록한 애플리케이션 빈
			 * 	Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			 */
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
				// 개발자가 애플리케이션을 개발하기 위해서 등록한 Bean 객체(Bean Role이 ROLE_APPLICATION)들만 조회
				Object bean = applicationContext.getBean(beanDefinitionName);
				System.out.println("name = " + beanDefinitionName + " / object = " + bean);
			}
		}
	}

}
