package hello.inflearnspringcorebasic;

import hello.inflearnspringcorebasic.member.Grade;
import hello.inflearnspringcorebasic.member.Member;
import hello.inflearnspringcorebasic.member.MemberService;
import hello.inflearnspringcorebasic.member.MemberServiceImpl;

public class MemberApplication {
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();

		Member member = new Member(1L, "nooblette", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("findMember : " + findMember.getName());
		System.out.println("member = " + member.getName());

	}
}
