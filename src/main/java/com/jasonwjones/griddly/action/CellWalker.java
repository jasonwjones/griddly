package com.jasonwjones.griddly.action;


public interface CellWalker<E> {
	
	public enum WalkMethod {
		NORMAL,
		SKIPNULL
	}
	
	public void hasCellData(int row, int column, E data);
}
