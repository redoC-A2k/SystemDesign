package LLD.Fundamental.SOLID;

// ---------------------------------- Open Closed principle ----------------------------------
// Open for extension but closed for modification
// ---------------------------------- Correct Example ----------------------------------

public interface OCorrect {
	public void save(Invoice invoice);
}

class DatabaseInvoiceDao implements OCorrect {
	@Override
	public void save(Invoice invoice) {
		// save to db
	}
}

class FileInvoiceDao implements OCorrect {
	@Override
	public void save (Invoice invoice) {
		// save to file
	}
}
