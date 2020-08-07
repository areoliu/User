package com.example.user.service;

import com.example.user.entity.MyClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;


public class ClientDeatilServiceImpl implements ClientDetailsService {

    @Autowired
    MyClientDetailService myClientDetailService;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        MyClientDetail authClient = myClientDetailService.loadClientByClientId(clientId);

        BaseClientDetails details = new BaseClientDetails(authClient.getClientId(),
                authClient.getResourceIds(),
                authClient.getScope(),
                authClient.getAuthorizedGrantTypes(),
                authClient.getAuthorities(),
                authClient.getWebServerRedirectUri());
        details.setClientSecret(authClient.getClientSecret());
        return details;

    }
}
