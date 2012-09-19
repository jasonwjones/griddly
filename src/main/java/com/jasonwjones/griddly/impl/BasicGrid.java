package com.jasonwjones.griddly.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.action.CellPopulator;
import com.jasonwjones.griddly.action.CellWalker;

/**
 * The BasicGrid is the simplest implementation of a Grid and allows for
 * creating a Grid of fixed size at creation time. Data is stored using a List
 * of List approach.
 * 
 * @author Jason W. Jones
 * 
 * @param <E>
 */
public class BasicGrid<E> extends AbstractGrid<E> implements Grid<E> {

	/** By default, the grid stores its data as a List of GridRow */
	protected List<BasicGridRow<E>> gridRows;

	/** The grid tracks its size irrespective of the array object */
	protected int rows, columns;

	public BasicGrid(int rows, int cols) {
		super(rows, cols);

		this.rows = rows;
		this.columns = cols;

		gridRows = new ArrayList<BasicGridRow<E>>(rows);
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			BasicGridRow<E> newGridRow = new BasicGridRow<E>(cols);
			gridRows.add(newGridRow);
		}
	}

	public BasicGrid(Grid<E> grid) {
		this(grid.getRows(), grid.getColumns());
		grid.walk(new CellWalker<E>(){
			public void hasCellData(int row, int column, E data) {
				setCellData(row, column, data);
			}
		});
	}

	public BasicGrid(int rows, int columns, CellPopulator<E> cellPopulator) {
		this(rows, columns);
		fillGrid(cellPopulator);
	}
	
	public void setCellData(int row, int col, E cellData) {
		gridRows.get(row).getCell(col).setData(cellData);
	}

	/**
	 * Somewhat of a vestigial structure. Keeping around for now in case it
	 * becomes useful/necessary to get at the wrapper.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	protected GridCell<E> getCell(int row, int column) {
		if (row < this.getRows() && column < this.getColumns()) {
			return gridRows.get(row).getCell(column);
		} else {
			return null;
		}
	}

	public int getRows() {
		return rows;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.jasonwjones.griddly.Grid#getColumns() */
	public int getColumns() {
		return columns;
	}

	public Iterator<E> iterator() {
		return new BasicGridIterator();
	}

	/*
	private class BasicGridRowIterator implements Iterator<GridRow<E>> {

		int cursor = 0;

		public boolean hasNext() {
			return cursor != gridRows.size();
		}

		public GridRow<E> next() {
			return (GridRow<E>) gridRows.get(cursor++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	*/

	private class BasicGridIterator implements Iterator<E> {

		int rowIndex = 0;
		int colIndex = 0;

		public boolean hasNext() {
			return rowIndex < gridRows.size();
		}

		public E next() {
			E data = gridRows.get(rowIndex).getCell(colIndex++).getData();
			if (colIndex >= gridRows.get(rowIndex).getColumns()) {
				rowIndex++;
				colIndex = 0;
			}
			return data;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	
	@Override
	public E getCellData(int row, int col) {
		return gridRows.get(row).getCell(col).getData();
	}

}
