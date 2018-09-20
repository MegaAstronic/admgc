package net.moonj.admgc.vo;

public class LinkNode extends Node {
	private String href;
	private String iconClass= null;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	@Override
	public String toString() {
		return "{LinkNode "+super.toString()+" href=" + href + ", iconClass=" + iconClass + "}";
	}
	
}
