package com.jasonwjones.griddly.cellaction;

import java.util.Map;

import com.jasonwjones.griddly.ResultCellAction;

public class Replacer<E> implements ResultCellAction<E> {

	protected Map<E, E> replacementValues;
	
	public Replacer(Map<E, E> replacementValues) {
		this.replacementValues = replacementValues;
	}
	
	public E resultAction(E cellData) {
		return replacementValues.containsKey(cellData) ? replacementValues.get(cellData) : cellData;
	}

}
