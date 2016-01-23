package com.aobin.maven.springmvc.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aobin.maven.springmvc.entity.Items;

@Repository
public class ItemsDaoImpl extends BaseDaoImpl<Items> implements ItemsDao
{

  @Autowired
  public ItemsDaoImpl(SqlSessionTemplate sessionTemplate)
  {
    super(sessionTemplate);

  }

}
