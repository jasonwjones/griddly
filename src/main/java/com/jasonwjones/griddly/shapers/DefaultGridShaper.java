package com.jasonwjones.griddly.shapers;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.GridShaper;
import com.jasonwjones.griddly.viewers.GridWindow;

public class DefaultGridShaper<E> implements GridShaper<E> {

	private Grid<E> grid;
	
	public DefaultGridShaper(Grid<E> grid) {
		this.grid = grid;
	}
	
	public Grid<E> getLeftColumns(int columnCount) {
		if (columnCount < 0) throw new IllegalArgumentException("Column count cannot be negative.");
		if (columnCount > grid.getColumns()) return grid;
		return copySubgrid(0, 0, grid.getRows() - 1, columnCount);
	}
	
	public Grid<E> copySubgrid(int startRow, int startCol, int endRow, int endCol) {
		return null;
	}
	
	public Grid<E> removeColumn(int columnIndex) {
		return null;
	}

	public Grid<E> crop() {
		return null;
	}

	public Grid<E> removeRow(int rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Grid<E> removeTopRows(int rowCount) {
		return new GridWindow<E>(grid, rowCount, 0, grid.getRows(), grid.getColumns());
	}

}
