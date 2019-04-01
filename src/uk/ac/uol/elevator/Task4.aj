package uk.ac.uol.elevator;

/**
 * Whenever the elevator is in state AtFloor and reveices a doorClosingRequest
 * then show the image open_blocked.jpg
 *
 */
public aspect Task4 {
	
	before(ElevatorVis e): call(public boolean ElevatorVis.doorClosingRequest()) && target(e) {
		
		System.out.println("doorClosingRequest " + thisJoinPoint.getTarget());
		
		if(e.getState() == Elevator.State.AtFloor && e.blocked())
			e.gui.show("open_blocked");

	}
}
