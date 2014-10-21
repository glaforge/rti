package rti.dsl

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@Immutable
@CompileStatic
class TimeUnitDuration {
    int quantity
    TimeUnit unit

    // we could deal with arithmetic as well
    // thanks to Groovy's operator overloading support

    @Override
    String toString() {
        "$quantity $unit"
    }
}
