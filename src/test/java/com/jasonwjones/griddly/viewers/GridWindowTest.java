package com.jasonwjones.griddly.viewers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.action.CellWalker;
import com.jasonwjones.griddly.testgrids.TestGridFactory;
import com.jasonwjones.griddly.viewers.GridWindow;

public class GridWindowTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void testGridWindowSameSize() {
		Grid<String> grid = TestGridFactory.createStringGrid(4, 6);
		Grid<String> window = new GridWindow<String>(grid);
		assertTrue(grid.isSameSizeAs(window));
		assertEquals(grid.getCellData(3, 5), window.getCellData(3, 5));
	}

	@Test
	public void testWindow() {
		Grid<String> grid = TestGridFactory.createStringGrid(7, 8);
		Grid<String> window = new GridWindow<String>(grid, 1, 1, 6, 7);
		window.walk(new CellWalker<String>() {
			public void hasCellData(int row, int column, String data) {
				System.out.println("row: " + row + " col: " + column + " data: " + data);
			}
		});
		
	}
	
}
