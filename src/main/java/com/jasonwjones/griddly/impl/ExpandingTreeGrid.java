package com.jasonwjones.griddly.impl;

public class ExpandingTreeGrid<E> extends AutoSizingTreeGrid<E> {

	@Override
	public int getRows() {
		return maxRow + 1;
	}

	@Override
	public int getColumns() {
		return maxColumn + 1;
	}

}
