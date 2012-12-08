package org.hangfire.problem;

import org.hangfire.utils.UrlUtils;

public class ProblemContainerRepository {
	
	private static final String USER_NAME = "hangfire";
	private static final String PASSWORD = "empire12";
	private static final String BASE_URL = "http://www.hacker.org/runaway/?name=" + USER_NAME + "&password=" + PASSWORD;
	
	public String fetchCurrentProblem() {
		return UrlUtils.getUrlResponse(BASE_URL);
	}
		
}
