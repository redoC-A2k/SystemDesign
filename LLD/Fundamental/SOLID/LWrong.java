package LLD.Fundamental.SOLID;

import java.util.ArrayList;
import java.util.List;

// ---------------------------------- Liskov substitution principle ----------------------------------
// Objects in a program should be replaceable with instances of their subtypes without breaking the program

// ---------------------------------- Wrong Example ----------------------------------

class Vehicle{
	public Integer getNumberOfWheels() {
		return 2;
	}
	public Boolean hasEngine() {
		return true;
	}
}

class MotorCycle extends Vehicle {}

class Car extends Vehicle {
	@Override
	public Integer getNumberOfWheels() {
		return 4;
	}
}

class Bicycle extends Vehicle {
	public Boolean hasEngine() { 
		return null;
	}
}

class Main {
	public static void main(String [] args) {
		List<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(new MotorCycle());
		vehicleList.add(new Car());
		vehicleList.add(new Bicycle()); // problem due to this in below for loop , here we are not following Liskov substitution principle
		
		for(Vehicle vehicle : vehicleList) {
			System.out.println (vehicle.hasEngine().toString()); //hasEngine returns null which will cause nullpointer exception
		}
	}
}