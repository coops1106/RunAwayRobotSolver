package org.hangfire.problem;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class PuzzleMap {
	
    private boolean[][] map;

    public PuzzleMap() {
    }

    public PuzzleMap(final boolean[][] map) {
        this.map = map;
    }

    public PuzzleMap(String fullMap, int mapSize) {
		map = new boolean[mapSize][mapSize];
		for (int rowIndex = 0; rowIndex < mapSize; rowIndex++) {
			for (int columnIndex = 0; columnIndex < mapSize; columnIndex++) {
				int position = (rowIndex * mapSize) + (columnIndex);
				if(fullMap.charAt(position) == 'X') {
					map[columnIndex][rowIndex] = true;
                }
			}
		}
	}

    public int getSize() {
        return this.map.length;
    }

    public boolean isBoomAt(int x, int y) {
        return this.map[x][y];
    }

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
