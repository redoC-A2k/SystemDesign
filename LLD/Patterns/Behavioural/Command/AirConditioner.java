package LLD.Patterns.Behavioural.Command;

public class AirConditioner {
    boolean isOn;
    int temperature;

    public void turnOnAC() {
        isOn = true;
        System.out.println("AC is on");
    }

    public void turnOffAC() {
        isOn = false;
        System.out.println("AC is off");
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to " + temperature);
    }
}
