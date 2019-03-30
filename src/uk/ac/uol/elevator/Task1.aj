package uk.ac.uol.elevator;

/**
 * Aspect for tracing of setting states.
 *
 */
public aspect Task1 {

	public Elevator.State aState;

	pointcut stateTrace() : get(Elevator.State Elevator.state);

	// before() : stateTrace() {
	// System.out.println("From " + thisJoinPoint.getSourceLocation() + " ");
	// }

	after() returning(Elevator.State value): stateTrace() {
		System.out.println("From " + aState + " to " + value);
		aState = value;
	}
	
	pointcut eventProcessed() : call(public boolean Elevator.*());
	
	after() returning(boolean r) : eventProcessed() {
		if(!r)
		System.out.println("Event " + thisJoinPoint.getSignature().getName() + " not processed");
	}
}
