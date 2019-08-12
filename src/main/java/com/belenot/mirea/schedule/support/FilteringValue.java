package com.belenot.mirea.schedule.support;

public class FilteringValue<T> {
    private T value;
    private boolean excluded;
    public FilteringValue() { }
    public T getValue() {
	return value;
    }
    public void setValue(T value) {
	this.value = value;
    }
    public boolean isExcluded() {
	return excluded;
    }
    public void setExcluded(boolean excluded) {
	this.excluded = excluded;
    }

}
