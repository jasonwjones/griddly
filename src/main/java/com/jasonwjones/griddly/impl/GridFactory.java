package com.jasonwjones.griddly.impl;

import com.jasonwjones.griddly.Grid;

public class GridFactory {

	public <E> Grid<E> createGrid() {
		return new AutoSizingTreeGrid<E>();
	}
}
