package LLD.Patterns.Behavioural.TemplateMethod;

// Template
// Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure. (Ref: R)

// Why is it required and when to use (Ref: S)
// When you want all classes to follow the specific steps to process the the task but also,
// want to allow the subclasses to override the specific steps and have their own logic

// Problem (Ref: S):
// Suppose you are designing a payment application  and you want to process the payment in a specific way.
// That is following steps will be followed in order
// 1. Validate request
// 2. Debit money
// 3. Calculate platform Fees and Taxes
// 4. Credit money

// Now the payment could be to a payment or could be to a merchant . but we want the above steps to be followed in order.

// Solution (Ref: S):
abstract class PaymentFlow {
    public abstract void validateRequest();
    public abstract void calculateFees();
    public abstract void debitAmount();
    public abstract void creditAmount();

    // Here we have created a template method, so that all the subclasses will follow the same steps
    public final void sendMoney() {
        // Step 1
        validateRequest();
        
        // Step 2
        calculateFees();
        
        // Step 3
        debitAmount();
        
        // Step 4
        creditAmount();
    }
}

// Now we can have different subclasses for different types of payments
class PayToFriend extends PaymentFlow {

    @Override
    public void calculateFees() {
        System.out.println("0% fees charged");        
    }

    @Override
    public void creditAmount() {
        System.out.println("Credit the full amount");
    }

    @Override
    public void debitAmount() {
        System.out.println("Debit the amount logic of PayToFriend");        
    }

    @Override
    public void validateRequest() {
        System.out.println("Validate logic of PayToFriend");        
    }

}

class PayToMerchant extends PaymentFlow {

    @Override
    public void calculateFees() {
        System.out.println("2% fees charged");        
    }

    @Override
    public void creditAmount() {
        System.out.println("Credit the remaining amount after fees");
    }

    @Override
    public void debitAmount() {
        System.out.println("Debit the amount logic of PayToMerchant");        
    }

    @Override
    public void validateRequest() {
        System.out.println("Validate logic of PayToMerchant");        
    }
}

class Client {
    public static void main(String[] args) {
        PaymentFlow payToFriend = new PayToFriend();
        payToFriend.sendMoney();

        PaymentFlow payToMerchant = new PayToMerchant();
        payToMerchant.sendMoney();
    }
}