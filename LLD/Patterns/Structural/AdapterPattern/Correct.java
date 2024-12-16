package LLD.Patterns.Structural.AdapterPattern;

// Reference - https://refactoring.guru/design-patterns/adapter
// Allows objects with incompatible interfaces to collaborate
// Adapter pattern also known as wrapper pattern

// Problem : suppose you are makeing stock trading app , it downloads stock data from multiple sources in xml and then displays it in nice looking charts and diagrams for users
// At some point you decide to improve the app by adding analytics , you have a library that can do this but it only works with json data . So now what to do ?
// Solution : you can use adapter pattern to make the analytics library work with xml data

// How it works?
// 1. The adapter gets an interface, compatible with one of the existing objects.
// 2. Using this interface, the existing object can safely call the adapter’s methods.
// 3. Upon receiving a call, the adapter passes the request to the second object, but in a format and order that the second object expects.

// Real world analogy -> 1. OTG adapter, 2. Us to indian plug adapter

// Pros :
// 1. Single Responsibility Principle. You can separate the interface or data conversion code from the primary business logic of the program.
// 2. Open/Closed Principle. You can introduce new types of adapters into the program without breaking the existing client code, as long as they work with the adapters through the client interface.

// Cons : 
// 1. Code complexity increases

// RoundHoles are compatible with RoundPegs.

class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg){
        return this.getRadius() >= peg.getRadius();
    }
}

class RoundPeg {
    private double radius;
    
    public RoundPeg() {}
    
    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

// Square pegs are not compatible with round holes

class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare(){
        return this.width * this.width;
    }
}

// Now round holes want to work with square pegs , so we need to create an adapter

class SquarePegAdapter extends RoundPeg { // 1. compatible with Round Holes as it extends RoundPeg and RoundPeg is compatible with RoundHoles
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    public double getSquare(){
        return peg.getSquare();
    }

    @Override
    public double getRadius() { // 2. Using this interface, the existing object can safely call the adapter’s methods.
        return Math.sqrt((peg.getWidth() * peg.getWidth()) / 2); // 3. Upon receiving a call, the adapter passes the request to the second object, but in a format and order that the second object expects.
    }
}

class Main {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);

        if(hole.fits(rpeg)){
            System.out.println("Round peg r5 fits round hole r5.");
        }
        
        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        
        // hole.fits(smallSqPeg); // Won't compile. // not compatible

        // Adapter solves the problem
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);

        if(hole.fits(smallSqPegAdapter)){ // it allows incom
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if(!hole.fits(largeSqPegAdapter)){
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}