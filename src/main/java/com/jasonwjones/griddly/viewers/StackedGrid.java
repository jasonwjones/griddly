package com.jasonwjones.griddly.viewers;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.impl.AbstractGrid;

/**
 * Lightweight translation object for stitching two grids together. Warning:
 * should not be used with a grid whose rows can change! (e.g.
 * AutoSizingTreeGrid).
 * 
 * @author Jason W. Jones
 * 
 * @param <E>
 */
public abstract class StackedGrid<E> extends AbstractGrid<E> implements Grid<E> {

	/**
	 * Attempt to automatically determine way to stitch grids based on a size
	 * that matches up.
	 * 
	 * @param firstGrid
	 * @param secondGrid
	 * @return
	 */
	public static <E> StackedGrid<E> stitch(Grid<E> firstGrid, Grid<E> secondGrid) {
		return null;
	}

	public static <E> StackedGrid<E> createTopBottom(Grid<E> topGrid, Grid<E> bottomGrid) {
		return null;
	}

}
