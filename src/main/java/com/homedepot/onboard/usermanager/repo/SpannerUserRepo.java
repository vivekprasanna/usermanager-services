package com.homedepot.onboard.usermanager.repo;

import com.google.cloud.spanner.Statement;
import com.homedepot.onboard.usermanager.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class SpannerUserRepo {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpannerUserRepo.class);

    @Autowired
    SpannerTemplate template;

    @Transactional
    public User readUser(User user) throws Exception{
        User response = new User();
        try{
            List<User> queryResult = template.query(User.class, Statement.of("SELECT * FROM user_manager WHERE username='"+user.getUserName()+"'"));
            if(queryResult.size()>0)
                response = queryResult.get(0);
            else
                return null;
        }catch (Exception e){
            LOGGER.error("Exception while reading for user: "+ user.getUserName() +" : exception :: "+e.getMessage());
            throw e;
        }
        return response;
    }

    @Transactional
    public User validateUserLogin(User user) throws Exception{
        User response = new User();
        try{
            List<User> queryResult = template.query(User.class, Statement.of("SELECT * FROM user_manager WHERE username='"+user.getUserName()+"' AND password='"+user.getPassword()+"'"));
            response = queryResult.get(0);
        }catch (Exception e){
            LOGGER.error("Exception while reading for user: "+ user.getUserName() +" : exception :: "+e.getMessage());
            throw e;
        }
        return response;
    }

    @Transactional
    public void insertUser(User user) throws Exception{
        try{
            template.insert(user);

        }catch (Exception e){
            LOGGER.error("Exception while inserting for user: "+ user.getUserName() +" : exception :: "+e.getMessage());
            throw e;
        }

    }

}
