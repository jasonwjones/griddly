package com.jasonwjones.griddly.printer;

import java.io.PrintWriter;

import com.jasonwjones.griddly.Grid;

public class CallbackGridPrinter implements GridPrinter {

	public interface ValueSource {

		public String getValue(int row, int column);
	}

	private ValueSource valueSource;
	private String template;

	public CallbackGridPrinter(ValueSource valueSource) {
		this(20, valueSource);
	}

	public CallbackGridPrinter(int maxLength, ValueSource valueSource) {
		template = "[%" + maxLength + "s]";
		this.valueSource = valueSource;
	}

	@Override
	public void printGrid(Grid<? extends Object> grid, PrintWriter writer) {
		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getColumns(); col++) {
				String value = valueSource.getValue(row, col);
				writer.format(template, value);
			}
			writer.println();
		}
	}

}
