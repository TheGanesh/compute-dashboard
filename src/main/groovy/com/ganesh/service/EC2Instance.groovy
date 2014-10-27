package com.ganesh.service

/**
 * Created by Ganesh Kandisa.
 * This class is Value Holder for aws ec2 instances properties.
 */
class EC2Instance {

    private String name
    private String id
    private String type
    private String state
    private String az
    private String publicIp
    private String privateIp

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getType() {
        return type
    }

    void setType(String type) {
        this.type = type
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    String getAz() {
        return az
    }

    void setAz(String az) {
        this.az = az
    }

    String getPublicIp() {
        return publicIp
    }

    void setPublicIp(String publicIp) {
        this.publicIp = publicIp
    }

    String getPrivateIp() {
        return privateIp
    }

    void setPrivateIp(String privateIp) {
        this.privateIp = privateIp
    }
}
