//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Arrays;
//
//import org.junit.Test;
//
//public class Archive {
//	
//	
//	
//	
//	
//	@Test
//	public void givenAUrlResponseWhenIParseItThenIExpectACompletePuzzleContainer() {
//		RunawayRobotPuzzle aPuzzle = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
//		assertEquals(2025, aPuzzle.getFullMap().length());
//		assertEquals(84, aPuzzle.getLevel());
//		assertEquals(45, aPuzzle.getMapSize());
//		assertEquals(27, aPuzzle.getMaxInstructions());
//		assertEquals(16, aPuzzle.getMinInstructions());
//		assertEquals(45, aPuzzle.getMapArray().length);
//	}
//	
//	@Test
//	public void givenAMapRowAndAPositionICanKnowTheFirstRockToTheRightOfThePosition() {
//		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
//		int currentPosition = 15;
//		int currentRow = 0;
//		int firstRockPosition = problem.getRockOnRowAtPosition(currentRow, currentPosition);
//		System.out.println("First rock position is: " + firstRockPosition);
//		assertTrue(firstRockPosition > 0);
//	}
//	
//	@Test
//	public void givenARowAndAPositionICanKnowIfThePositionOnTheRowBeneathIsARock() {
//		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
//		int currentPosition = 11;
//		int currentRow = 0;
//		boolean rock = problem.isPositionUnderneathARock(currentRow, currentPosition);
//		System.out.println(rock);
//	}
//	
//	@Test
//	public void givenARowAndAPositionICanFindTheFurtherestRightICanGoBeforeHittingARock() {
//		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
//		int currentPosition = 0;
//		int currentRow = 0;
//		try {
//			while(currentRow != problem.getMapSize() - 1 && currentPosition != problem.getMapSize() - 1) {
//				//Go as far right as possible
//				currentPosition = problem.getRockOnRowAtPosition(currentRow, currentPosition) - 1;
//				//whats underneath
//				while (problem.isPositionUnderneathARock(currentRow, currentPosition)) {
//					currentPosition--;
//				}
//				problem.getMapArray()[currentRow] = problem.getMapArray()[currentRow].substring(0, currentPosition) + '>' + problem.getMapArray()[currentRow].substring(currentPosition+1);
//				System.out.println(problem.getMapArray()[currentRow]);
//				currentRow++;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("Current row is: " + currentRow + "\n" + "Current position is: " + currentPosition);
//	}
//	
//	public class RunawayRobotPuzzle {
//		
//		public boolean isPositionUnderneathARock(int row, int position) {
//			String currentRowDef = this.getMapArray()[row];
//			String underneathRowDef = this.getMapArray()[row + 1];
//			//System.out.println("underneath row definition: " + underneathRowDef);
//			return underneathRowDef.charAt(position) == 'X';
//		}
//
//		public int getRockOnRowAtPosition(int row, int position) {
//			String rowDef = this.getMapArray()[row];
//			//System.out.println("Row " + row + " definition: " + rowDef);
//			int rockPosition = position;
//			boolean found = false;
//			while(found == false && rockPosition < this.mapSize) {
//				if(rowDef.charAt(rockPosition) == 'X') {
//					found = true;
//				} else {
//					rockPosition++;
//				}
//			}
//			return rockPosition;
//		}
//
//		public String getFullMap() {
//			return fullMap;
//		}
//
//		public void setFullMap(String fullMap) {
//			this.fullMap = fullMap;
//		}
//
//		public int getMapSize() {
//			return mapSize;
//		}
//
//		public void setMapSize(int mapSize) {
//			this.mapSize = mapSize;
//		}
//
//		public int getMaxInstructions() {
//			return maxInstructions;
//		}
//
//		public void setMaxInstructions(int maxInstructions) {
//			this.maxInstructions = maxInstructions;
//		}
//
//		public int getMinInstructions() {
//			return minInstructions;
//		}
//
//		public void setMinInstructions(int minInstructions) {
//			this.minInstructions = minInstructions;
//		}
//
//		public int getLevel() {
//			return level;
//		}
//
//		public void setLevel(int level) {
//			this.level = level;
//		}
//
//		@Override
//		public String toString() {
//			return "Problem [fullMap=" + fullMap + ", mapSize=" + mapSize
//					+ ", maxInstructions=" + maxInstructions
//					+ ", minInstructions=" + minInstructions + ", level="
//					+ level + ", mapArray=" + Arrays.toString(mapArray) + "]";
//		}
//
//		public String[] getMapArray() {
//			return mapArray;
//		}
//
//		public void setMapArray(String[] mapArray) {
//			this.mapArray = mapArray;
//		}
//
//	}
//	
//}
