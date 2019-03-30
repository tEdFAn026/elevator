package uk.ac.uol.elevator;

public class ElevatorVis extends Elevator {
	private boolean blocked = false;
	private boolean requested = false;
	protected SimpleGUI gui;  

	public ElevatorVis() throws Exception {
		gui = new SimpleGUI();
	}

	@Override
	public void goUp() {
		gui.show("up");
	}

	@Override
	public void goDown() {
		gui.show("down");
	}

	@Override
	public void stop() {
		gui.show("up_called");
	}

	@Override
	public void openDoor() {
		gui.show("open");
	}

	@Override
	public void closeDoor() {
		gui.show("closed");
	}

	@Override
	public Boolean floorRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	@Override
	public Boolean blocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

}
