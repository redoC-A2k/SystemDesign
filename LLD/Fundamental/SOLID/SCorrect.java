package LLD.Fundamental.SOLID;

// ---------------------------------- Single Responsiblity principle ----------------------------------

// ---------------------------------- Correct Example ----------------------------------

class Invoice {
	private Marker marker;
	private int quantity;
	
	public Invoice(Marker marker, int quantity) {
		this.marker = marker;
		this.quantity = quantity;
	}
	
	public int calculateTotal() {
		int price = ((marker.price) * this.quantity);
		return price;
	}
}

class InvoiceDao {
	private Invoice invoice;

	public InvoiceDao(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public void saveToDB() {
		System.out.println("Saving invoice to the database" + invoice);
		// save into the db
	}
}

class InvoicePrinter {
	private Invoice invoice;
	
	public InvoicePrinter(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public void print() {
		System.out.println("Printing invoice" + invoice);
	 	// print the invoice
	}
}
