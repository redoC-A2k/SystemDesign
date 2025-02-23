package LLD.Patterns.Behavioural.Observer;

import java.util.ArrayList;
import java.util.List;

// Observer
// Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing (Ref : R)

// Example (Ref: S)
// Wheather Station , suppose we have tv display, mobile display and other display observing wheather station and showing current tempreature 

interface WeatherStationObservable { // or observable
    void add(Observer observer);
    void remove(Observer observer);
    void notifyObserver();
    void setTemperature(float tempreature);
    float getTemperature(); 
} 

class WeatherStation implements WeatherStationObservable {
    List<Observer> displayObservers;
    float tempreature;
    
    WeatherStation() {
        this.displayObservers = new ArrayList<>();
    } 
    
    @Override
    public void add(Observer observer) {  
        displayObservers.add(observer);         
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : displayObservers) {
            observer.update();
        }        
    }

    @Override
    public void remove(Observer observer) {
        displayObservers.remove(observer);        
    }

    @Override
    public float getTemperature() {
        return tempreature;
    }

    @Override
    public void setTemperature(float tempreature) {
        this.tempreature = tempreature;
        notifyObserver();
    }
}

interface Observer {
    void update();
}

class MobileDisplay implements Observer {
    WeatherStationObservable wheatherStation; // Advantage of this has a relationship is that in update method we don't need to take data as parameter as we have reference to WeatherStationObservable 
    String name;

    MobileDisplay(WeatherStationObservable wheatherStation, String name) {
        this.wheatherStation = wheatherStation;
        this.name = name;
    }
    
    @Override
    public void update() {
        System.out.println("Mobile Display : " + wheatherStation.getTemperature());
    }
}



class Client {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        MobileDisplay mobileDisplay1 = new MobileDisplay(weatherStation, "Nokia");
        MobileDisplay mobileDisplay2 = new MobileDisplay(weatherStation, "Iphone");

        weatherStation.add(mobileDisplay1);
        weatherStation.add(mobileDisplay2);

        
        System.out.println("Weather station setting temperature to 25.0 degrees Celsius");
        weatherStation.setTemperature(25.0f);

        System.out.println("\nWeather station setting temperature to 30.5 degrees Celsius");
        weatherStation.setTemperature(30.5f);

        weatherStation.remove(mobileDisplay1);

        System.out.println("\nWeather station setting temperature to 20.0 degrees Celsius");
        weatherStation.setTemperature(20.0f);
    }
}

