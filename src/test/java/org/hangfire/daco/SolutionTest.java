package org.hangfire.daco;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SolutionTest {
	
	private static final String USER_NAME = "hangfire";
	private static final String PASSWORD = "empire12";
	private static final String BASE_URL = "http://www.hacker.org/runaway/?name=" + USER_NAME + "&password=" + PASSWORD;
	
	@Test
	public void whenNothingThenApplicationContextAvailable() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		assertNotNull(applicationContext);
	}
	
	@Test
	public void givenAUrlIExpectProblemToParseThePuzzle() {
		Problem problem = new Problem(BASE_URL);
		assertTrue(problem.getFullMap().length() > 0);
		assertTrue(problem.getLevel() > 0);
		assertTrue(problem.getMapSize() > 0);
		assertTrue(problem.getMaxInstructions() > 0);
		assertTrue(problem.getMinInstructions() > 0);
		assertTrue(problem.getMapArray().length > 0);
	}
	
	public class Problem {
		private String fullMap;
		private int mapSize;
		private int maxInstructions;
		private int minInstructions;
		private int level;
		private String[] mapArray;
		
		public Problem(String locationOfPuzzle) {
			BufferedReader bufferedReader = null;
			try {
				URL url = new URL(locationOfPuzzle);		   
				URLConnection urlConnection = url.openConnection();
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String inputLine;
	        	while ((inputLine = bufferedReader.readLine()) != null) {
				    if(inputLine.contains("FVterrainString=")) {
				    	this.fullMap = this.getPuzzleParameter(inputLine, "FVterrainString=");
				    	this.mapSize = Integer.parseInt(this.getPuzzleParameter(inputLine, "FVboardX="));
				    	this.maxInstructions = Integer.parseInt(this.getPuzzleParameter(inputLine, "FVinsMax="));
				    	this.minInstructions = Integer.parseInt(this.getPuzzleParameter(inputLine, "FVinsMin="));
				    	this.level = Integer.parseInt(this.getPuzzleParameter(inputLine, "FVlevel="));
				    	this.mapArray = this.fullMap.split("(?<=\\G.{" + this.mapSize + "})");
				    }
	        	}
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private String getPuzzleParameter(String inputLine, String parameterName) {
			int beginIndex = inputLine.indexOf(parameterName) + parameterName.length();
			int endIndex = inputLine.indexOf("&", beginIndex);
			if (endIndex < 0) {
				endIndex = inputLine.indexOf("\"", beginIndex);
			}
			return inputLine.substring(beginIndex, endIndex);
		}
		
		public String getFullMap() {
			return fullMap;
		}

		public void setFullMap(String fullMap) {
			this.fullMap = fullMap;
		}

		public int getMapSize() {
			return mapSize;
		}

		public void setMapSize(int mapSize) {
			this.mapSize = mapSize;
		}

		public int getMaxInstructions() {
			return maxInstructions;
		}

		public void setMaxInstructions(int maxInstructions) {
			this.maxInstructions = maxInstructions;
		}

		public int getMinInstructions() {
			return minInstructions;
		}

		public void setMinInstructions(int minInstructions) {
			this.minInstructions = minInstructions;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		@Override
		public String toString() {
			return "Problem [fullMap=" + fullMap + ", mapSize=" + mapSize
					+ ", maxInstructions=" + maxInstructions
					+ ", minInstructions=" + minInstructions + ", level="
					+ level + ", mapArray=" + Arrays.toString(mapArray) + "]";
		}

		public String[] getMapArray() {
			return mapArray;
		}

		public void setMapArray(String[] mapArray) {
			this.mapArray = mapArray;
		}

	}
	
}
