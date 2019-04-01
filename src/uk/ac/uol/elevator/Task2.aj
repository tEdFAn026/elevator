package uk.ac.uol.elevator;

/**
 * noRequest will be handled from any state to go to Idle.
 *
 */
public privileged aspect Task2 {

	boolean around(Elevator e) : call(public boolean Elevator.noRequest()) && target(e) {
		e.setState(Elevator.State.Idle);
		System.out.println("Task 2 noRequest() change state:" + e.state);
		return true;
	}

}
