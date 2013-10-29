/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.invicta.testing.spock

import spock.lang.*
/**
 *
 * @author Marcin Wisniewski
 */
@Unroll
class MockowanieTest extends Specification {

    BigDecimal price = new BigDecimal(200)
    
    def "stubbing basic" () {
        given:
            TaxCalculator calculator = Stub(TaxCalculator)
            Transaction transaction = new Transaction(calculator)
            calculator.calculateTax(price) >> BigDecimal.ONE
        when:
            transaction.perform("test", price)
        then:
            transaction.tax == BigDecimal.ONE
    }
    
    def "stubbing during creation" () {
        given:
            TaxCalculator calculator = Stub(TaxCalculator) {
                calculateTax(price) >> BigDecimal.ONE
            }
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            transaction.tax == BigDecimal.ONE
    }
    
    def "stubbing subsequent" () {
        given:
            TaxCalculator calculator = Stub(TaxCalculator) {
                calculateTax(price) >>> [BigDecimal.ONE, BigDecimal.TEN, {throw new RuntimeException()}]
            }
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            transaction.tax == BigDecimal.ONE
        when:
            transaction.perform("test", price)
        then:
            transaction.tax == BigDecimal.TEN
        when:
            transaction.perform("test", price)
        then:
            thrown(RuntimeException)
    }
    
    def "mocking basic" () {
        given:
            TaxCalculator calculator = Mock(TaxCalculator)
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            1 * calculator.calculateTax(price)
    }    
    
    @Ignore
    def "mocking check params" () {
        given:
            TaxCalculator calculator = Mock(TaxCalculator)
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            1 * calculator.calculateTax(price) // same value
            1 * calculator.calculateTax({it == 200}) // concrete value
            1 * calculator.calculateTax(_ as BigDecimal) // concrete class
            1 * calculator.calculateTax() // whatever
            1 * calculator.calculateTax(!price) // not this price
            1 * calculator.calculateTax(*_) // any argument list
            1 * calculator.calculateTax(!null) // any not null argument
    }    
    
    def "mocking check no invocations on any mock" () {
        given:
            TaxCalculator calculator = Mock(TaxCalculator)
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            0 * _._
    }    
    
    def "spy - rzeczywisty obiekt XTaxCalculator" () {
        given:
            TaxCalculator calculator = Spy(XTaxCalculator, constructorArgs: [0, 1])
            Transaction transaction = new Transaction(calculator)
        when:
            transaction.perform("test", price)
        then:
            1 * calculator.calculateTax(price)
            transaction.getTax() == 20
    }    
    
    def "spy - rzeczywisty obiekt XTaxCalculator częściowo zamockowany" () {
        given:
            TaxCalculator calculator = Spy(XTaxCalculator, constructorArgs: [0, 1])
            Transaction transaction = new Transaction(calculator)
            calculator.calculateTax(price) >> BigDecimal.ZERO // mockujem jedną z metod calculator (reszta działa jak w oryginalnym obiekcie)
        when:
            transaction.perform("test", price)
        then:
            transaction.getTax() == 0
    }    
    
}

