package hello.inflearnspringcorebasic.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔의 대상이 되어 자동으로 스프링 빈으로 등록
public class MemoryMemberRepository implements MemberRepository{
	// 실무에서 Map을 사용할 경우 동시성 이슈가 있기 때문에, ConcurrentMap을 사용한다.
	private static Map<Long, Member> store = new HashMap<>();
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
