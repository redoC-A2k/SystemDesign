package LLD.Fundamental.SOLID;

import java.util.ArrayList;
import java.util.List;

// ---------------------------------- Liskov substitution principle ----------------------------------
// Objects in a program should be replaceable with instances of their subtypes without breaking the program

// ---------------------------------- Correct Example ----------------------------------
// Solution to above is to keep very generic methods in parent , not specific methods which can be overridden in child classes

class Vehicle {
	public Integer getNumberOfWheels() {
		return 2;
	}
}

class EngineVehicle extends Vehicle {
	public Boolean hasEngine() {
		return true;
	}
}

class Bicycle extends Vehicle {}

class MotorCycle extends EngineVehicle {}

class Car extends EngineVehicle {
	@Override
	public Integer getNumberOfWheels() {
		return 4;
	}
}

class Main {
	public static void main(String [] args) {
		List<Vehicle> vehicleList = new ArrayList<>();
		vehicleList.add(new MotorCycle());
		vehicleList.add(new Car());
		vehicleList.add(new Bicycle());
		
		// This will work 
		for(Vehicle vehicle : vehicleList) {
			System.out.println (vehicle.getNumberOfWheels().toString());
		}

		// And here we will get compile time error
		List<EngineVehicle> engineVehicleList = new ArrayList<> ();
		engineVehicleList.add(new MotorCycle());
		engineVehicleList.add(new Car());
		// engineVehicleList.add(new Bicycle()); // <<<<------We will get compile time Error
		
		for(EngineVehicle vehicle : engineVehicleList) {
			System.out.println (vehicle.hasEngine().toString()); 
		}
	}
}