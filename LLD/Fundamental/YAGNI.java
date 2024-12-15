package LLD.Fundamental;

// YAGNI - You aren't gonna need it i.e Always implement things when you actually need them, never when you just foresee that you might need them.

// When not to apply YAGNI ?
// Well known requirements - its fine building some support for certain and sure future requirements
// Performance critical areas - Sometimes a more necessary is that performance targets are met first than any other requirements target

class YAGNIWrong { // Over engineered
    public void processPayment(String paymentType) {
        if (paymentType.equals("creditCard")) {
            System.out.println("Processing credit card payment");
        } else if (paymentType.equals("debitCard")) {
            System.out.println("Processing debit card payment");
        } else if (paymentType.equals("paypal")) {
            System.out.println("Processing net banking payment");
        } else if (paymentType.equals("wallet")) {
            System.out.println("Processing wallet payment");
        } else if (paymentType.equals("upi")) {
            System.out.println("Processing upi payment");
        } else if (paymentType.equals("bitcoin")) {
            System.out.println("Processing cod payment");
        }
    }
}

class YAGNICorrect { // Yagni aligned
    public void processPayment(String paymentType) {
        if (paymentType.equals("creditCard")) {
            System.out.println("Processing credit card payment");
        } else if (paymentType.equals("debitCard")) {
            System.out.println("Processing debit card payment");
        }
    }
}