package com.belenot.mirea.schedule.support;

public class IntervalValue<T extends Comparable<T> >  {
    private T firstValue;
    private T lastValue;
    public T getFirstValue() {
	return firstValue;
    }
    public void setFirstValue(T firstValue) {
	this.firstValue = firstValue;
    }
    public T getLastValue() {
	return lastValue;
    }
    public void setLastValue(T lastValue) {
	this.lastValue = lastValue;
    }
    public T getMinValue() {
	return firstValue.compareTo(lastValue) < 0 ? firstValue : lastValue;
    }
    public T getMaxValue() {
	return firstValue.compareTo(lastValue) > 0 ? firstValue : lastValue;
    }
    public boolean isEquals() {
	return firstValue.compareTo(lastValue) == 0;
    }

}
