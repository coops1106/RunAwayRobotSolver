package org.hangfire.problem;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

public class ProblemContainerTest {

	private ProblemContainer uut;

	@Test
	public void givenIWantToSolveRunAwayRobotPuzzleWhenINeedTheNextLevelProblemIGetOneFullyPopulated() {
		uut = new ProblemContainer(new ProblemContainerRepositoryStub());
		assertNotNull(uut);
		assertEquals("Current problem level should be 84", 84, uut.getLevel());
		assertEquals("Current problem mapsize should be 45", 45, uut.getMapSize());
		assertEquals("Current problem max instructions should be 27", 27, uut.getMaxInstructions());
		assertEquals("Current problem max instructions should be 16", 16, uut.getMinInstructions());
		
		ArrayList<RowOfTiles> map = new ArrayList<RowOfTiles>(45);
		
		for (int rowIndex = 0; rowIndex < 45; rowIndex++) {
			RowOfTiles aRow = new RowOfTiles();
			for (int columnIndex = 0; columnIndex < 45; columnIndex++) {
				int position = (rowIndex * 45) + (columnIndex);
				Tile aTile;
				if(uut.getFullMap().charAt(position) == 'X') {
					aTile = new Tile(Tile.BLOCKED);
				} else {
					aTile = new Tile(Tile.EMPTY);
				}
				aRow.addTile(aTile);
			}
			map.add(aRow);
		}
		
		assertTrue("Current problem should contain a map that is correct", uut.getMap().containsAll(map));
		
	}
}

