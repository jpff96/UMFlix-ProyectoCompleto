package edu.um.aa.web;

import com.umflix.movies.managers.MovieManager;
import com.umflix.movies.managers.MovieManagerImpl;
import edu.um.aa.umflix.interfaces.UserMgt;
import edu.um.aa.umflix.managers.AuthorizationMgr;
import edu.um.aa.umflix.managers.UserMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 */
@Configuration
public class RestWebApplicationConfiguration {
    @Bean
    public UserMgt user(){
     return UserMgr.getUserMgrInstance();
 }

    @Bean
    public AuthorizationMgr authorizationMgr(){
        return AuthorizationMgr.getAuthorizationMgrInstance();
    }

    @Bean
    @Autowired
    public MovieManager movieMgr(AuthorizationMgr authorizationMgr){return new MovieManagerImpl(authorizationMgr);}



}
