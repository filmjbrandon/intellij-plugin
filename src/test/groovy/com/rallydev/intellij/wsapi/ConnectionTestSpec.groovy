package com.rallydev.intellij.wsapi

import com.rallydev.intellij.SpecUtils
import spock.lang.Specification

class ConnectionTestSpec extends Specification {

    def "Ensure doTest tries to make connection"() {
        given:
        RallyClient rallyClient = Mock(RallyClient)
        1 * rallyClient.makeRequest(_) >> { new ApiResponse(SpecUtils.minimalResponseJson) }
        ConnectionTest connectionTest = new ConnectionTest(rallyClient)

        when:
        connectionTest.doTest()

        then:
        true
    }

    def "Ensure exception when no response results"() {
        RallyClient rallyClient = Mock(RallyClient)
        1 * rallyClient.makeRequest(_) >> { null }
        ConnectionTest connectionTest = new ConnectionTest(rallyClient)

        when:
        connectionTest.doTest()

        then:
        thrown(Exception)
    }

}
