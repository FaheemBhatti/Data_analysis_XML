package de.itdesign.application.bean;

public class Operation {

	private String name;
	private String type;
	private String func;
	private String attrib;
	private String filter;

	public Operation() {
	}

	public Operation(String name, String type, String func, String attrib, String filter) {
		this.name = name;
		this.type = type;
		this.func = func;
		this.attrib = attrib;
		this.filter = filter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getAttrib() {
		return attrib;
	}

	public void setAttrib(String attrib) {
		this.attrib = attrib;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public String toString() {
		return "Operation [name=" + name + ", type=" + type + ", func=" + func + ", attrib=" + attrib + ", filter="
				+ filter + "]";
	}

}
