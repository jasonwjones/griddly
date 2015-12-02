package com.jasonwjones.griddly.shapers;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.GridShaper;
import com.jasonwjones.griddly.viewers.TopBottomStackedGrid;

public class BasicGridShaper<E> extends AbstractGridShaper<E> implements GridShaper<E> {

	public Grid<E> removeRow(int rowIndex) {
		return null;
	}

	/**
	 * Given two grids of equal width, creates a new grid of both
	 * 
	 * @param topGrid the top grid
	 * @param bottomGrid the bottom grid
	 * @return the new grid object
	 */
	public Grid<E> stackGrids(Grid<E> topGrid, Grid<E> bottomGrid) {
		return new TopBottomStackedGrid<E>(topGrid, bottomGrid);
	}

}
