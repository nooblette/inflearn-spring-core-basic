package hello.inflearnspringcorebasic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
	MemberService memberService = new MemberServiceImpl(); // command + shift + enter : 세미콜론(;) 까지 추가
	@Test
	void join() {
		// given : 이런 환경에서
		Member member = new Member(1L, "nooblette", Grade.VIP); // option + command + v : 참조 변수 생성

		// when : 이렇게 했을떄
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		// then : 이 결과가 생긴다
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
