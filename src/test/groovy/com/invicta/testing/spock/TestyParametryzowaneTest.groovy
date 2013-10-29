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

class TestyParametryzowaneTest extends Specification {

    @Unroll("maksimum z liczb #a i #b wynosi #max")
    def "maksimum dwóch liczb" () {
        
        expect:
            Math.max(a, b) == max
        
        where:
            a | b | max
            1 | 3 | 3
            7 | 4 | 7
    }
    
    static List<String> staticList = ["Hello", "World!"]
    
    def "parametr wejściowy" () {
        expect:
            ["Hello", "World!"].contains(array)
        where:
            array << staticList
    }
    
}

