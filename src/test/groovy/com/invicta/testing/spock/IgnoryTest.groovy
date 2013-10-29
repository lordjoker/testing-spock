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
class IgnoryTest extends Specification {
	
    @Ignore("because TODO")
    def "should have ignore" () {
        expect:
            false
    }
    
    //@IgnoreRest
    def "should ignore everything except for this" () {
        expect:
            true
    }
    
    @IgnoreIf({System.getProperty("os.name").contains("Windows")})
    def "should ignore everything except for this" () {
        expect:
            false
    }
    
    
}

