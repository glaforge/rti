package rti.dsl

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class Window {
    String name
    WindowType type
    TimeUnitDuration interval
    TimeUnitDuration laggingInterval
    TimeUnitDuration leadingInterval

    void name(String name) {
        this.name = name
    }

    void type(WindowType type) {
        this.type = type
    }

    void interval(TimeUnitDuration interval) {
        this.interval = interval
    }

    void laggingInterval(TimeUnitDuration laggingInterval) {
        this.laggingInterval = laggingInterval
    }

    void leadingInterval(TimeUnitDuration leadingInterval) {
        this.leadingInterval = leadingInterval
    }
}
