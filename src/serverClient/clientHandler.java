package serverClient;

import DatabasePack.*;

import java.util.ArrayList;

public class clientHandler implements Runnable {
    private Thread t;
    private networkUtil clientToHandle;
    private Database database;
    private requestPrcoess request;
    private responseProcess response;

    public clientHandler(networkUtil n,Database data) {
        clientToHandle = n;
        database = data;
        t = new Thread(this, "Thread " + clientToHandle.getToken());
        t.start();
    }

    @Override
    public void run() {
        while(true) {
            request = (requestPrcoess) clientToHandle.read();
            if(request != null) {
                response = handleRequests();
                if(!clientToHandle.write(response)) {
                    break;
                }
            }
            else {
                break;
            }
        }
    }

    private responseProcess handleRequests() {
        responseProcess response = new responseProcess();

        if(request.getRequestType() == requestType.loginRequest) {
            System.out.println("Login");
            response.setSuccessful(handleLoginRequest());
        }
        if(request.getRequestType() == requestType.viewAllCars) {
            System.out.println("view Car");
            response.setCars(handleViewAllCar());
        }
        else if(request.getRequestType() == requestType.AddCar) {
            System.out.println("Add car");
            response.setSuccessful(handleAddCar());
        }
        else if(request.getRequestType() == requestType.buyCar) {
            System.out.println("Buy Car");
            response.setSuccessful(handleBuyCar());
        }
        else if(request.getRequestType() == requestType.deleteCar) {
            System.out.println("Delete Car");
            response.setSuccessful(handleDeleteCar());
        }
        else if(request.getRequestType() == requestType.editedCar) {
            System.out.println("Edit Car");
            response.setSuccessful(handleEditedCar());
        }
        else if(request.getRequestType() == requestType.requestToEdit) {
            System.out.println("Request To Edit");
            response.setSuccessful(handleRequestToEdit());
        }
        else if(request.getRequestType() == requestType.SearchByMake) {
            System.out.println("Search By Make");
            response.setCars(handleSearchByMake());
        }
        else if(request.getRequestType() == requestType.SearchByMakeModel) {
            System.out.println("Search By Make and Model");
            response.setCars(handleSearchByMakeModel());
        }
        else if(request.getRequestType() == requestType.SearchByReg) {
            System.out.println("Search By Reg");
            response.setCar(handleSearchByRegNo());
        }

        return response;
    }

    private boolean handleLoginRequest() {
        return request.getLoginInfo().checkLoginInfo(database);
    }

    private ArrayList<Car> handleViewAllCar() {
        return database.executeSelectAllCars();
    }

    private Car handleSearchByRegNo() {
        return database.executeSelectByRegNo(request.getRegNo());
    }

    private  ArrayList<Car> handleSearchByMake() {
        return database.executeSelectByMake(request.getMake());
    }

    private ArrayList<Car> handleSearchByMakeModel() {
        return database.executeSelectByMakeModel(request.getMake(),request.getModel());
    }

    private boolean handleBuyCar() {
        Car car = database.executeSelectByRegNo(request.getRegNo());
        if(car != null && car.getQuantity() != 0) {
            return database.executeUpdateQuantity(car.getQuantity()-1,request.getRegNo());
        }
        return false;
    }

    private boolean handleAddCar() {
        return database.executeInsertCar(request.getCar());
    }

    private boolean handleDeleteCar() {
        return database.executeDeleteCar(request.getRegNo());
    }

    private boolean handleEditedCar() {
        if(database.executeDeleteCar(request.getRegNo())) {
            return database.executeInsertCar(request.getCar());
        }
        return false;
    }

    private boolean handleRequestToEdit() {
        String editing = database.executeSelectEditingBy(request.getRegNo());
        if(editing != null && editing.equals("")) {
            boolean f = database.executeUpdateEditingBy(clientToHandle.getToken(), request.getRegNo());
            if(f) {
                return true;
            }
        }
        return false;
    }
}
