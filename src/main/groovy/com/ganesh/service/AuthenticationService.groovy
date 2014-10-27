package com.ganesh.service

import org.springframework.stereotype.Component

import javax.annotation.Resource

/**
 * Created by Ganesh Kandisa.
 * Service layer class for authentication and authorization.
 */
@Component
class AuthenticationService {

    @Resource(name = "credentialsMap")
    HashMap<String, String> credentialsMap

    @Resource(name = "apiAuthEntriesList")
    List<String> apiAuthEntriesList

    /**
     * This method validates whether user provided username,password are correct or not.
     * @param username
     * @param password
     * @return boolean
     */
    public boolean isValidCredentials(String username, String password) {

        if (credentialsMap.keySet().contains(username) && credentialsMap.get(username).equals(password)) {
            return true
        } else {
            return false
        }
    }

    /**
     * This method validates coming auth header is one of the valid whitelisted or not,
     * if it is not whitelisted value then API request will be ignored.
     * @param authHeader
     * @return boolean
     */
    public boolean isValidAuthHeader(String authHeader) {

        if (apiAuthEntriesList?.contains(authHeader)) {
            return true
        } else {
            return false
        }
    }

}
