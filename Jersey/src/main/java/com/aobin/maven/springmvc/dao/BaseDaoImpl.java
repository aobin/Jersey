package com.aobin.maven.springmvc.dao;

import java.lang.reflect.ParameterizedType;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aobin.maven.springmvc.entity.Items;

public class BaseDaoImpl<T> implements BaseDao<T>
{


  private SqlSessionTemplate sessionTemplate;

  private Class clazz;

  private String fullClassName;

  private static final String MAPPER_PATH = "com.aobin.maven.springmvc.mapper.";
  private static final String MAPPER = "Mapper";

  @Autowired
  public BaseDaoImpl(SqlSessionTemplate sessionTemplate)
  {
    this.sessionTemplate = sessionTemplate;

    this.clazz = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    this.fullClassName = MAPPER_PATH + this.clazz.getSimpleName() + MAPPER;
  }

 
  
  public  T getById(Integer id)
  {

    T t = sessionTemplate.selectOne(fullClassName + "." + "getById", id);

    return t;
  }

}
