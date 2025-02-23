package LLD.Patterns.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;

interface StockObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStockCount(int price);
    public int getStockCount();
}

class IphoneObservable implements StockObservable { // Iphone is a product whose stock is empty
    List<NotificationAlertObserver> observerList;
    int stockCount;

    IphoneObservable() {
        this.observerList = new ArrayList<>();
        this.stockCount = 0; 
    }

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public int getStockCount() {
        return stockCount;
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);        
    }

    @Override
    public void setStockCount(int newStockAdded) {
        if (stockCount == 0 ) {  // As this method is called that means we are adding stock, and if stock is 0 then we need to notify all the subscribers
            notifySubscribers();
        }
        stockCount = stockCount + newStockAdded;
    }   
}

interface NotificationAlertObserver {
    void update();
}

class EmailAlertObserver implements NotificationAlertObserver {
    StockObservable stockObservable;
    String emailId;

    EmailAlertObserver(String emailId, StockObservable stockObservable) {
        this.stockObservable = stockObservable;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        sendMail(emailId, "Product is back in stock");
    }

    private void sendMail(String emailId, String message) {
        System.out.println("Mail sent to " + emailId + " with message : " + message);
    }
}

class MobileAlertObserver implements NotificationAlertObserver {
    StockObservable stockObservable;
    String mobileNumber;

    MobileAlertObserver(String mobileNumber, StockObservable stockObservable) {
        this.stockObservable = stockObservable;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void update() {
        sendSMS(mobileNumber, "Product is back in stock");
    }

    private void sendSMS(String mobileNumber, String message) {
        System.out.println("SMS sent to " + mobileNumber + " with message : " + message);
    }
}

class Client {
    public static void main(String[] args) {
        StockObservable iphoneObservable = new IphoneObservable();

        NotificationAlertObserver observer1 = new EmailAlertObserver("abc@gmail.com", iphoneObservable);
        NotificationAlertObserver observer2 = new MobileAlertObserver("1234567890", iphoneObservable);
        NotificationAlertObserver observer3 = new EmailAlertObserver("xyz@gmail.com", iphoneObservable);

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.add(observer3);
        
        iphoneObservable.setStockCount(10);
    }
}