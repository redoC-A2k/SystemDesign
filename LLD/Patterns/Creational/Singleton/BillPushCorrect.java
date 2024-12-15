package LLD.Patterns.Creational.Singleton;

// This uses static inner helper class to hold the singleton instance
// The inner class is not loaded until it is first referenced by getInstance() method , hence lazy loading
// Pros of BillPushSingleton:
// 1. Thread safe without any synchronization , as JVM loads class only on first reference and final keyword further ensures that it cannot be reassigned
// 2. Lazy loading
// 3. High performance as no synchronization is used

// Cons of BillPushSingleton:
// 1. It is just a complex to implement

// Cons of BillPushSingleton:

class BillPushSingleton {
    private BillPushSingleton () {}

    // Static inner class
    private static class SingletonHelper {
        private static final BillPushSingleton INSTANCE = new BillPushSingleton();
    }

    public static BillPushSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}