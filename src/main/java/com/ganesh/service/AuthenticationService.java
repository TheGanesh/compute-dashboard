package com.ganesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.HashMap;


@Component
public class AuthenticationService {

    @Resource(name="credentialsMap")
    HashMap<String, String> credentialsMap;

    public boolean isValidCredentials(String username, String password) {

        if (credentialsMap.keySet().contains(username) && credentialsMap.get(username).equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
