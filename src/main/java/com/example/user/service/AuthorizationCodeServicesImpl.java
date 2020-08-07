package com.example.user.service;

import com.example.user.entity.AuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {

   // private final ConcurrentMap<String, OAuth2Authentication> codes = new ConcurrentHashMap<String, OAuth2Authentication>();

    @Autowired
    AuthCodeService authCodeService;

    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        String code  = UUID.randomUUID().toString().trim().replaceAll("-", "");
        AuthCode authCode=new AuthCode();
        authCode.setCode(code);
        try {
            authCode.setAuthentication(objectToBytes(oAuth2Authentication));
        } catch (IOException e) {
            e.printStackTrace();
        }
        authCodeService.insert(authCode);
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
