package com.jasonwjones.griddly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generic grid class allows for creating and managing a Grid that can hold
 * any object.
 * @author jasonwjones
 *
 * @param <E> the type of object that the Grid will store. 
 */
public class Grid<E> implements Iterable<GridRow<E>> {

	protected List<GridRow<E>> gridRows;
	protected int rows, columns;
	
	/**
	 * Designated constructor to create a Grid
	 * @param rows the rows in the grid
	 * @param cols the columns in the grid
	 */
	public Grid(int rows, int cols) {
		
		this.rows = rows;
		this.columns = cols;
		
		gridRows = new ArrayList<GridRow<E>>(rows);
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			GridRow<E> newGridRow = new GridRow<E>(cols);
			gridRows.add(newGridRow);
		}
	}
	
	/**
	 * Copy constructor
	 * @param grid
	 */
	public Grid(Grid<E> grid) {
		gridRows = new ArrayList<GridRow<E>>();
		for (GridRow<E> gridRow : grid) {
			gridRows.add(new GridRow<E>(gridRow));
		}
	}
		
	/**
	 * Sets the data in the specified cell intersection
	 * @param row the row
	 * @param col the column
	 * @param cellData the data to set
	 */
	public void setCellData(int row, int col, E cellData) {
		gridRows.get(row).getCell(col).setData(cellData);
	}
	
	/**
	 * Get the GridCell at the specified intersection
	 * @param row the row of the cell to get
	 * @param column the column of the cell to get
	 * @return the GridCell at the specified intersection
	 */
	public GridCell<E> getCell(int row, int column) {
		if (row < this.getRows() && column < this.getColumns()) {
			return gridRows.get(row).getCell(column);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the data in the specific cell
	 * @param row the row of the cell to get
	 * @param col the column of the cell
	 * @return the data in the cell, null if none
	 */
	public E getCellData(int row, int col) {
		// TODO: use getCell to simplify this
		if (row < this.getRows() && col < this.getColumns()) {
			return gridRows.get(row).getCell(col).getData();
		} else {
			return null;
		}
	}
	
	/**
	 * Delete a row from the grid
	 * @param rowIndex the index of the row to delete
	 */
	public void deleteRow(int rowIndex) {
		gridRows.remove(rowIndex);
		rows--;
	}
	
	/**
	 * Get the number of rows in the grid
	 * @return the number of rows in the grid
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Get the number of columns in the grid
	 * @return the number of columns in the grid
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Remove a column from the Grid
	 * @param colIndex
	 */
	public void deleteColumn(int colIndex) {
		for (GridRow<E> currentRow : gridRows) {
			currentRow.deleteCell(colIndex);
		}
		columns--;
	}
	
	/**
	 * Prints the contents of the grid 
	 * @param cellWidth the width of the text cell to use to print
	 * @return the Grid printed out
	 */
	public String toString(int cellWidth) {
		String template = "[%" + cellWidth + "s]";
		StringBuilder sb = new StringBuilder();
		
		for (GridRow<E> gridRow : this) {
			for (GridCell<E> gridCell : gridRow) {
				E data = gridCell.getData();
				sb.append(String.format(template, data != null ? data.toString() : ""));
			}
			sb.append(String.format("%n"));
		}
		return sb.toString();
	}

	/** 
	 * Iterator for GridRows so we can foreach over the Grid itself
	 */
	public Iterator<GridRow<E>> iterator() {
		return gridRows.iterator();
	}
	
	/**
	 * Update the data in all cells that contain data by calling the provided
	 * CellAction object with the data
	 * @param delegate
	 */
	public void updateCells(CellAction<E> delegate) {
		for (GridRow<E> gridRow : this) {
			for (GridCell<E> gridCell : gridRow) {
				if (gridCell.hasData()) {
					delegate.modifyCell(gridCell.getData());
				}
			}
		}
	}

	/**
	 * Replace the data in all the cells that have data by calling the provided
	 * ResultCellAction delegate. ResultCellAction must return the original 
	 * object if it doesn't want to make any changes.
	 * @param delegate
	 */
	public void replaceCells(ResultCellAction<E> delegate) {
		for (GridRow<E> gridRow : this) {
			for (GridCell<E> gridCell : gridRow) {
				if (gridCell.hasData()) {
					gridCell.setData(delegate.resultAction(gridCell.getData()));
				}
			}
		}
	}


	// TODO: rework with cell-replacer and new object. Make static?
	public Grid<E> copyGrid(ResultCellAction<E> delegate) {

		Grid<E> resultGrid = new Grid<E>(rows, columns);
		
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < columns; colIndex++) {
				GridCell<E> gridCell = getCell(rowIndex, colIndex);
				if (gridCell.hasData()) {
					resultGrid.setCellData(rowIndex, colIndex, delegate.resultAction(gridCell.getData()));	
				}
			}
		}
		return resultGrid;
	}
	
	/**
	 * Converts the grid from one type to another be using the provided
	 * converter interface.
	 * @param converter
	 * @return the converted grid
	 */
	public <S> Grid<S> convert(ConverterCellAction<E, S> converter) {
		Grid<S> resultGrid = new Grid<S>(rows, columns);
		
		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < columns; colIndex++) {
				GridCell<E> gridCell = (GridCell<E>) getCell(rowIndex, colIndex);
				if (gridCell.hasData()) {
					resultGrid.setCellData(rowIndex, colIndex, converter.convert(gridCell.getData()));	
				}
			}
		}
		return resultGrid;
	}
	
// Can't really seem to implement this without an unchecked cast:
// http://stackoverflow.com/questions/3617863/java-convert-generic-linkedlist-to-generic-array
//	public E[][] toArray() {
//		E[][] arrayGrid = (E[][]) new Object[getRows()][getColumns()];
//		return arrayGrid;
//	}
	
	@Override
	public String toString() {
		return String.format("Grid (%d x %d)", rows, columns);
	}
}
