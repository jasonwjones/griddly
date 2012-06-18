package com.jasonwjones.griddly;

/**
 * This interface must be supported in order to perform a Grid conversion from
 * one type to another
 * @author jasonwjones
 *
 * @param <E> The type of the source grid
 * @param <S> The type of the destination grid
 */
public interface ConverterCellAction<E, S> {

	public S convert(E cellData);

}
