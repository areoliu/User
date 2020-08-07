package com.example.user.service;

import com.example.user.entity.AuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {


    @Autowired
    AuthCodeService authCodeService;

    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        String code  = UUID.randomUUID().toString().trim().replaceAll("-", "");

        try {
            System.out.println("code is "+code);
            byte[] bytes=objectToBytes(oAuth2Authentication);
            System.out.println("byte is "+bytes);
            authCodeService.insert(code,bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {

        AuthCode authCode=authCodeService.select(code);
        OAuth2Authentication OAuth2Authentication= null;
        try {
            OAuth2Authentication = bytesToObject(authCode.getAuthentication());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        authCodeService.delete(code);
        return OAuth2Authentication;
    }

    /**
     * 将对象转化为字节数组
     * @author lichmama
     * @param object
     * @return
     * @throws IOException
     */
    private byte[] objectToBytes(Object object) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        byte[] bytes = baos.toByteArray();
        baos.close();
        oos.close();
        return bytes;
    }

    /**
     * 将字节数组转化为对象
     * @author lichmama
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private <T> T bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object object = ois.readObject();
        bais.close();
        ois.close();
        return (T) object;
    }
}
