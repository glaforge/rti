package rti.dsl

import groovy.transform.CompileStatic

@CompileStatic
enum TimeUnit {
    MILLISECOND (                  1, 'ms'),
    SECOND      (               1000, 'sec'),
    MINUTE      (          60 * 1000, 'min'),
    HOUR        (     60 * 60 * 1000, 'hour'),
    DAY         (24 * 60 * 60 * 1000, 'day')

    final long millisecond
    final String abbreviation

    TimeUnit(long millisecond, String abbrev) {
        this.millisecond = millisecond
        this.abbreviation = abbrev
    }

    @Override
    String toString() {
        abbreviation
    }
}
