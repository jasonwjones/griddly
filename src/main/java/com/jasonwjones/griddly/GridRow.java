package com.jasonwjones.griddly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridRow<E> implements Iterable<GridCell<E>> {

	protected List<GridCell<E>> cells;
	
	public GridRow(int columns) {
		cells = new ArrayList<GridCell<E>>(columns);
		for (int index = 0; index < columns; index++) {
			GridCell<E> cell = new GridCell<E>();
			cells.add(cell);
		}
	}
	
	public GridRow(GridRow<E> gridRow) {
		cells = new ArrayList<GridCell<E>>();
		for (GridCell<E> cell : gridRow) {
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

	public Iterator<GridCell<E>> iterator() {
		return cells.iterator();
	}

}
