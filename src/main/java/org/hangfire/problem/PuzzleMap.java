package org.hangfire.problem;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class PuzzleMap {
	
	private List<RowOfTiles> rows;


    //TODO Refactor to factory
	public PuzzleMap(String fullMap, int mapSize) {
		rows = new ArrayList<RowOfTiles>(mapSize);
		for (int rowIndex = 0; rowIndex < mapSize; rowIndex++) {
			RowOfTiles aRow = new RowOfTiles();
			for (int columnIndex = 0; columnIndex < mapSize; columnIndex++) {
				int position = (rowIndex * mapSize) + (columnIndex);
				Tile aTile;
				if(fullMap.charAt(position) == 'X') {
					aTile = new Tile(Tile.BLOCKED);
				} else {
					aTile = new Tile(Tile.EMPTY);
				}
				aRow.addTile(aTile);
			}
			rows.add(aRow);
		}
	}

    public int getSize() {
        return this.rows.size();
    }

    public Tile getTileAt(int column, int row) {
        return this.rows.get(row).getTiles().get(column);
    }

	public class RowOfTiles {
		private List<Tile> tiles;
		
		public void addTile(Tile aTile) {
			this.getTiles().add(aTile);
		}
		
		public List<Tile> getTiles() {
			if (this.tiles ==  null) {
				this.tiles = new ArrayList<Tile>();
			}
			return this.tiles;
		}

		@Override
		public String toString() {
			return tiles + "\n";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RowOfTiles other = (RowOfTiles) obj;
			if (tiles == null) {
				if (other.tiles != null)
					return false;
			} else if (!tiles.equals(other.tiles))
				return false;
			return true;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleMap other = (PuzzleMap) obj;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
