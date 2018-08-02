package com.homedepot.onboard.usermanager.controller;

import com.homedepot.onboard.usermanager.dto.User;
import com.homedepot.onboard.usermanager.manager.Manager;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api
@RestController
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    Manager manager;

    @ApiOperation(value = "Gets input and prints it out", notes = "Prints user Input", response = String.class)
    @RequestMapping(value = "/api/printUserInput", method = RequestMethod.POST)
    public ResponseEntity<Object> printUserInput(@RequestBody User userManager) throws Exception{
        try{
            LOGGER.info("User Name:"+ userManager.getUserName());
            LOGGER.info("Password:"+ userManager.getPassword());
            System.out.println("User Name:"+ userManager.getUserName());
            System.out.println("Password:"+ userManager.getPassword());
        }catch(Exception e){
            LOGGER.error(" User: errorMessage = " + e.getMessage());
            throw e;
        }
        return ResponseEntity.ok("SUCCESS: Printed the user object.");
    }

    @ApiOperation(value = "Gets input and registers the user", notes = "Registers new user", response = String.class)
    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody User userManager) throws Exception{
        String response = "";
        try{
            response = manager.registerUser(userManager);
        }catch(Exception e){
            LOGGER.error(" User: errorMessage = " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Gets input and validates it", notes = "validates user login", response = String.class)
    @RequestMapping(value = "/api/validateLogin", method = RequestMethod.POST)
    public ResponseEntity<Object> validateLogin(@RequestBody User userManager) throws Exception{
        User response = new User();
        try{
            response = manager.validateLogin(userManager);
        }catch(Exception e){
            LOGGER.error(" User: errorMessage = " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView swaggerui() {
        return new ModelAndView("redirect:swagger-ui.html");
    }
}
