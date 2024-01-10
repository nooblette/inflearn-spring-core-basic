package hello.inflearnspringcorebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 빈 설정 정보를 담는 클래스이므로 @Configuration 어노테이션 기입(누락하면 스프링 빈이 스프링 컨테이너에 등록되지 않고 싱글톤으로 생성되지 못한다.)
@ComponentScan( // @Component 어노테이션이 붙은 클래스를 모두 찾아서 자동으로 스프링 빈으로 등록

	// 스프링 빈으로 등록하기 위해 @Component 어노테이션이 붙은 클래스를 찾을때, 제외할 케이스를 지정
	// Configuration 어노테이션이 붙은 클래스는 스프링 빈 등록 제외
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
