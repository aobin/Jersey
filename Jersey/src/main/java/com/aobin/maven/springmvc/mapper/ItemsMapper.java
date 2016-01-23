package com.aobin.maven.springmvc.mapper;

import com.aobin.maven.springmvc.entity.Items;

import java.util.List;

public interface ItemsMapper {

    public List<Items> getAllItems();

    public Items getItemById(int id);

    public Items getById(int id);

    public int add(Items item);

    public int getItemsTotalNumber();

    public void updateItem(Items item);
}
