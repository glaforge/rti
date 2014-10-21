package rti.dsl

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class Device {
    String manufacturer
    String model
}
