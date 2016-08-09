/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devramble.ejb;

import javax.ejb.Remote;


@Remote
public interface AccountBeanRemote {

    void createAccount(Account account);
    
    void deposit(Transaction transaction);
    
    void withdraw(Transaction transaction);
    
    Double checkBalance(Long accountId);
}
