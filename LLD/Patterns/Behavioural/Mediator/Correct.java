package LLD.Patterns.Behavioural.Mediator;

import java.util.List;
import java.util.ArrayList;

// Mediator
// Mediator is a behavioral design pattern that lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object (Ref: R)

// Difference with proxy and observer pattern
// Observer pattern use case if a change happens in the state all other object should be notified, unlike in mediator pattern the use case is to not to allow object to interact with each other directly
// Proxy pattern intent is to make sure that the object is not directly accessed and control access to the actual object , this intent is very different from mediator pattern

// Problem (Ref: S):
// online bidding system : All the bidders do not directly interact with each other. They interact with the mediator and the mediator sells the product to the highest bidder
// Airline management system : All the flights do not interact with each other. They interact with the mediator(airline control center) and the mediator assigns the runway to the flight

interface Colleague { 
    void placeBid(int bidAmount);
    void receiveBidNotification (int bidAmount);
    String getName();
}

class Bidder implements Colleague {
    String name;
    AuctionMediator auctionMediator;

    Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator; // It is upto bidder which auction he wants to participate in , hence taking auctionmediator as a parameter
        auctionMediator.addBidder(this);
    }

    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this, bidAmount);
    }

    @Override
    public void receiveBidNotification(int bidAmount) {
        System.out.println(name + " received bid notification that someone has put the bid of : " + bidAmount);
    }

    @Override
    public String getName() {
        return name;
    }
}

interface AuctionMediator {
    void addBidder(Colleague colleague);
    void placeBid(Colleague colleague, int bidAmount);
}

class Auction implements AuctionMediator {
    List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void addBidder(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void placeBid(Colleague bidder, int bidAmount) {
        for (Colleague colleague : colleagues) {
            if (!colleague.getName().equals(bidder.getName())) {
                bidder.receiveBidNotification(bidAmount);
            }
        }
    }
}

// Client
class Client {
    public static void main(String[] args) {
        AuctionMediator auctionMediator = new Auction();
        Bidder bidder1 = new Bidder("Bidder1", auctionMediator);
        Bidder bidder2 = new Bidder("Bidder2", auctionMediator);
        Bidder bidder3 = new Bidder("Bidder3", auctionMediator);

        bidder1.placeBid(100);
        bidder2.placeBid(200);
        bidder3.placeBid(300);
    }
}