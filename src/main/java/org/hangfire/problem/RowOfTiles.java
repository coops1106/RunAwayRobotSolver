package org.hangfire.problem;

import java.util.ArrayList;
import java.util.List;

public class RowOfTiles {
	
	private List<Tile> tiles;
	
	public void addTile(Tile aTile) {
		this.getTiles().add(aTile);
	}
	
	private List<Tile> getTiles() {
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
