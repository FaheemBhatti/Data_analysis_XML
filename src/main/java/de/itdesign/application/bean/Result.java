package de.itdesign.application.bean;

public class Result {
	private String name;
	private double value;

	public Result() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Result [name=" + name + ", value=" + value + "]";
	}

	public Result(String name, double value) {
		this.name = name;
		this.value = value;
	}

}
