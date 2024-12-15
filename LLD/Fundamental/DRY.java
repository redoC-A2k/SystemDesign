package LLD.Fundamental;
// DRY: Don't Repeat Yourself

// How to apply dry ? -
// Identify repititive code and extract it to a common method
// Use inheritance or composition to avoid code duplication
// Leverage libraries and frameworks , for example logging libraries or frameworks (be cautious of overusing external dependency) 
// Refactor regularly : applying dry principle is an ongoing process

// When not to use dry ? -
// Premature abstraction - Don't try to apply dry too early in code
// Performance critical code - duplicated code can be faster than a common method , especially if function has high overhead or not inlined
// Sacrificing readablity - Leave simple and easy to understand duplicate code rather than creating complex hierarchy
// One-time usage - Not worth to extract code if it is used only once
// Legacy code - Duplicate code rather than refactoring entire legacy code system
// Debugging and testing - In some cases, it is easier to debug and test duplicated code than a common method

class DRYWrong {
    public Double calculateTaxFood(int price) {
        return price * 0.05;
    }

    public Double calculateTaxClothes(int price) {
        return price * 0.1;
    }

    public Double calculateTaxElectronics(int price) {
        return price * 0.15;
    }
}

class DRYCorrect {
    public Double calculateTax(int price, double taxRate) {
        return price * taxRate;
    }
}

public class DRY {
    public static void main(String[] args) {
        DRYWrong dryWrong = new DRYWrong();
        System.out.println(dryWrong.calculateTaxFood(100));
        System.out.println(dryWrong.calculateTaxClothes(100));
        System.out.println(dryWrong.calculateTaxElectronics(100));

        DRYCorrect dryCorrect = new DRYCorrect();
        System.out.println(dryCorrect.calculateTax(100, 0.05));
        System.out.println(dryCorrect.calculateTax(100, 0.1));
        System.out.println(dryCorrect.calculateTax(100, 0.15));
    }
}