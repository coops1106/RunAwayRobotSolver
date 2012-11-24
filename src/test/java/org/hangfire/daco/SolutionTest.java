package org.hangfire.daco;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SolutionTest {
	
	
	
	@Test
	public void testContextAvailable() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		assertNotNull(applicationContext);
	}

}
