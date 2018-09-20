package net.moonj.admgc.vo;

import java.util.ArrayList;
import java.util.List;

public class DirNode extends Node {
	private List<Node> nodes = new ArrayList<>();
	private String iconClass = null;
	private Integer level;
	
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	@Override
	public String toString() {
		return "{DirNode "+super.toString()+" nodes=" + nodes + ", iconClass=" + iconClass + ", level=" + level + "}";
	}
	
}
