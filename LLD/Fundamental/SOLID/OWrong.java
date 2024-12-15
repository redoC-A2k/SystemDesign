package LLD.Fundamental.SOLID;
// ---------------------------------- Open Closed principle ----------------------------------
// Open for extension but closed for modification
// ---------------------------------- Wrong Example ----------------------------------

class InvoiceDao {
	private Invoice invoice;

	public InvoiceDao(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public void saveToDB() {
		System.out.println("Saving invoice to the database" + invoice);
		// save into the db
	}
	// This is wrong we are modifying already live in production class , this should not be done rather we can extend but should not modify 
	public void saveToFile(String filename){
		// Save invoice in the file with the given name
	}
}