package com.jasonwjones.griddly.impl;

/**
 * A GridCell is the lowest-granularity object in a Grid (other than the data
 * itself). It is a generic container. 
 * 
 * A GridCell is entirely allowed to contain nothing (null)!
 * 
 * Possible todo: inline with BasicGrid?
 * 
 * @author jasonwjones
 *
 * @param <E>
 */
public class GridCell<E> {
	
	protected E cellData = null;
	
	/**
	 * Construct with no data (null)
	 */
	public GridCell() {
	}
	
	/**
	 * This is not a copy of the data! This new GridCell will just have a 
	 * reference to the same piece of data!
	 * @param gridCell
	 */
	public GridCell(GridCell<E> gridCell) {
		cellData = gridCell.cellData;
	}
	
	public boolean hasData() {
		return cellData != null;
	}
	
	public GridCell(E cellData) {
		this.cellData = cellData;
	}

	public E getData() {
		return cellData;
	}
	
	public void setData(E cellData) {
		this.cellData = cellData;
	}
	
}
