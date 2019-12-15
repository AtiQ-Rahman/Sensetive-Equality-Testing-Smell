package utility;

public class Flag {
	boolean toStringChecked;
	public Flag() {
		this.toStringChecked=false;
	}
	public void check() {
		this.toStringChecked=true;
	}
	public void uncheck() {
		this.toStringChecked=false;
	}
	public boolean isChecked() {
		return toStringChecked;
	}
}
