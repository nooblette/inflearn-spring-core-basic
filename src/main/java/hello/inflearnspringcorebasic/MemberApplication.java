package hello.inflearnspringcorebasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;

public class MemberApplication {
	public static void main(String[] args) {
		/**
		 * 스프링은 모두 ApplicationContext 로 부터 시작
		 * ApplicationContext: 스프링이 관리하는 모든 객체들을 담고있는 컨테이너
		 */
		ApplicationContext applicationContext =
			// Spring 프레임워크가 AppConfig.class 파일에서 Bean 어노테이션이 붙은 객체들을 생성하고 컨테이너에 담아서 관리한다.
			new AnnotationConfigApplicationContext(AppConfig.class);

		/**
		 * getBean () : 스프링 컨테이너(applicationContext)에서 스프링이 관리중인 Bean 객체를 반환
		 * 이때 꺼낼 객체명을 메서드의 첫번째 매개변수로, 객체의 타입을 두번쨰 매개변수로 지정한다.
		 */
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

		Member member = new Member(1L, "nooblette", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("findMember : " + findMember.getName());
		System.out.println("member = " + member.getName());

	}
}
