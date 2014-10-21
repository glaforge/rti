package rti.dsl

import spock.lang.Specification
import static rti.dsl.TimeUnit.*

class TimeUnitSpec extends Specification {
    def "definition of time durations and their string representation"() {
        expect:
        new TimeUnitDuration(q, u).toString() == s

        where:
            q   |        u      ||      s
            1   |   MILLISECOND ||   '1 ms'
           10   |   MILLISECOND ||  '10 ms'
            3   |     SECOND    ||   '3 sec'
           26   |     MINUTE    ||  '26 min'
            5   |      HOUR     ||   '5 hour'
            8   |      DAY      ||   '8 day'
    }

    def "number based DSL for time representation"() {
        expect:
        durationDsl == durationVerbatim

        where:
        durationDsl | durationVerbatim
        3.ms        | new TimeUnitDuration(3, MILLISECOND)
        7.sec       | new TimeUnitDuration(7, SECOND)
        2.min       | new TimeUnitDuration(2, MINUTE)
        4.hour      | new TimeUnitDuration(4, HOUR)
        8.day       | new TimeUnitDuration(8, DAY)
    }
}
