package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.WithdrawNegativeSumException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService{

    @Autowired
    private TransactionRepository transactionList;

    public Double generatePromo(Double sum){
        if(sum >= 2000){
            Double promotion = sum * 0.1;
            if(promotion > 500){
                promotion = 500.0;
            }
            sum += promotion;
        }
        return sum;
    }

    public Transaction saveTransaction(Transaction transaction){
        return this.transactionList.save(transaction);
    }

    public Transaction createDeposit(Transaction transaction){
        transaction.setAmount(this.generatePromo(transaction.getAmount()));
        if(transaction.getAmount() < 0){
            throw new DepositNegativeSumException("It is not possible to deposit negative amounts");
        }
        return saveTransaction(transaction);
    }

    public Transaction createWithdraw(Transaction transaction){
        if(transaction.getAmount() <= 0){
            throw new WithdrawNegativeSumException("It is not possible to withdraw negative amounts");
        }
        transaction.withdraw();
        return saveTransaction(transaction);
    }

    public List<Transaction> getTransactionsByCbu(Long cbu){
        return this.transactionList.findAllByAccountCbu(cbu);
    }

    public Optional<Transaction>  getTransactionsById(Long id){
        return this.transactionList.findById(id);
    }

    public void deleteTransaction(Long id){
        transactionList.deleteById(id);
    }
    

}