package com.jasonwjones.griddly.testgrids;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.impl.BasicGrid;

public class TestGridFactory<E> {

	public interface GridCreationDelegate<E> {

		Grid<E> createGrid(int rows, int columns);

		E createValue(int row, int column);
	}

	public static <E> Grid<E> createGrid(int rows, int columns, GridCreationDelegate<E> delegate) {
		Grid<E> grid = delegate.createGrid(rows, columns);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				grid.setCellData(row, col, delegate.createValue(row, col));
			}
		}
		return grid;
	}

	/**
	 * Returns a simple Grid with string values for the form axb where a is the
	 * row and b is the column.
	 * 
	 * @param rows the number of rows for the grid to be created
	 * @param columns the number of columns for the grid to be created
	 * @return a new Grid of String
	 */
	public static Grid<String> createStringGrid(int rows, int columns) {
		return createGrid(rows, columns, new TestGridFactory.GridCreationDelegate<String>() {

			public Grid<String> createGrid(int rows, int columns) {
				return new BasicGrid<String>(rows, columns);
			}

			public String createValue(int row, int column) {
				return String.format("%dx%d", row, column);
			}
		});
	}

}
