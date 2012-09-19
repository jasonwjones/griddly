package com.jasonwjones.griddly.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jasonwjones.griddly.impl.AbstractGrid;
import com.jasonwjones.griddly.impl.BasicGrid;


public class AbstractGridTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToArray() {
		AbstractGrid<String> grid = createSimpleGrid(2, 5);
		String[][] gridVals = grid.toArray(String.class);		
		assertEquals(2, gridVals.length);
		assertEquals("0,0", gridVals[0][0]);
	}

	private AbstractGrid<String> createSimpleGrid(int rows, int columns) {
		AbstractGrid<String> grid = new BasicGrid<String>(rows, columns);
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				grid.setCellData(row, column, row + "," + column);
			}
		}
		return grid;
	}
	
}
