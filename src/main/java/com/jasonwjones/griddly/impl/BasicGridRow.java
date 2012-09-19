package com.jasonwjones.griddly.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * TODO: Inline this inside default grid?
 * @author Jason W. Jones
 *
 * @param <E>
 */
public class BasicGridRow<E> implements Iterable<E>, GridRow<E> {

	protected List<GridCell<E>> cells;
	
	public BasicGridRow(int columns) {
		cells = new ArrayList<GridCell<E>>(columns);
		for (int index = 0; index < columns; index++) {
			GridCell<E> cell = new GridCell<E>();
			cells.add(cell);
		}
	}
	
	public BasicGridRow(GridRow<E> gridRow) {
		cells = new ArrayList<GridCell<E>>();
		for (E cell : gridRow) {
			cells.add(new GridCell<E>(cell));
		}
	}
	
	public GridCell<E> getCell(int columnIndex) {
		return cells.get(columnIndex);
	}
	
	public void setCell(int columnIndex, GridCell<E> cell) {
		cells.set(columnIndex, cell);
	}
	
	public void deleteCell(int columnIndex) {
		cells.remove(columnIndex);
	}
	
	public int getColumns() {
		return cells.size();
	}

	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = 0;
			public boolean hasNext() {
				return index < cells.size();
			}

			public E next() {
				return cells.get(index++).getData();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
