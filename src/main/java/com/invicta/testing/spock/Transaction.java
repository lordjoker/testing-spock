/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invicta.testing.spock;

import java.math.BigDecimal;

/**
 *
 * @author Marcin Wisniewski
 */
public class Transaction {
    
    private BigDecimal tax;
    private final TaxCalculator calculator;
    
    public Transaction(TaxCalculator calculator) {
        this.calculator = calculator;
    }
    
    public void perform(String productName, BigDecimal price) {
        tax = calculator.calculateTax(price);
    }

    public BigDecimal getTax() {
        return tax;
    }
    
}
