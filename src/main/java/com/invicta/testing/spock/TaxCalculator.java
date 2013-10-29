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
public interface TaxCalculator {
    
    BigDecimal calculateTax(BigDecimal price);
    
}
