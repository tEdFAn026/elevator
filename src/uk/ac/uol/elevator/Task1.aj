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

	pointcut stateTrace() : get(Elevator.State Elevator.state);

	after() returning(Elevator.State value): stateTrace() {
		System.out.println("From " + aState + " to " + value);
		aState = value;
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
