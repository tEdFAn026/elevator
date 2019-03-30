package uk.ac.uol.elevator;
//%% NEW FILE Elevator BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.29.1.4439.923218886 modeling language!*/

/**
 * UML State machine of an elevator moving among floors
 */
//line 4 "model.ump"
//line 57 "model.ump"
public class Elevator {

//------------------------
// MEMBER VARIABLES
//------------------------

//Elevator State Machines
	public enum State {
		Idle, DoorClosingMovingUp, DoorClosingMovingDown, Moving, Stopping, DoorOpening, AtFloor, DoorClosing,
		CheckingNextDestination
	}

	private State state;

//------------------------
// CONSTRUCTOR
//------------------------

	public Elevator() {
		setState(State.Idle);
	}

//------------------------
// INTERFACE
//------------------------

	public String getStateFullName() {
		String answer = state.toString();
		return answer;
	}

	public State getState() {
		return state;
	}

	public boolean upRequest() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case Idle:
			setState(State.DoorClosingMovingUp);
			wasEventProcessed = true;
			break;
		case CheckingNextDestination:
			setState(State.DoorClosingMovingUp);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean downRequest() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case Idle:
			setState(State.DoorClosingMovingDown);
			wasEventProcessed = true;
			break;
		case CheckingNextDestination:
			setState(State.DoorClosingMovingDown);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean doorClosed() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case DoorClosingMovingUp:
			// line 13 "model.ump"
			goUp();
			setState(State.Moving);
			wasEventProcessed = true;
			break;
		case DoorClosingMovingDown:
			// line 17 "model.ump"
			goDown();
			setState(State.Moving);
			wasEventProcessed = true;
			break;
		case DoorClosing:
			setState(State.CheckingNextDestination);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean approachingFloor() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case Moving:
			if (!(floorRequested())) {
				setState(State.Moving);
				wasEventProcessed = true;
				break;
			}
			if (floorRequested()) {
				// line 22 "model.ump"
				stop();
				setState(State.Stopping);
				wasEventProcessed = true;
				break;
			}
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean stopped() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case Stopping:
			// line 25 "model.ump"
			openDoor();
			setState(State.DoorOpening);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean doorOpened() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case DoorOpening:
			setState(State.AtFloor);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean doorClosingRequest() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case AtFloor:
			if (!(blocked())) {
				// line 33 "model.ump"
				closeDoor();
				setState(State.DoorClosing);
				wasEventProcessed = true;
				break;
			}
			if (blocked()) {
				setState(State.DoorOpening);
				wasEventProcessed = true;
				break;
			}
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean doorOpeningRequest() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case DoorClosing:
			setState(State.DoorOpening);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	public boolean noRequest() {
		boolean wasEventProcessed = false;

		State aState = state;
		switch (aState) {
		case CheckingNextDestination:
			setState(State.Idle);
			wasEventProcessed = true;
			break;
		default:
			// Other states do respond to this event
		}

		return wasEventProcessed;
	}

	private void setState(State aState) {
		state = aState;
	}

	public void delete() {
	}

// line 48 "model.ump"
	public void goUp() {

	}

// line 49 "model.ump"
	public void goDown() {

	}

// line 50 "model.ump"
	public void stop() {

	}

// line 51 "model.ump"
	public void openDoor() {

	}

// line 52 "model.ump"
	public void closeDoor() {

	}

// line 53 "model.ump"
	public Boolean floorRequested() {
		return false;
	}

// line 54 "model.ump"
	public Boolean blocked() {
		return false;
	}

}