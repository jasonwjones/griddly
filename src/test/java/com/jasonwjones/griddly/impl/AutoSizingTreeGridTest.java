package com.jasonwjones.griddly.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jasonwjones.griddly.Grid;
import com.jasonwjones.griddly.action.CellPopulator;

public class AutoSizingTreeGridTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateWithPopulator() {
		AutoSizingTreeGrid<String> g = new AutoSizingTreeGrid<String>(4, 5, new CellPopulator<String>() {
			public String createValue(int row, int column) {
				return String.format("%dx%d", row, column);
			}
		});
		assertEquals(4, g.getRows());
		assertEquals("0x0", g.getCellData(0, 0)); 
	}
	
	@Test
	@Ignore
	public void testWalk() {
		
	}

	@Test
	public void testCropped() {
		AutoSizingTreeGrid<String> grid = new AutoSizingTreeGrid<String>();
		grid.setCellData(2, 4, "2x4");
		grid.setCellData(6, 10, "6x10");
		
		Grid<String> cropped = grid.cropped();
		assertEquals(5, cropped.getRows());
		assertEquals(7, cropped.getColumns());
		assertEquals("2x4", cropped.getCellData(0, 0));
		assertEquals("6x10", cropped.getCellData(4, 6));
	}

}
