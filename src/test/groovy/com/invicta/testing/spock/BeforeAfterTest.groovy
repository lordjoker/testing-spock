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
class BeforeAfterTest extends Specification {

    def setup() {
        println("setup is the same as @Before");
    }
    
    def setupSpec() {
        println("setupSpec is the same as @BeforeClass");
    }
    
    def cleanup() {
        println("cleanup is the same as @Ater");
    }
    
    def cleanupSpec() {
        println("cleanupSpec is the same as @AfterClass");
    }
    
    def "example test" () {
        expect:
            'to read nice print lines'
    }
    
}

