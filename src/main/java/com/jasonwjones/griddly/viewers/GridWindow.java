package com.jasonwjones.griddly.viewers;

import java.util.Iterator;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.action.CellWalker;
import com.jasonwjones.griddly.impl.AbstractGrid;

/**
 * A Grid<E> implementing class which, given another grid and a 'frame' will act
 * as a window into that grid without actually copying the original grid.
 * 
 * Original Grid:
 * 
 * +-----+-----+-----+-----+-----+
 * | 0,0 | 0,1 | 0,2 | 0,3 | 0,4 |
 * +-----+-----+-----+-----+-----+
 * |     |     |     |     |     |
 * +-----+-----+-----+-----+-----+
 * |     | 2,1 | 2,2 | 2,3 | 2,4 |
 * +-----+-----+-----+-----+-----+
 * |     |     |     |     |     |
 * +-----+-----+-----+-----+-----+
 * 
 * @author Jason W. Jones
 * 
 * @param <E> the type of the original grid
 */
public class GridWindow<E> extends AbstractGrid<E> implements Grid<E> {

	public enum SpanDirection {
		ABOVE_LEFT, ABOVE_RIGHT, BELOW_LEFT, BELOW_RIGHT
	}
	
	/** Source grid that this Window is based on */
	private Grid<E> grid;
	
	/** Offsets in to source grid */
	private int rowStart, colStart, rowEnd, colEnd;

	/** 
	 * Just creates a GridWindow with the same dimensions as the source grid
	 * @param grid
	 */
	public GridWindow(Grid<E> grid) {
		this(grid, 0, 0, grid.getRows(), grid.getColumns());
	}
	
	public GridWindow(Grid<E> grid, int rowStart, int colStart, SpanDirection spanDirection) {
		
	}
	
	/**
	 * TODO: Auto flip coords?
	 * @param grid
	 * @param rowStart
	 * @param colStart
	 * @param rowEnd
	 * @param colEnd
	 */
	public GridWindow(Grid<E> grid, int rowStart, int colStart, int rowEnd, int colEnd) {
		if (rowEnd <= rowStart || colEnd <= colStart) {
			throw new IllegalArgumentException("Row/col ends must be greater than start.");
		}
		this.grid = grid;
		this.rowStart = rowStart;
		this.colStart = colStart;
		this.rowEnd = rowEnd;
		this.colEnd = colEnd;
	}

	private int adjustedRow(int row) {
		return row + rowStart;
	}
	
	private int adjustedColumn(int column) {
		return column + colStart;
	}
	
	@Override
	public void setCellData(int row, int col, E cellData) {
		grid.setCellData(adjustedRow(row), adjustedColumn(col), cellData);
	}

	@Override
	public int getRows() {
		return rowEnd - rowStart + 1;
	}

	@Override
	public int getColumns() {
		return colEnd - colStart + 1;
	}

	/**
	 * TODO: FIX to only iterate over elements in subwindow
	 */
	public Iterator<E> iterator() {
		return grid.iterator();
	}

	@Override
	public void walk(CellWalker<E> walker) {
		for (int row = rowStart; row <= rowEnd; row++) {
			for (int col = colStart; col <= colEnd; col++) {
				walker.hasCellData(row - rowStart, col - colStart, grid.getCellData(row, col));
			}
		}	
	}
	
	@Override
	public E getCellData(int row, int col) {
		return grid.getCellData(adjustedRow(row), adjustedColumn(col));
	}

}
