package com.example.user.entity;

import lombok.Data;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Data
public class AuthCode {

    private String code;

    private byte[] authentication;
}
