/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devramble.ejb;

import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
public class Main {

    private static InitialContext CONTEXT;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileReader("conf/jndi.properties"));

            CONTEXT = new InitialContext(props);
        } catch (NamingException | IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        AccountBeanRemote accountBean = lookupAccountBean();
        System.out.println("AccountBean: " + accountBean);

        // create account
        Account acc = new Account();
        acc.setCustomerName("John Doe");
        acc.setAmount(100.00);

        accountBean.createAccount(acc);

    }

    private static AccountBeanRemote lookupAccountBean() throws Exception {
        return (AccountBeanRemote) CONTEXT.lookup("java:module/AccountBean!com.devramble.ejb.AccountBeanRemote");
    }

}
