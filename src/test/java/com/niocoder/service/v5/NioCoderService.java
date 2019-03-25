package com.niocoder.service.v5;

import com.niocoder.beans.factory.Autowired;
import com.niocoder.dao.v5.AccountDao;
import com.niocoder.dao.v5.ItemDao;
import com.niocoder.stereotype.Component;
import com.niocoder.util.MessageTracker;

/**
 * 测试注解
 */
@Component(value = "nioCoder")
public class NioCoderService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }
    public void placeOrderWithException() {
        throw new NullPointerException();
    }

}