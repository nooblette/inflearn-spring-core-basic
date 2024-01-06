package hello.inflearnspringcorebasic;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;

public class MemberApplication {
	public static void main(String[] args) {
		AppConfig appConfig = new AppConfig(); // AppConfig : "구현 객체를 생성"하고 "연결"하는 책임을 갖는 별도의 설정 클래스
		MemberService memberService = appConfig.memberService();

		Member member = new Member(1L, "nooblette", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("findMember : " + findMember.getName());
		System.out.println("member = " + member.getName());

	}
}
