package com.ganesh.service;



public class EC2Instance {

    private String name;
    private String id;
    private String type;
    private String state;
    private String az;
    private String publicIp;
    private String privateIp;

    public EC2Instance(String name, String id, String type, String state, String az, String publicIp, String privateIp) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.state = state;
        this.az = az;
        this.publicIp = publicIp;
        this.privateIp = privateIp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAz() {
        return az;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getPrivateIp() {
        return privateIp;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp;
    }
}
