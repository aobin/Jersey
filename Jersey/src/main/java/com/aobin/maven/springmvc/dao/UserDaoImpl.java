package com.aobin.maven.springmvc.dao;

import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao
{


  public String getUser()
  {
    
    return "get user";
  }

}
