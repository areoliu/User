//package com.example.user.config;
//
//import org.apache.tomcat.util.http.parser.Authorization;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.*;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.util.Arrays;
//
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter
//{
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private ClientDetailsService  clientDetailsService;
//
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    // 该对象将为刷新token提供支持
//    @Autowired
//    UserDetailsService userDetailsService;
//
//
//    //客户端配置
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("net5ijy")
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .authorizedGrantTypes("authorization_code","password", "refresh_token")
//                .resourceIds("order")
//                .scopes("all")
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com");
//
//    }
//
//    //令牌访问端点
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)   //密码模式
//                .authorizationCodeServices(authorizationCodeServices) //授权码模式
//                .tokenServices(tokenServices())
//                .userDetailsService(userDetailsService)
//                //令牌管理服务
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
//    }
//
//
//    //令牌访问端点安全认证
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//
//        security.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()")
//                .allowFormAuthenticationForClients();
//
//    }
//
//
//
//    //令牌管理服务
//    @Bean
//    public AuthorizationServerTokenServices tokenServices(){
//        DefaultTokenServices services = new DefaultTokenServices();
//        services.setClientDetailsService(clientDetailsService);
//        services.setSupportRefreshToken(true);
//        services.setTokenStore(tokenStore);
//        services.setAccessTokenValiditySeconds(7200);
//        services.setRefreshTokenValiditySeconds(259200);
//        return services;
//
//    }
//
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new InMemoryAuthorizationCodeServices();
//    }
//
//
//}
