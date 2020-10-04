package de.itdesign.application.bean;

public class City {
	private String name;
	private double attrib;
	private double area;

	public City() {
	}

	public City(String name, double attrib, double area) {
		this.name = name;
		this.attrib = attrib;
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAttrib() {
		return attrib;
	}

	public void setAttrib(double attrib) {
		this.attrib = attrib;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", attrib=" + attrib + ", area=" + area + "]";
	}

}
