package com.jasonwjones.griddly.printer;

import java.io.PrintWriter;
import java.io.Writer;

import com.jasonwjones.griddly.Grid;

public class SimpleGridPrinter extends AbstractGridPrinter implements GridPrinter {

	private String template;

	private String nullText = " ";
	
	public interface ValueConverter {

		public String convert(Object value);

	}

	public SimpleGridPrinter() {
		this(20);
	}

	public SimpleGridPrinter(int maxLength) {
		template = "[%-" + maxLength + "s]";
	}

	@Override
	public void printGrid(Grid<? extends Object> grid, Writer writer) {
		PrintWriter printWriter = new PrintWriter(writer);

		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getColumns(); col++) {
				Object data = grid.getCellData(row, col);
				printWriter.format(template, data != null ? data.toString() : nullText);
			}
			printWriter.println();
		}
		printWriter.flush();
		printWriter = null;
	}

	public void printGrid(Grid<?> grid, ValueConverter valueConverter) {
		PrintWriter printWriter = new PrintWriter(System.out);

		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getColumns(); col++) {
				String toPrint = valueConverter.convert(grid.getCellData(row, col));
				printWriter.format(template, toPrint);
			}
			printWriter.println();
		}
		printWriter.flush();
		printWriter = null;
	}

}
