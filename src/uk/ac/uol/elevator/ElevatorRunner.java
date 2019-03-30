package uk.ac.uol.elevator;

public class ElevatorRunner {

	public static void main(String[] args) throws Exception {
		ElevatorVis ev = new ElevatorVis();
		
		ev.upRequest();
		ev.doorClosed();
		ev.upRequest();
		ev.setRequested(true);
		ev.approachingFloor();
		ev.stopped();
		ev.doorOpened();
		ev.setBlocked(true);
		ev.doorClosingRequest();
		ev.doorOpened();
		ev.setBlocked(false);
		ev.doorClosingRequest();
		ev.doorClosed();
		ev.noRequest();
	}

}
