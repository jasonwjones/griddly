package com.jasonwjones.griddly.printer;

import java.io.PrintWriter;
import java.io.Writer;

import com.jasonwjones.griddly.Grid;

public class CallbackGridPrinter extends AbstractGridPrinter implements GridPrinter {

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
	public void printGrid(Grid<? extends Object> grid, Writer writer) {
		PrintWriter printWriter = new PrintWriter(writer);

		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getColumns(); col++) {
				String data = valueSource.getValue(row, col);
				printWriter.format(template, data != null ? data.toString() : "");
			}
			printWriter.println();
		}
		printWriter.flush();
		printWriter.close();
	}

}
