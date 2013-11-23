package org.hangfire.problem;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Problem {
	
	private String fullMap;
	private int mapSize;
	private int maxInstructions;
	private int minInstructions;
	private int level;
	private PuzzleMap puzzleMap;
	
	public Problem(String problemString) {
		this.setFullMap(this.getPuzzleParameter(problemString, "FVterrainString=", "&"));
		this.setMapSize(Integer.parseInt(this.getPuzzleParameter(problemString, "FVboardX=", "&")));
		this.setMaxInstructions(Integer.parseInt(this.getPuzzleParameter(problemString, "FVinsMax=", "&")));
		this.setMinInstructions(Integer.parseInt(this.getPuzzleParameter(problemString, "FVinsMin=", "&")));
		this.setLevel(Integer.parseInt(this.getPuzzleParameter(problemString, "FVlevel=", "\"")));
		this.puzzleMap = new PuzzleMap(this.getFullMap(), this.getMapSize());
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

	public PuzzleMap getPuzzleMap() {
		return puzzleMap;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
