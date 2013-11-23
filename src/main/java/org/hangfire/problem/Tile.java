package org.hangfire.problem;

public class Tile {

    //TODO Refactor to Enum
	public static final int EMPTY = 0;
	public static final int BLOCKED = 1;
	
	private int status;
	
	public Tile(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		if (this.status == 0) {
			return "" + Tile.EMPTY;
		} else {
			return "" + Tile.BLOCKED;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + status;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (status != other.status)
			return false;
		return true;
	}
}
