package com.jasonwjones.griddly.viewers;

import java.util.Iterator;

import com.jasonwjones.griddly.Grid;

public class TopBottomStackedGrid<E> extends StackedGrid<E> {

	private Grid<E> topGrid, bottomGrid;
	private int topHeight;
	
	public TopBottomStackedGrid(Grid<E> topGrid, Grid<E> bottomGrid) {
		if (topGrid.getColumns() != bottomGrid.getColumns()) {
			throw new IllegalArgumentException("Top/bottom stacked grids must have same width.");
		}
		this.topGrid = topGrid;
		this.bottomGrid = bottomGrid;
		topHeight = topGrid.getRows();
	}
	
	public E getCellData(int row, int col) {
		if (row < topHeight) {
			return topGrid.getCellData(row, col);
		}
		return bottomGrid.getCellData(row - topHeight, col);
	}
	
	public Grid<E> getTopGrid() {
		return topGrid;
	}
	
	public Grid<E> getBottomGrid() {
		return bottomGrid;
	}

	@Override
	public void setCellData(int row, int col, E cellData) {
		if (row < topHeight) {
			topGrid.setCellData(row, col, cellData);
		}
		bottomGrid.setCellData(row - topHeight, col, cellData);
	}

	@Override
	public int getRows() {
		return topGrid.getRows() + bottomGrid.getRows();
	}

	@Override
	public int getColumns() {
		return topGrid.getColumns();
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}
	
}
