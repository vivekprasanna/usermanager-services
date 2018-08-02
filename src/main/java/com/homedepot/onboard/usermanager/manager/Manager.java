package com.homedepot.onboard.usermanager.manager;

import com.homedepot.onboard.usermanager.dto.User;
import com.homedepot.onboard.usermanager.repo.SpannerUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(Manager.class);

    @Autowired
    SpannerUserRepo userRepo;

    public String registerUser(User user) throws Exception{
        try {
            User userRead = userRepo.readUser(user);
            if (null == userRead || null == userRead.getUserName()) {
                userRepo.insertUser(user);
                return "Success";
            }else{
                return "User already registered.";
            }
        }catch (Exception e){
            LOGGER.error("Exception while registering for user: "+ user.getUserName()+" : Exception :: "+e.getMessage());
            throw e;
        }
    }

    public User validateLogin(User user) throws Exception{
        return userRepo.validateUserLogin(user);
    }
}
