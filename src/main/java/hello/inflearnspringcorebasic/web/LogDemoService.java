package hello.inflearnspringcorebasic.web;

import org.springframework.stereotype.Service;

import hello.inflearnspringcorebasic.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
	private final MyLogger myLogger;

	public void logic(String id) {
		myLogger.log("Service id = " + id);

	}
}
