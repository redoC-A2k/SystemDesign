package LLD.Fundamental.SOLID;


// ---------------------------------- Interface Segmented Principle ----------------------------------
// Interfaces should be such (segmented), that client should not implement unnecessary functions they do not need 

// ---------------------------------- Wrong Example ----------------------------------
interface WaiterInterface {
	void serveCustomers();
	void takeOrder();
}

interface ChefInterface {
	void cookFood();
	void decideMenu();
}

class Waiter implements WaiterInterface {
	public void serveCustomers(){
		System.out.println("Serving the customer");
	}
	public void	takeOrder(){
		System.out.println("Taking the order");
	}
}