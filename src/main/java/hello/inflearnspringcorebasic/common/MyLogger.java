package hello.inflearnspringcorebasic.common;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;

@Component
@Scope(value = "request") // MyLogger 빈은 http 요청 당 하나씩 생성되고 요청이 끝나는 시점에 소멸된다.
public class MyLogger { // 로그를 출력하기 위한 클래스
	private String uuid;

	@Setter // requestURL은 MyLogger 빈이 생성되는 시점에 알 수 없으므로 외부에서 setter로 입력받는다.
	private String requestURL;

	public void log(String message){
		System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
	}

	@PostConstruct // 빈이 생성되는 시점(빈 생성과 의존관계 주입 후 빈 사용 전)에 호출하는 초기화 메서드
	public void init(){
		// HTTP 요청 당 UUID가 하나씩 생성되므로, UUID를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
		String uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: " + this);
	}

	@PreDestroy // http 요청이 끝나고 빈 소멸 직전에 호출하는 소멸 전 메서드
	public void close(){
		System.out.println("[" + uuid + "] request scope bean close: " + this);
	}

}
