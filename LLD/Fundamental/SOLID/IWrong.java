package LLD.Fundamental.SOLID;

// ---------------------------------- Interface Segmented Principle ----------------------------------
// Interfaces should be such (segmented), that client should not implement unnecessary functions they do not need 

// ---------------------------------- Wrong Example ----------------------------------
interface RestaurantEmployee {
	void washDishes(); 
	void serveCustomers();
	void cookFood();
}	

class Waiter implements RestaurantEmployee {
	public void washDishes() { // waiter had to implement this function although he does not needed
		// not my job
	}
	
	public void serveCustomers() {
		// yes and here is my implementation
		System.out.println("Serving the customer");
	}
	
	public void cookFood() { // waiter had to implement this function although he does not needed
		// not my job
	}
}	