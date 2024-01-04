package hello.inflearnspringcorebasic.member;

public class MemberServiceImpl implements MemberService{
	// OCP, DIP 위반(MemberServiceImpl 클래스는 추상화(MemberRepository)와 구현 클래스(MemoryMemberRepository)에 모두 의존하고 있다.
	private final MemberRepository memberRepository = new MemoryMemberRepository();

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
