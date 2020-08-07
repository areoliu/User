package com.example.user.service;

import com.example.user.entity.MyClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


public class ClientDeatilServiceImpl implements ClientDetailsService {

    @Autowired
    MyClientDetailService myClientDetailService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        MyClientDetail authClient = myClientDetailService.loadClientByClientId(clientId);
//        if(authClient == null){
//            System.out.println(clientId+" query null ...");
//        }
//        else{
//            System.out.println(clientId+" query result "+authClient);
//
//        }

        BaseClientDetails details = new BaseClientDetails(authClient.getClientId(),
                authClient.getResourceIds(),
                authClient.getScope(),
                authClient.getAuthorizedGrantTypes(),
                authClient.getAuthorities(),
                authClient.getWebServerRedirectUri());
       // System.out.println(clientId+""+authClient.getClientSecret());
        details.setClientSecret(new BCryptPasswordEncoder().encode(authClient.getClientSecret()));
        return details;

    }

}
