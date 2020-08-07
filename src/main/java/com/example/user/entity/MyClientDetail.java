package com.example.user.entity;

import lombok.Data;

@Data
public class MyClientDetail {

    private String resourceIds;

    private String clientId;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private int accessTokenValidity;

    private int refreshTokenValidity;

    private String additionalInformation;

    private String autoApprove;


}
