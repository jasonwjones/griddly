package com.jasonwjones.griddly.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jasonwjones.griddly.action.CellWalker;


public class ExpandingTreeGridTest {

	private ExpandingTreeGrid<String> grid = new ExpandingTreeGrid<String>();
	
	@Before
	public void setUp() throws Exception {
		grid.setCellData(4, 2, "4x2");
		grid.setCellData(10, 8, "10x8");
	}

	@Test
	public void testGetRows() {
		assertEquals(11, grid.getRows());
	}

	@Test
	public void testGetColumns() {
		assertEquals(9, grid.getColumns());
	}

	@Test
	public void testWalk() {
		final Map<String, Integer> counts = new HashMap<String, Integer>();
		counts.put("count", 0);
		
		grid.walk(new CellWalker<String>() {
			@Override
			public void hasCellData(int row, int column, String data) {
				counts.put("count", counts.get("count") + 1);
				//System.out.println("R: " + row + " C: " + column + " D: " + data);				
			}
		});
		
		assertEquals(99, (int) counts.get("count"));
	}
	
}
