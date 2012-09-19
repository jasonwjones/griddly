package com.jasonwjones.griddly.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.action.CellPopulator;
import com.jasonwjones.griddly.action.CellWalker;
import com.jasonwjones.griddly.viewers.GridWindow;

/**
 * Basically by its nature the AutoSizingTreeGrid is mutable and growing.
 * 
 * @author Jason W. Jones
 * 
 * @param <E>
 */
public class AutoSizingTreeGrid<E> extends AbstractGrid<E> implements Grid<E> {

	private Map<CellPath, E> cells = new TreeMap<CellPath, E>();

	/** If no dimension specified, just cap to some arbitrary number */
	private final static int DEFAULT_LENGTH = 1024;

	private int rows;
	private int columns;

	private int minRow = Integer.MAX_VALUE;
	private int minColumn = Integer.MAX_VALUE;

	protected int maxRow;
	protected int maxColumn;

	public AutoSizingTreeGrid() {
		super(DEFAULT_LENGTH, DEFAULT_LENGTH);
	}

	/**
	 * In the case of the AutoSizingTreeGrid, we only take the rows and columns
	 * in to consideration as a hint to performance optimization.
	 * 
	 * @param rows the starting number of rows to hint about
	 * @param columns the starting number of columns to hint about
	 */
	public AutoSizingTreeGrid(int rows, int columns) {
		super(rows, columns);
		this.rows = rows;
		this.columns = columns;
	}

	public AutoSizingTreeGrid(int rows, int columns, CellPopulator<E> cellPopulator) {
		this(rows, columns);
		fillGrid(cellPopulator);
	}

	public AutoSizingTreeGrid(Grid<E> grid) {
		super(grid);
	}

	public void setCellData(int row, int col, E cellData) {
		CellPath cellPath = createCellPath(row, col);

		if (cellData == null) {
			if (cells.containsKey(cellPath)) {
				cells.remove(cellPath);
			}
		}

		if (row > maxRow) maxRow = row;
		if (row < minRow) minRow = row;

		if (col > maxColumn) maxColumn = col;
		if (col < minColumn) minColumn = col;

		cells.put(cellPath, cellData);
	}

	public E getCellData(int row, int col) {
		CellPath cellPath = createCellPath(row, col);
		if (cells.containsKey(cellPath)) {
			return (cells.get(cellPath));
		}
		return null;
	}

	/**
	 * Convenience helper method creates a CellPath. Down the road if
	 * performance concerns warrant, perhaps cache them for quicker lookup?
	 * 
	 * @param row the row
	 * @param column the column
	 * @return a new CellPath object with the given row and column
	 */
	private CellPath createCellPath(int row, int column) {
		return new CellPath(row, column);
	}

	/**
	 * Returns biggest row. Recall the difference between number of rows and row
	 * indices. E.g., if a grid has rows from 0 to 2 then the max row is 2 but
	 * the number of rows is 3.
	 */
	@Override
	public int getRows() {
		return rows;
	}

	/**
	 * See <code>getRows()</code> for a note about row/column count semantics.
	 */
	@Override
	public int getColumns() {
		return columns;
	}

	private class AutoSizingTreeGridIterator implements Iterator<E> {

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Iterator<E> cellIterator() {
		return new AutoSizingTreeGridIterator();
	}

	public void walkGrid(CellWalker<E> walker) {
		for (Entry<CellPath, E> entry : cells.entrySet()) {
			walker.hasCellData(entry.getKey().getRow(), entry.getKey().getColumn(), entry.getValue());
		}
	}

	/**
	 * Creates a 'cropped' version of this grid (by way of a GridWindow) that is
	 * bound by the lowest items in every dimension.
	 * 
	 * @return a cropped version of the grid
	 */
	public Grid<E> cropped() {
		return new GridWindow<E>(this, minRow, minColumn, maxRow, maxColumn);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
