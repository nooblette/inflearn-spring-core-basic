package hello.inflearnspringcorebasic.member;

public class MemberServiceImpl implements MemberService{
	MemberService memberService;

	/**
	 * 	OCP, DIP 위반
	 *  - MemberServiceImpl 클래스는 추상화(MemberRepository)와 구현 클래스(MemoryMemberRepository)에 모두 의존하고 있다. (DIP 위반)
	 * 		마치 배우가 직접 그 역할의 담당 배우를 섭외하는 꼴 -> 배우는 배역에만 집중하고 담당 배우 섭외는 공연 기획자가 수행하는게 적절하다.(= 책임의 분리)
	 * 	- MemberServiceImpl 클래스는 MemberRepository 정책이 바뀌면 의존 관계를 수정해주어야 한다.(OCP 위반)
	 * 	- MemberServiceImpl 클래스는 의존관계 설정과 Member 비즈니스 로직 수행까지 여러개의 역할을 수행한다. (SRP 위반)
	 *
	 * 	클라이언트인 MemberServiceImpl 클래스는 MemberRepository 인터페이스에만 의존하며, (DIP 준수)
	 * 	 AppConfig.java가 MemoryMemberRepository(구현 객체)를 생성하고 연결한다. (= 관심사의 분리, SRP 준수)
	 * 	 MemberServiceImpl 클래스는 MemberRepository 인터페이스의 구현 객체에 의존하지 않기 때문에 정책 변경에 더 이상 영향을 받지 않는다. (OCP 준수)
 	 */
	private final MemberRepository memberRepository;

	// 의존 관계를 외부에서 주입 (의존관계 주입, DI, Dependency Injection)
	public MemberServiceImpl(MemberRepository memberRepository){
		/**
		 * MemberServiceImpl 클래스는 MemberRepository 인터페이스(추상화, 역할)에만 의존한다. (DIP 준수)
		 * 클라이언트인 MemberServiceImpl 클래스는
		 * - MemberRepository 인터페이스의 구현 객체는 외부에서 주입받는다.
		 * - 의존관계에 대한 고민은 외부(AppConfig.java)에 맡기고 실행(save(), findById() 메서드 호출)에만 집중할 수 있다.
		 * */
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
