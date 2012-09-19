package com.jasonwjones.griddly.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.GridShaper;
import com.jasonwjones.griddly.action.CellPopulator;
import com.jasonwjones.griddly.action.CellWalker;


public class BasicGridTest {

	private final int rows = 4;
	private final int cols = 5;
	
	private BasicGrid<String> grid;
	
	@Before
	public void setUp() throws Exception {
		grid = new BasicGrid<String>(rows, cols, new CellPopulator<String>() {
			public String createValue(int row, int column) {
				return String.format("%dx%d", row, column);
			}
		});
	}

	@Test
	public void testGridSize() {
		assertEquals(rows, grid.getRows());
	}
	
	@Test
	public void testGetValue() {
		assertEquals("3x4", grid.getCellData(rows - 1, cols - 1));
	}
	
	public void testWalk() {
		grid.walk(new CellWalker<String>(){
			public void hasCellData(int row, int column, String data) {
				
			}
		});
	}
	
	@Test
	@Ignore
	public void testGridShaper() {
		int removeRows = 1;
		GridShaper<String> gs = grid.createGridShaper();
		Grid<String> subGrid = gs.removeTopRows(removeRows);
		assertEquals(rows - removeRows, subGrid.getRows());
		assertEquals("1x0", subGrid.getCellData(0, 0));
		assertEquals("3x0", subGrid.getCellData(2, 0));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testIterator() {
		int count = 0;
		for (String value : grid) {
			count++;
		}
		assertEquals(rows * cols, count);
	}

}
