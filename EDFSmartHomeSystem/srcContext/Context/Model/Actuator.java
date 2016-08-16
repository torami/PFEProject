package Context.Model;

public class Actuator extends ConnectedObject {
	private boolean locked = false;

	public Actuator (){  }
	public Actuator (final boolean statelocked){
		this.locked=statelocked;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
