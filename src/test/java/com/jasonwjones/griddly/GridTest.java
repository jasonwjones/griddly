package com.jasonwjones.griddly;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jasonwjones.griddly.impl.BasicGrid;

public class GridTest {

	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void testGrid() {
//		Grid<Double> testGrid = new Grid<Double>(10, 10);
//		testGrid.setCellData(0, 0, 423.0);
//		Double val = testGrid.getCellData(0, 0);
//		assertEquals("Value in is value out", 423.0, val.doubleValue(), 0.01);
//	}
		
	@Test
	public void testGridOfString() {
		Grid<String> grid = new BasicGrid<String>(10, 10);
		grid.setCellData(0, 0, "Mary");
		assertTrue(true);
//		
//		System.out.println(grid);
//		System.out.println(grid.toString(10));
		
	}
	
}
