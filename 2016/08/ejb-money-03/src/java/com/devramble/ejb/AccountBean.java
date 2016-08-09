/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devramble.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AccountBean implements AccountBeanRemote {
    
    @PersistenceContext(unitName = "ejb-money-02PU")
    private EntityManager entityManager;

    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void deposit(Transaction transaction) {
        Account account = entityManager.find(Account.class, transaction.getAccount().getId());
        double amount = account.getAmount();
        amount += transaction.getAmount();
        account.setAmount(amount);
        entityManager.persist(account);
    }

    @Override
    public void withdraw(Transaction transaction) {
        Account account = entityManager.find(Account.class, transaction.getAccount().getId());
        double amount = account.getAmount();
        // can not withdraw if insufficient funds
        if(amount < transaction.getAmount()){
            throw new IllegalArgumentException("Insufficient funds");
        }
        amount -= transaction.getAmount();
        account.setAmount(amount);
        entityManager.persist(account);
    }

    @Override
    public Double checkBalance(Long accountId) {
        Account account = entityManager.find(Account.class, accountId);
        return account.getAmount();
    }

}
