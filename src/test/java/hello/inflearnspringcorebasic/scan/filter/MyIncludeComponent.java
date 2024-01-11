package hello.inflearnspringcorebasic.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // ElementType.TYPE = 클래스 레벨에 붙는 어노테이션
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 클레스 레벨에 @MyIncludeComponent 어노테이션이 붙으면 컴포넌트 스캔 대상에 포함
		public @interface MyIncludeComponent {
}
