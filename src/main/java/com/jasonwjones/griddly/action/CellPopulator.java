package com.jasonwjones.griddly.action;

public interface CellPopulator<E> {
	public E createValue(int row, int column);
}
