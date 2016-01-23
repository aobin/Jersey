package com.aobin.maven.springmvc.controller;

import com.aobin.maven.springmvc.dao.ItemsDao;
import com.aobin.maven.springmvc.dao.UserDao;
import com.aobin.maven.springmvc.entity.Items;
import com.aobin.maven.springmvc.entity.User;
import com.aobin.maven.springmvc.mapper.ItemsMapper;
import com.aobin.maven.springmvc.task.TaskManager;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/items")
public class ItemController /* implements Controller */
{

    private ItemsMapper itemsMapper;

    private MemcachedClient memcachedClient;

    private TaskManager taskManager;

    private ItemsDao itemsDao;

    private UserDao userDao;

    @Autowired
    public ItemController(ItemsMapper itemsMapper, MemcachedClient memcachedClient, ItemsDao itemsDao, UserDao userDao)
    {
        this.itemsMapper = itemsMapper;
        this.memcachedClient = memcachedClient;
        this.itemsDao = itemsDao;
        this.userDao = userDao;
        try
        {
            memcachedClient.add("memcacheName", 0, "memcache_Aobin");
        } catch (TimeoutException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();


        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MemcachedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("=======正在进行ItemsMapper bean的初始化=================");
    }

    @RequestMapping(value = "/getAllItems")
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception
    {

        ModelAndView modelAndView = new ModelAndView();
        // get items from db
        List<Items> items = itemsMapper.getAllItems();
        if (items != null && items.size() > 0)
        {
            // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
            modelAndView.addObject("itemsList", items);
            String memcacheName = memcachedClient.get("memcacheName");
            modelAndView.addObject("memcacheName", memcacheName);
        }

        // 指定视图
        modelAndView.setViewName("items/itemsList");

        Items item = new Items();
        item.setCreatetime(new Date());
        item.setDetail("新增数据");
        item.setName("aobin");
        item.setPrice(102f);
        int resultInt = itemsMapper.add(item);
        // Items item1 =itemsDao.getById(1);
        // System.out.println(item1);
        System.out.println("=====add result+++++" + resultInt);
        System.out.println(userDao.getUser() + "=================");
        // taskManager.printInfo();
        System.out.println();

        return modelAndView;
    }

    @RequestMapping(value = "/getItemsTotalNumber", method = RequestMethod.POST)
    public ModelAndView getItemsTotalNumber(HttpServletRequest request, HttpServletResponse response, String aobin, String sex)
    {
        int resultNumber = itemsMapper.getItemsTotalNumber();
        System.out.println(resultNumber);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultNumber", resultNumber);
        modelAndView.setViewName("items/itemsInfo");

        System.out.println(aobin);
        System.out.println(sex);

        try
        {
            modelAndView.addObject("memcacheName", memcachedClient.get("memcacheName"));
        } catch (TimeoutException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MemcachedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response, User user)
    {
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultNumber", user);
        modelAndView.setViewName("items/userInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
    public ModelAndView editItem(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = true) Integer id) throws Exception
    {
        if (id == null)
        {
            throw new Exception();
        }

        // Items item = itemsMapper.getItemById(id.intValue());
        Items item = itemsDao.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", item);
        modelAndView.setViewName("items/editItem");

        return modelAndView;
    }

    @RequestMapping(value = "/edititemSubmit")
    public String edititemSubmit(HttpServletRequest request, HttpServletResponse response, Integer id, String name, Items item) throws Exception
    {

        System.out.println(name);

        if (item == null)
        {
            throw new Exception();
        }

        itemsMapper.updateItem(item);

        return "redirect:/items/getAllItems";
    }

}
