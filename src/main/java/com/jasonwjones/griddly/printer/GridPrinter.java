package com.jasonwjones.griddly.printer;

import java.io.Writer;

import com.jasonwjones.griddly.Grid;

/**
 * A GridPrinter prints out a Grid (shocking!).
 * 
 * @author Jason W. Jones
 * 
 */
public interface GridPrinter {

	public void printGrid(Grid<? extends Object> grid, Writer writer);

	public void printGrid(Grid<? extends Object> grid);

	public String printGridToString(Grid<? extends Object> grid);

}
