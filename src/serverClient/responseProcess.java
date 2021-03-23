package serverClient;

import DatabasePack.Car;
import java.io.Serializable;
import java.util.ArrayList;

public class responseProcess implements Serializable {
    private responseType resp;
    private ArrayList<Car> cars;
    private Car car;
    private boolean successful;

    public void setResponseType(responseType t) {
        resp = t;
    }
    public void setCars(ArrayList<Car> c) {
        cars = c;
    }
    public void setCar(Car c) {
        car = c;
    }
    public void setSuccessful(boolean f) {
        successful = f;
    }
    public boolean getSuccessful() {
        return successful;
    }
    public responseType getResponseType() {
        return resp;
    }
    public ArrayList<Car> getCars() {
        return cars;
    }
    public Car getCar() {
        return car;
    }

}
