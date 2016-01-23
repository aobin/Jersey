package com.aobin.maven.springmvc.entity;

import java.io.Serializable;
import java.util.Date;

public class Items implements Serializable
{
  private Date createtime;

  private String detail;

  private Integer id;

  private String name;

  private String pic;

  private Float price;

  public Date getCreatetime()
  {
    return createtime;
  }

  public String getDetail()
  {
    return detail;
  }

  public Integer getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getPic()
  {
    return pic;
  }

  public Float getPrice()
  {
    return price;
  }

  public void setCreatetime(Date createtime)
  {
    this.createtime = createtime;
  }

  public void setDetail(String detail)
  {
    this.detail = detail == null ? null : detail.trim();
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public void setName(String name)
  {
    this.name = name == null ? null : name.trim();
  }

  public void setPic(String pic)
  {
    this.pic = pic == null ? null : pic.trim();
  }

  public void setPrice(Float price)
  {
    this.price = price;
  }

  @Override
  public String toString()
  {
    return "Items [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", createtime=" + createtime + ", detail=" + detail
        + "]";
  }
}
