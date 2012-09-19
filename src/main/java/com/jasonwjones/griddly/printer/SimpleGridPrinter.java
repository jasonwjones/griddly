package com.jasonwjones.griddly.printer;

import java.io.PrintWriter;

import com.jasonwjones.griddly.Grid;

public class SimpleGridPrinter implements GridPrinter {

	private String template;

	public SimpleGridPrinter() {
		this(20);
	}

	public SimpleGridPrinter(int maxLength) {
		template = "[%" + maxLength + "s]";
	}

	@Override
	public void printGrid(Grid<? extends Object> grid, PrintWriter writer) {
		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getColumns(); col++) {
				Object data = grid.getCellData(row, col);
				// TOOD: use native format method
				writer.print(String.format(template, data != null ? data.toString() : ""));
			}
			writer.println();
		}
	}

}
