package org.hangfire.problem;

import java.util.ArrayList;
import java.util.List;

import org.hangfire.utils.UrlUtils;

public class ProblemContainer {
	
	private String problemString;
	private String fullMap;
	private int mapSize;
	private int maxInstructions;
	private int minInstructions;
	private int level;
	private List<RowOfTiles> map = new ArrayList<RowOfTiles>();
	
	public ProblemContainer(ProblemContainerRepository problemContainerRepository) {
		this.problemString = problemContainerRepository.fetchCurrentProblem();
		this.parseProblem();
	}
	
	private void parseProblem() {
		
		this.setFullMap(this.getPuzzleParameter(this.getProblemString(), "FVterrainString=", "&"));
		this.setMapSize(Integer.parseInt(this.getPuzzleParameter(this.getProblemString(), "FVboardX=", "&")));
		this.setMaxInstructions(Integer.parseInt(this.getPuzzleParameter(this.getProblemString(), "FVinsMax=", "&")));
		this.setMinInstructions(Integer.parseInt(this.getPuzzleParameter(this.getProblemString(), "FVinsMin=", "&")));
		this.setLevel(Integer.parseInt(this.getPuzzleParameter(this.getProblemString(), "FVlevel=", "\"")));
		
		for (int rowIndex = 0; rowIndex < this.getMapSize(); rowIndex++) {
			RowOfTiles aRow = new RowOfTiles();
			for (int columnIndex = 0; columnIndex < this.getMapSize(); columnIndex++) {
				int position = (rowIndex * this.getMapSize()) + (columnIndex);
				Tile aTile;
				if(this.getFullMap().charAt(position) == 'X') {
					aTile = new Tile(Tile.BLOCKED);
				} else {
					aTile = new Tile(Tile.EMPTY);
				}
				aRow.addTile(aTile);
			}
			map.add(aRow);
		}
	}
	
	private String getPuzzleParameter(String inputLine, String parameterName, String delimiter) {
		int beginIndex = inputLine.indexOf(parameterName) + parameterName.length();
		int endIndex = inputLine.indexOf(delimiter, beginIndex);
		return inputLine.substring(beginIndex, endIndex);
	}

	public String getProblemString() {
		return problemString;
	}

	public void setProblemString(String problemString) {
		this.problemString = problemString;
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

	public List<RowOfTiles> getMap() {
		return map;
	}

	public void setMap(List<RowOfTiles> map) {
		this.map = map;
	}

	
}
