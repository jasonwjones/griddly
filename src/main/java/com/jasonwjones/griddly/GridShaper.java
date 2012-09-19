package com.jasonwjones.griddly;


/**
 * Not really a "shaper", more like a derived grid creator.
 * 
 * @author Jason W. Jones
 * 
 * @param <E>
 */
public interface GridShaper<E> {

	public Grid<E> removeColumn(int columnIndex);

	public Grid<E> removeRow(int rowIndex);
		
	/**
	 * "Crops a grid by taking off edges that are completely made of cells with
	 * null values
	 * 
	 * @return the "cropped" grid.
	 */
	public Grid<E> crop();
	
	public Grid<E> removeTopRows(int rowCount);
	
}