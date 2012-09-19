package com.jasonwjones.griddly.printer;

import java.io.PrintWriter;

import com.jasonwjones.griddly.Grid;

public interface GridPrinter {

	public void printGrid(Grid<? extends Object> grid, PrintWriter writer);

}
