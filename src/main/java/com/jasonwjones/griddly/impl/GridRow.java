package com.jasonwjones.griddly.impl;

import java.util.Iterator;


public interface GridRow<E> extends Iterable<E> {

	public GridCell<E> getCell(int columnIndex);

	public void setCell(int columnIndex, GridCell<E> cell);

	public int getColumns();

	public Iterator<E> iterator();

}