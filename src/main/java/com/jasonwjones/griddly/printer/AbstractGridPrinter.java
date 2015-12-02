package com.jasonwjones.griddly.printer;

import java.io.OutputStreamWriter;
import java.io.StringWriter;

import com.jasonwjones.griddly.Grid;

public abstract class AbstractGridPrinter implements GridPrinter {

	public AbstractGridPrinter() {
		super();
	}

	@Override
	public void printGrid(Grid<? extends Object> grid) {
		printGrid(grid, new OutputStreamWriter(System.out));
	}

	public String printGridToString(Grid<? extends Object> grid) {
		StringWriter stringWriter = new StringWriter();
		printGrid(grid, stringWriter);
		return stringWriter.toString();
	}
}