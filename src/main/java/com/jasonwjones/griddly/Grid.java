package com.jasonwjones.griddly;

import java.util.Iterator;

import com.jasonwjones.griddly.action.CellWalker;
import com.jasonwjones.griddly.action.ConverterCellAction;
import com.jasonwjones.griddly.action.ResultCellAction;

/**
 * Interface for a Grid object. A grid has several invariants:
 * 
 * Must be at least 1x1: no dimension may be 0 or less than 0.
 * 
 * @author Jason W. Jones
 * 
 * @param <E>
 */
public interface Grid<E> extends Iterable<E> {

	/**
	 * Sets the data in the specified cell intersection
	 * 
	 * @param row the row
	 * @param col the column
	 * @param cellData the data to set
	 */
	public void setCellData(int row, int col, E cellData);

	/**
	 * Get the data in the specific cell
	 * 
	 * @param row the row of the cell to get
	 * @param col the column of the cell
	 * @return the data in the cell, null if none
	 */
	public E getCellData(int row, int col);

	/**
	 * Get the number of rows in the grid
	 * 
	 * @return the number of rows in the grid
	 */
	public int getRows();

	/**
	 * Get the number of columns in the grid
	 * 
	 * @return the number of columns in the grid
	 */
	public abstract int getColumns();

	/**
	 * Iterator for GridRows so we can foreach over the Grid itself
	 */
	public abstract Iterator<E> iterator();

	// TODO: rework with cell-replacer and new object. Make static?
	public Grid<E> copyGrid(ResultCellAction<E> delegate);

	/**
	 * Check if this Grid has the same size (rows/columns as given grid)
	 * 
	 * @param grid
	 * @return true if the grids have the same dimensions, false otherwise
	 */
	public boolean isSameSizeAs(Grid<E> grid);

	/**
	 * Create a plain Java two-dimensional array based on the grid contents.
	 * Note that a reference type must be passed in. This is the type of the
	 * class (E).
	 * 
	 * @param clazz the type of object currently being managed by the grid.
	 * @return the grid converted to a two-dimensional array
	 */
	public E[][] toArray(Class<?> clazz);

	/**
	 * Converts the grid from one type to another be using the provided
	 * converter interface.
	 * 
	 * @param converter
	 * @return the converted grid
	 */
	public <S> Grid<S> convert(ConverterCellAction<E, S> converter);

	/**
	 * Gets a GridShaper object -- a class that can generate new grids based on
	 * this grid. The default implementation is "dumb" and may provide poor
	 * performance on large grids. Grid subclasses may wish to subclass
	 * DefaultGridShaper or provide a class that implements GridShaper in order
	 * to provide better performance.
	 * 
	 * @return a GridShaper object that works with this grid
	 */
	public GridShaper<E> createGridShaper();

	/**
	 * Iterates over the cells in the grid. Not guaranteed to hit every single
	 * spot (i.e., the AutoSizingTreeGrid which stores its data in a sparse
	 * structure will only iterate over cells that exist).
	 * 
	 * @param walker the delegate to call for each cell
	 */
	public void walk(CellWalker<E> walker);

}