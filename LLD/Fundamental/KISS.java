package LLD.Fundamental;

// KISS - Keep it simple, stupid i.e Emphasize simplicity and clarity in code design

class KISSWrong {
    public int factorial (int n) {
        int result = 1;
        if (n < 0 ) { throw new IllegalArgumentException("Number should be non-negative"); }
        else if (n == 0) { return 1; }
        else {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        }
        return result;
    }
}

class KISSCorrect {
    public int factorial (int n) {
        if (n < 0 ) { throw new IllegalArgumentException("Number should be non-negative"); }
        else return n == 0 ? 1 : n * factorial(n - 1);
    }
}
