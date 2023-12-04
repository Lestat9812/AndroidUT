package com.example.utbus.models;

public class Driver {

    String id;
    String name;
    String email;
    String licencia;

    public Driver(String id, String name, String email, String licencia) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.licencia=licencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}
