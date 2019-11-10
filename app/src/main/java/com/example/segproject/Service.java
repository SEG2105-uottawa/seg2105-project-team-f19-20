package com.example.segproject;

public class Service {

    private String name;
    private String role;
    private String ID;

    public Service() {
    }

    //service constructor accepts name of service and role providing it
    public Service(String name, String role, String ID) {
        this.name = name;
        this.role = role;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
