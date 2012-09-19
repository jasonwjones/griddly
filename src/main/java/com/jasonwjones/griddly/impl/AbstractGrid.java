package com.jasonwjones.griddly.impl;

import java.lang.reflect.Array;
import java.util.Iterator;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.GridShaper;
import com.jasonwjones.griddly.action.CellAction;
import com.jasonwjones.griddly.action.CellPopulator;
import com.jasonwjones.griddly.action.CellWalker;
import com.jasonwjones.griddly.action.ConverterCellAction;
import com.jasonwjones.griddly.action.ResultCellAction;
import com.jasonwjones.griddly.shapers.DefaultGridShaper;

/**
 * Generic grid class allows for creating and managing a Grid that can hold any
 * object. Subclasses should implement Grid<E> in their interface as well as
 * here. It is provided here in order to use itself as an argument to
 * DefaultGridShaper among other things.
 * 
 * @author jasonwjones
 * 
 * @param <E> the type of object that the Grid will store.
 */
public abstract class AbstractGrid<E> implements Iterable<E>, Grid<E> {

	public AbstractGrid() {

	}

	/**
	 * Designated constructor to create a Grid
	 * 
	 * @param rows the rows in the grid
	 * @param cols the columns in the grid
	 */
	public AbstractGrid(int rows, int cols) {
		if (rows < 1 || cols < 1) {
			throw new IllegalArgumentException("A grid may not have a dimension of size 0 or less.");
		}
	}

	protected void fillGrid(CellPopulator<E> cellPopulator) {
		for (int row = 0; row < getRows(); row++) {
			for (int column = 0; column < getColumns(); column++) {
				setCellData(row, column, cellPopulator.createValue(row, column));
			}
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param grid
	 */
	public AbstractGrid(Grid<E> grid) {
	}

	public abstract void setCellData(int row, int col, E cellData);

	// protected abstract GridCell<E> getCell(int row, int column);

	public abstract E getCellData(int row, int col);

	/**
	 * Delete a row from the grid
	 * 
	 * @param rowIndex the index of the row to delete
	 */
	// public void deleteRow(int rowIndex) {
	// gridRows.remove(rowIndex);
	// rows--;
	// }

	public abstract int getRows();

	public abstract int getColumns();

	/**
	 * Remove a column from the Grid
	 * 
	 * @param colIndex
	 */
	// public void deleteColumn(int colIndex) {
	// for (GridRow<E> currentRow : gridRows) {
	// currentRow.deleteCell(colIndex);
	// }
	// columns--;
	// }

	/**
	 * Prints the contents of the grid
	 * 
	 * @param cellWidth the width of the text cell to use to print
	 * @return the Grid printed out
	 */
	public String toString(int cellWidth) {
		String template = "[%" + cellWidth + "s]";
		StringBuilder sb = new StringBuilder();

		for (E data : this) {
			sb.append(String.format(template, data != null ? data.toString() : ""));
			sb.append(String.format("%n"));
		}
		return sb.toString();
	}

	public abstract Iterator<E> iterator();

	/**
	 * Update the data in all cells that contain data by calling the provided
	 * CellAction object with the data
	 * 
	 * @param delegate
	 */
	public void updateCells(CellAction<E> delegate) {
		for (E gridCell : this) {
			if (gridCell != null) {
				delegate.modifyCell(gridCell);
			}
		}
	}

	/**
	 * Replace the data in all the cells that have data by calling the provided
	 * ResultCellAction delegate. ResultCellAction must return the original
	 * object if it doesn't want to make any changes.
	 * 
	 * @param delegate
	 */
	public void replaceCells(ResultCellAction<E> delegate) {
		for (E gridCell : this) {
			if (gridCell != null) {
				// FIXME
				// gridCell.setData(delegate.resultAction(gridCell));
			}
		}
	}

	// TODO: rework with cell-replacer and new object. Make static?
	/* (non-Javadoc)
	 * 
	 * @see com.jasonwjones.griddly.Grid#copyGrid(com.jasonwjones.griddly.
	 * ResultCellAction) */
	public Grid<E> copyGrid(ResultCellAction<E> delegate) {

		int rows = getRows();
		int columns = getColumns();

		Grid<E> resultGrid = new BasicGrid<E>(rows, columns);

		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < columns; colIndex++) {
				E gridCell = getCellData(rowIndex, colIndex);
				if (gridCell != null) {
					resultGrid.setCellData(rowIndex, colIndex, delegate.resultAction(gridCell));
				}
			}
		}
		return resultGrid;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.jasonwjones.griddly.Grid#convert(com.jasonwjones.griddly.
	 * ConverterCellAction) */
	public <S> Grid<S> convert(ConverterCellAction<E, S> converter) {
		int rows = getRows();
		int columns = getColumns();

		Grid<S> resultGrid = new BasicGrid<S>(rows, columns);

		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < columns; colIndex++) {
				E gridCell = getCellData(rowIndex, colIndex);
				if (gridCell != null) {
					resultGrid.setCellData(rowIndex, colIndex, converter.convert(gridCell));
				}
			}
		}
		return resultGrid;
	}

	/**
	 * Converts the grid to a normal 2D array. Note that this is the "default"
	 * implementation and just leans on the ability to pull out cells one by
	 * one. Custom implementations may want to override this method in order to
	 * achieve better performance.
	 * 
	 * @return a two-dimensional array with the contents of the grid
	 */
	@SuppressWarnings("unchecked")
	public E[][] toArray(Class<?> clazz) {
		E[][] arrayGrid = (E[][]) Array.newInstance(clazz, getRows(), getColumns());
		for (int rowIndex = 0; rowIndex < getRows(); rowIndex++) {
			for (int colIndex = 0; colIndex < getColumns(); colIndex++) {
				arrayGrid[rowIndex][colIndex] = getCellData(rowIndex, colIndex);
			}
		}
		return arrayGrid;
	}

	/**
	 * Check size of grid against another grid.
	 * 
	 * @param grid the grid to check against
	 * @return true if the grid reports the same width and height (rows/columns)
	 *         as this grid, false otherwise.
	 */
	public boolean isSameSizeAs(Grid<E> grid) {
		return grid.getRows() == getRows() && grid.getColumns() == getColumns();
	}

	public GridShaper<E> createGridShaper() {
		return new DefaultGridShaper<E>(this);
	}

	/**
	 * Walks the grid and calls the delegate with every single cell data. Cells
	 * may be empty (null).
	 * 
	 * @param walker
	 */
	public void walk(CellWalker<E> walker) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getColumns(); col++) {
				walker.hasCellData(row, col, getCellData(row, col));
			}
		}
	}
	
//	public Grid<E> copyGrid() {
//		
//	}
//	
//	protected void manualCopy() {
//		
//	}

	@Override
	public String toString() {
		return String.format("<Grid: dimensions=%dx%d>", getRows(), getColumns());
	}
}
