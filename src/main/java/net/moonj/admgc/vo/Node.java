package net.moonj.admgc.vo;

public abstract class Node {
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleClassName(){
		return this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return "Nodename=" + name;
	}
	
}
