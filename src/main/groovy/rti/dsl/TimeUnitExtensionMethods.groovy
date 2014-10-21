package rti.dsl

import groovy.transform.CompileStatic

@CompileStatic
class TimeUnitExtensionMethods {

    static TimeUnitDuration getMs(int q) {
        new TimeUnitDuration(q, TimeUnit.MILLISECOND)
    }

    static TimeUnitDuration getMs(Number q) {
        new TimeUnitDuration(q as int, TimeUnit.MILLISECOND)
    }

    static TimeUnitDuration getSec(int q) {
        new TimeUnitDuration(q, TimeUnit.SECOND)
    }

    static TimeUnitDuration getSec(Number q) {
        new TimeUnitDuration(q as int, TimeUnit.SECOND)
    }

    static TimeUnitDuration getMin(int q) {
        new TimeUnitDuration(q, TimeUnit.MINUTE)
    }

    static TimeUnitDuration getMin(Number q) {
        new TimeUnitDuration(q as int, TimeUnit.MINUTE)
    }

    static TimeUnitDuration getHour(int q) {
        new TimeUnitDuration(q, TimeUnit.HOUR)
    }

    static TimeUnitDuration getHour(Number q) {
        new TimeUnitDuration(q as int, TimeUnit.HOUR)
    }

    static TimeUnitDuration getDay(int q) {
        new TimeUnitDuration(q, TimeUnit.DAY)
    }

    static TimeUnitDuration getDay(Number q) {
        new TimeUnitDuration(q as int, TimeUnit.DAY)
    }
}
