package serverClient;

import DatabasePack.Car;
import DatabasePack.userLoginInfo;
import java.io.Serializable;

public class requestPrcoess implements Serializable {

    private requestType req;
    private String regNo;
    private String make;
    private String model;
    private String color; // for buy
    private Car car;
    private userLoginInfo loginInfo;

    public void setRegNo(String reg) {
        regNo = reg;
    }
    public void setMake (String mk) {
        make = mk;
    }
    public void setModel (String mod) {
        model = mod;
    }
    public void setColor (String col) {
        color = col;
    }
    public void setCar (Car c) {
        car = c;
    }
    public void setRequestType (requestType t) {
        req = t;
    }
    public void setLoginInfo(userLoginInfo user) {loginInfo = user; }
    public requestType getRequestType () {
        return req;
    }
    public String getRegNo() {
        return regNo;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public Car getCar() {
        return car;
    }
    public userLoginInfo getLoginInfo() { return loginInfo; }
}
