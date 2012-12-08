package org.hangfire.daco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class SolutionTest {
	
	private static final String USER_NAME = "hangfire";
	private static final String PASSWORD = "empire12";
	private static final String BASE_URL = "http://www.hacker.org/runaway/?name=" + USER_NAME + "&password=" + PASSWORD;
	
	private static final String MOCK_URL_RESPONSE = "<html><head><title>Runaway Robot</title>   <META NAME=\"keywords\" CONTENT=\"game, hack, hacking, programming, competition,runaway\">   <META NAME=\"description\" CONTENT=\"Play and hack a runaway robot puzzle game.\">	<LINK rel=\"stylesheet\" href=\"/css/style.css\" type=\"text/css\"></head><body><table border=0 cellspacing=0 cellpadding=0><tr valign=center><td> <img src=\"/images/header.png\" width=\"877\" height=\"68\" border=\"0\" alt=\"\" usemap=\"#header_Map\"><map name=\"header_Map\"><area shape=\"rect\" alt=\"\" coords=\"753,41,858,63\" href=\"/forum/profile.php?mode=register\"><area shape=\"rect\" alt=\"\" coords=\"753,14,858,36\" href=\"/forum/login.php\"><area shape=\"rect\" alt=\"\" coords=\"0,22,223,61\" href=\"/\"></map><map name=\"header_Map_li\"><area shape=\"rect\" alt=\"\" coords=\"0,22,223,61\" href=\"/\"></map></td><td align=left style=\"border-bottom: 1px solid #d4f3e1\"><img src=\"logo.png\"></td></tr></table><table border=0><tr valign=top><td><object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0\" width=\"550\" height=\"500\" id=\"escape\" align=\"middle\"><PARAM NAME=FlashVars VALUE=\"FVterrainString=.........X.X.XX...X....XX....X...X.......X.....X....XX..XX...........XX..........X..........XX..X.............XX.X...X...X.X...X.X.XX....XX..................X......XX..XX.X.....X.X....XX.........X.....X.........X.....X.........X...X............X.XX..X...X.....X.....X..............X.X.....X.........X..........X..X.X.......X.X.XX.........X............X.........XX.X........XX..X......X.X..X..........X.........................X..XXX...X......X...........X.X.X....XX.......XXX.X......X......X......X............................XX...X..X..........XX....X........X..X........XX.......XX.X..XX.................X...XX...............X.XXX.................X.....X........XX.....X...............X....X.X.....X.XX.XX.X...X...X........X..X..........X.X..XX.......X.....X..XX.......X......X.......XX.XX.X...X........XXXX.XXX.....X............XX.X........X.X...X............X.X..X..X.........XX.XX....X.X.......X.X..........X.X.X........X.......XX.X.........X......X...........X...........X...X.X...X.XX...XXX...X...X.............XX....X...X...X.X...X.........X...X......X.......X..X..X...............XXX..X..........X.......X...............X..XX.....X.XX.............X..........X.......X........X......X...XX.X...XX......................X...X.X...X.X....X...X.............XX.....XXX..................X.X.....X......X...XX........X.X.X.X.X.XX..X...X.X..........X.X...X..X..X...X.........X...X.........X......X.....X.........XX...XX....X..............X.XX..X....X....XX.X.........XXX..X....XXX..X.......X..........X...X..XX...X....X.........XX.......X...X.XXX...X.X......X...X.........X....X....XX.....X..X......X.X........X..X.X..XX...X..XX..X.X.XXXX...X..X......X.....X..X.............X...X....X.....X......X...X.XX...X.X...X..X..................X.............XX...........X........X......X...XX....X..X....X....X........X.X..X......X.....X..X...X...X.X...X.X....................X.......XX..X......X.......XX...X...X..XX..........X.X..X......X.....XXXX.......X..X......X.....X.X........X.X..............X.X.......X....XX.....X..X.X.X...&FVinsMax=27&FVinsMin=16&FVboardX=45&FVboardY=45&FVlevel=84\"><param name=\"allowScriptAccess\" value=\"sameDomain\" /><param name=\"movie\" value=\"escape.swf\" /><param name=\"quality\" value=\"high\" /><param name=\"bgcolor\" value=\"#ffffff\" /><embed src=\"escape.swf\"FlashVars=\"FVterrainString=.........X.X.XX...X....XX....X...X.......X.....X....XX..XX...........XX..........X..........XX..X.............XX.X...X...X.X...X.X.XX....XX..................X......XX..XX.X.....X.X....XX.........X.....X.........X.....X.........X...X............X.XX..X...X.....X.....X..............X.X.....X.........X..........X..X.X.......X.X.XX.........X............X.........XX.X........XX..X......X.X..X..........X.........................X..XXX...X......X...........X.X.X....XX.......XXX.X......X......X......X............................XX...X..X..........XX....X........X..X........XX.......XX.X..XX.................X...XX...............X.XXX.................X.....X........XX.....X...............X....X.X.....X.XX.XX.X...X...X........X..X..........X.X..XX.......X.....X..XX.......X......X.......XX.XX.X...X........XXXX.XXX.....X............XX.X........X.X...X............X.X..X..X.........XX.XX....X.X.......X.X..........X.X.X........X.......XX.X.........X......X...........X...........X...X.X...X.XX...XXX...X...X.............XX....X...X...X.X...X.........X...X......X.......X..X..X...............XXX..X..........X.......X...............X..XX.....X.XX.............X..........X.......X........X......X...XX.X...XX......................X...X.X...X.X....X...X.............XX.....XXX..................X.X.....X......X...XX........X.X.X.X.X.XX..X...X.X..........X.X...X..X..X...X.........X...X.........X......X.....X.........XX...XX....X..............X.XX..X....X....XX.X.........XXX..X....XXX..X.......X..........X...X..XX...X....X.........XX.......X...X.XXX...X.X......X...X.........X....X....XX.....X..X......X.X........X..X.X..XX...X..XX..X.X.XXXX...X..X......X.....X..X.............X...X....X.....X......X...X.XX...X.X...X..X..................X.............XX...........X........X......X...XX....X..X....X....X........X.X..X......X.....X..X...X...X.X...X.X....................X.......XX..X......X.......XX...X...X..XX..........X.X..X......X.....XXXX.......X..X......X.....X.X........X.X..............X.X.......X....XX.....X..X.X.X...&FVinsMax=27&FVinsMin=16&FVboardX=45&FVboardY=45&FVlevel=84\" quality=\"high\" bgcolor=\"#ffffff\" width=\"660\" height=\"600\" name=\"escape\" align=\"middle\" allowScriptAccess=\"sameDomain\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" /></object><br><br><div style=\"clear:both\"><form action=index.php method=get><input type=\"text\" name=\"gotolevel\" size=\"3\" value=\"84\" /><input type=\"submit\" name=\"go\" value=\"Go To Level\" /></form></div></td><td><table border=0><tr><th colspan=3>High Scores</th></tr><tr><th>player</th><th>level</th><th>date</th></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=2\">bok</a></td><td><a href=\"/util/charthistory.php?userid=2&game=runaway\">513</td><td>07-05-07 22:37</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=387\">Captain Segfault</a></td><td><a href=\"/util/charthistory.php?userid=387&game=runaway\">513</td><td>07-05-09 01:51</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=377\">falcon2424</a></td><td><a href=\"/util/charthistory.php?userid=377&game=runaway\">513</td><td>07-05-10 04:13</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=369\">abes</a></td><td><a href=\"/util/charthistory.php?userid=369&game=runaway\">513</td><td>07-05-10 15:19</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=419\">zzzz</a></td><td><a href=\"/util/charthistory.php?userid=419&game=runaway\">513</td><td>07-05-21 00:40</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=498\">ShardFire</a></td><td><a href=\"/util/charthistory.php?userid=498&game=runaway\">513</td><td>07-06-04 16:29</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=507\">iceman</a></td><td><a href=\"/util/charthistory.php?userid=507&game=runaway\">513</td><td>07-06-07 11:52</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=636\">Edegroot</a></td><td><a href=\"/util/charthistory.php?userid=636&game=runaway\">513</td><td>07-06-18 13:15</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=658\">Takun</a></td><td><a href=\"/util/charthistory.php?userid=658&game=runaway\">513</td><td>07-06-23 07:44</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=554\">JamesCFraser</a></td><td><a href=\"/util/charthistory.php?userid=554&game=runaway\">513</td><td>07-06-30 12:57</td></tr><tr><td colspan=3><a href=\"?showhigh\">See all...</a></td></tr></table><br><table border=0><tr><th colspan=3>Weekly High Scores</th></tr><tr><th>player</th><th>level</th><th>date</th></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=29666\">eulerscheZahl</a></td><td><a href=\"/util/charthistory.php?userid=29666&game=runaway\">513</td><td>12-12-01 02:16</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=22118\">Shirosan</a></td><td><a href=\"/util/charthistory.php?userid=22118&game=runaway\">153</td><td>12-12-01 22:40</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=28926\">Chris92</a></td><td><a href=\"/util/charthistory.php?userid=28926&game=runaway\">84</td><td>12-11-27 13:10</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=21947\">Ingenious</a></td><td><a href=\"/util/charthistory.php?userid=21947&game=runaway\">31</td><td>12-11-27 08:15</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=13595\">zsolmanz</a></td><td><a href=\"/util/charthistory.php?userid=13595&game=runaway\">30</td><td>12-11-26 11:02</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=26552\">whxnwjq</a></td><td><a href=\"/util/charthistory.php?userid=26552&game=runaway\">25</td><td>12-11-26 07:13</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=15693\">young</a></td><td><a href=\"/util/charthistory.php?userid=15693&game=runaway\">22</td><td>12-11-30 01:37</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=29618\">pigtastegood</a></td><td><a href=\"/util/charthistory.php?userid=29618&game=runaway\">21</td><td>12-11-25 15:41</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=29702\">peterretep</a></td><td><a href=\"/util/charthistory.php?userid=29702&game=runaway\">21</td><td>12-12-02 02:39</td></tr><tr valign=center><td><a href=\"/forum/profile.php?mode=viewprofile&u=29648\">jkolocz</a></td><td><a href=\"/util/charthistory.php?userid=29648&game=runaway\">20</td><td>12-12-01 22:39</td></tr></table></td></tr></table></body></html>";
	
	@Test
	public void givenAUrlResponseWhenIParseItThenIExpectACompletePuzzleContainer() {
		RunawayRobotPuzzle aPuzzle = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
		assertEquals(2025, aPuzzle.getFullMap().length());
		assertEquals(84, aPuzzle.getLevel());
		assertEquals(45, aPuzzle.getMapSize());
		assertEquals(27, aPuzzle.getMaxInstructions());
		assertEquals(16, aPuzzle.getMinInstructions());
		assertEquals(45, aPuzzle.getMapArray().length);
	}
	
	@Test
	public void givenAMapRowAndAPositionICanKnowTheFirstRockToTheRightOfThePosition() {
		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
		int currentPosition = 15;
		int currentRow = 0;
		int firstRockPosition = problem.getRockOnRowAtPosition(currentRow, currentPosition);
		System.out.println("First rock position is: " + firstRockPosition);
		assertTrue(firstRockPosition > 0);
	}
	
	@Test
	public void givenARowAndAPositionICanKnowIfThePositionOnTheRowBeneathIsARock() {
		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
		int currentPosition = 11;
		int currentRow = 0;
		boolean rock = problem.isPositionUnderneathARock(currentRow, currentPosition);
		System.out.println(rock);
	}
	
	@Test
	public void givenARowAndAPositionICanFindTheFurtherestRightICanGoBeforeHittingARock() {
		RunawayRobotPuzzle problem = new RunawayRobotPuzzle(MOCK_URL_RESPONSE);
		int currentPosition = 0;
		int currentRow = 0;
		try {
			while(currentRow != problem.getMapSize() - 1 && currentPosition != problem.getMapSize() - 1) {
				//Go as far right as possible
				currentPosition = problem.getRockOnRowAtPosition(currentRow, currentPosition) - 1;
				//whats underneath
				while (problem.isPositionUnderneathARock(currentRow, currentPosition)) {
					currentPosition--;
				}
				problem.getMapArray()[currentRow] = problem.getMapArray()[currentRow].substring(0, currentPosition) + '>' + problem.getMapArray()[currentRow].substring(currentPosition+1);
				System.out.println(problem.getMapArray()[currentRow]);
				currentRow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Current row is: " + currentRow + "\n" + "Current position is: " + currentPosition);
	}
	
	public class RunawayRobotPuzzle {
		private String fullMap;
		private int mapSize;
		private int maxInstructions;
		private int minInstructions;
		private int level;
		private String[] mapArray;
		
		public RunawayRobotPuzzle(String problem) {
			this.parseProblem(problem);
		}
		
		public void parseProblem(String problem) {
			this.fullMap = this.getPuzzleParameter(problem, "FVterrainString=", "&");
			this.mapSize = Integer.parseInt(this.getPuzzleParameter(problem, "FVboardX=", "&"));
			this.maxInstructions = Integer.parseInt(this.getPuzzleParameter(problem, "FVinsMax=", "&"));
			this.minInstructions = Integer.parseInt(this.getPuzzleParameter(problem, "FVinsMin=", "&"));
			this.level = Integer.parseInt(this.getPuzzleParameter(problem, "FVlevel=", "\""));
			this.mapArray = this.fullMap.split("(?<=\\G.{" + this.mapSize + "})");
		}
		
		public boolean isPositionUnderneathARock(int row, int position) {
			String currentRowDef = this.getMapArray()[row];
			String underneathRowDef = this.getMapArray()[row + 1];
			//System.out.println("underneath row definition: " + underneathRowDef);
			return underneathRowDef.charAt(position) == 'X';
		}

		public int getRockOnRowAtPosition(int row, int position) {
			String rowDef = this.getMapArray()[row];
			//System.out.println("Row " + row + " definition: " + rowDef);
			int rockPosition = position;
			boolean found = false;
			while(found == false && rockPosition < this.mapSize) {
				if(rowDef.charAt(rockPosition) == 'X') {
					found = true;
				} else {
					rockPosition++;
				}
			}
			return rockPosition;
		}

		private String getPuzzleParameter(String inputLine, String parameterName, String delimiter) {
			int beginIndex = inputLine.indexOf(parameterName) + parameterName.length();
			int endIndex = inputLine.indexOf(delimiter, beginIndex);
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
