package com.invicta.testing.spock

import spock.lang.*

class HelloTest extends Specification {
    
    def "Długość łańcuch znaków dla name wynosi length"() {
        expect:
            name.size() == length

        where:
            name     | length
            "Spock"  | 5
            "Kirk"   | 4
            "Scotty" | 6
    }
    
    def "testowanie wyjątków" () {
        when:
            throw new IllegalArgumentException("test")
        then:
            thrown(IllegalArgumentException)
    }
    
    def "czytelne błędy" () {
        expect:
            "oczekiwany tekst jest taki" == "oczekiway tekst jt taki"
    }
} 