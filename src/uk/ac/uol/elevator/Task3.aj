package uk.ac.uol.elevator;

import java.util.Map;
import java.util.HashMap;

/**
 * Controlling the number of floors.
 *
 */
public aspect Task3 {

	class ElevatorStatus {

		final static int topFloor = 20;
		final static int LowestFloor = 0;
		int currentFloor = 0;
		int direction;

		public void setDirection(boolean up) {
			direction = up ? 1 : -1;
		}

		public boolean updateCurrentFloor() {
			if ((cannotDown() && direction == -1) || (cannotUp() && direction == 1))
				return false;

			System.out.print("update floor result: Before:" + currentFloor);
			currentFloor += direction;
			System.out.println(" After:" + currentFloor);
			return true;
		}

		public int getCurrentFloor() {
			return currentFloor;
		}

		public boolean cannotDown() {
			return currentFloor == LowestFloor;
		}

		public boolean cannotUp() {
			return currentFloor == topFloor;
		}
	}

	Map<Elevator, ElevatorStatus> elevatorStatus = new HashMap<Elevator, ElevatorStatus>();

	after(): initialization(Elevator+.new(..)) {
		System.out.println("cons " + thisJoinPoint.getTarget());
		elevatorStatus.put((Elevator) thisJoinPoint.getTarget(), new ElevatorStatus());
	}

	before(Elevator e) : call(public void Elevator.goUp()) && target(e) {
		System.out.println("goUp " + thisJoinPoint.getTarget() + " " + elevatorStatus.containsKey(e));

		if (!elevatorStatus.containsKey(e))
			elevatorStatus.put(e, new ElevatorStatus());

		elevatorStatus.get(e).setDirection(true);
	}

	before(Elevator e) : call(public void Elevator.goDown()) && target(e) {
		System.out.println("goDown " + thisJoinPoint.getTarget() + " " + elevatorStatus.containsKey(e));

		if (!elevatorStatus.containsKey(e))
			elevatorStatus.put(e, new ElevatorStatus());

		elevatorStatus.get(e).setDirection(false);
	}

	after(Elevator e) returning(boolean r) : call(public boolean Elevator.approachingFloor()) && target(e) {
		System.out.println("approachingFloor " + thisJoinPoint.getTarget() + " " + elevatorStatus.containsKey(e));

		if (!elevatorStatus.containsKey(e))
			elevatorStatus.put(e, new ElevatorStatus());

		if (r)
			elevatorStatus.get(e).updateCurrentFloor();
	}

	boolean around(Elevator e) : call(public boolean Elevator.upRequest()) && target(e) {

		System.out.println("upRequest " + thisJoinPoint.getTarget() + " " + elevatorStatus.containsKey(e));

		if (!elevatorStatus.containsKey(e))
			elevatorStatus.put(e, new ElevatorStatus());

		if (elevatorStatus.get(e).cannotUp()) {
			System.out.println("cannot up");
			return false;
		} else
			return proceed(e);
	}
	
	boolean around(Elevator e) : call(public boolean Elevator.downRequest()) && target(e) {

		System.out.println("downRequest " + thisJoinPoint.getTarget() + " " + elevatorStatus.containsKey(e));

		if (!elevatorStatus.containsKey(e))
			elevatorStatus.put(e, new ElevatorStatus());

		if (elevatorStatus.get(e).cannotDown()) {
			System.out.println("cannot down");
			return false;
		} else
			return proceed(e);
	}

}
