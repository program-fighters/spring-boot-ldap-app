package com.example.ldap.springbootldapapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${ldap.urls}")
  private String ldapUrls;

  @Value("${ldap.base.dn}")
  private String ldapBaseDn;

  @Value("${ldap.username}")
  private String ldapSecurityPrincipal;

  @Value("${ldap.password}")
  private String ldapPrincipalPassword;

  @Value("${ldap.user.dn.pattern}")
  private String ldapUserDnPattern;

  @Value(("${ldap.password.attribute}"))
  private String passwordAttribute;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
        .formLogin();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .ldapAuthentication()
        .userDnPatterns(ldapUserDnPattern)
        .contextSource()
        .url(ldapUrls + ldapBaseDn)
        .managerDn(ldapSecurityPrincipal)
        .managerPassword(ldapPrincipalPassword)
        .and()
        .passwordCompare()
        .passwordEncoder(new LdapShaPasswordEncoder())
        .passwordAttribute(passwordAttribute);
  }
}