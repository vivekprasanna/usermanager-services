package com.homedepot.onboard.usermanager.controller;

import com.homedepot.onboard.usermanager.dto.User;
import com.homedepot.onboard.usermanager.manager.Manager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class UserControllerTest {

    @InjectMocks
    UserController testController;

    @Mock
    Manager manager;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void printUserInput() {
        User user = new User();
        user.setPassword("1234");
        user.setUserName("1234");
        ResponseEntity<Object> response = null;
        try {
            response = testController.printUserInput(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void registerUser() throws Exception {
        User user = new User();
        user.setPassword("1234");
        user.setUserName("1234");
        ResponseEntity<Object> response = null;
        doReturn("Success").when(manager).registerUser(user);
        response = testController.registerUser(user);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void validateLogin() throws Exception {
        User user = new User();
        user.setPassword("1234");
        user.setUserName("1234");
        ResponseEntity<Object> response = null;
        doReturn(user).when(manager).validateLogin(user);
        response = testController.validateLogin(user);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}