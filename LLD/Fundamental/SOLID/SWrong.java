package LLD.Fundamental.SOLID;

// ---------------------------------- Single Responsiblity principle ----------------------------------
// A class should have only one reason to change
// ---------------------------------- Wrong Example ----------------------------------
//  Marker entity
class Marker {
	String name;
	String color;
	int year;
	int price;
	
	public Marker(String name, String color, int year, int price) {
		this.name = name;
		this.color = color;
		this.year = year;
		this.price = price;
	}
}

class Invoice {
	private Marker marker;
	private int quantity;
	
	public Invoice(Marker marker, int quantity) {
		this.marker = marker;
		this.quantity = quantity;
	}
	
	// Because of below 3 reasons we an say that our class is not following Single responsibility principle
	
	// Reason 1 to change : In case suppose gst introduced and we need to give discount , then we will need to change below method i.e calculateTotal and our class will change
	public int calculateTotal() {
		int price = ((marker.price) * (this.quantity));
        return price;
    }
	
	// Reason 2 to change : In case suppose our printer technology changed then again we will need to change the below method ie. our class will change	
	public void printInvoice() {
		// print the invoice
	}
	
	// Reason 3 to change : Suppose we need to save to file also along with saving to db then again we will need to change below class method
	public void saveToDB() {
		// Save the data into DB
	}
}