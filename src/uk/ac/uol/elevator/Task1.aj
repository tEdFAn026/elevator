package uk.ac.uol.elevator;

/**
 * Aspect for tracing of setting states.
 *
 */
public aspect Task1 {

	/**
	 * a. State trace
	 */
	public Elevator.State aState;

	pointcut stateTrace() : set(Elevator.State Elevator.state);

	before(Elevator e) : stateTrace() && target(e) {
		System.out.print("From " + e.getState() + " ");

	}

	after(Elevator e) : stateTrace() && target(e) {
		System.out.println("to " + e.getState());
	}

	/**
	 * b. Not processed event trace
	 */
	pointcut eventProcessed() : call(public boolean Elevator.*());

	after() returning(boolean r) : eventProcessed() {
		if (!r)
			System.out.println("Event " + thisJoinPoint.getSignature().getName() + " not processed");
	}
}
