/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devramble.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class TransactionBean implements TransactionBeanRemote {
    
     @PersistenceContext(unitName = "ejb-money-02PU")
    private EntityManager entityManager;

    @Override
    public List<Transaction> viewTransactions(Long accountId) {
        return entityManager.createQuery("From Transaction").getResultList();
    }

}
