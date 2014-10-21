package rti.dsl

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@CompileStatic
@Canonical
class StreamBuilder {
    String name
    String[] includes
    String[] excludes
    Closure[] allFunctions
    Closure expressionClause
    Closure havingClause
    Window window
    String[] formats
    Closure[] criteriaClauses

    void init(@DelegatesTo(StreamBuilder) Closure cl) {
        def body = (Closure)cl.clone()

        body.delegate = this
        body.resolveStrategy = Closure.DELEGATE_FIRST

        body()
    }

    void include(String... includes) {
        this.includes = includes
    }

    void exclude(String... excludes) {
        this.excludes = excludes
    }

    void groupBy(Closure groupByClosure) {
        groupByClosure()
    }

    void expression(@DelegatesTo(DeviceHolder) Closure expressionClosure) {
        this.expressionClause = (Closure)expressionClosure.clone()
        this.expressionClause.delegate = new DeviceHolder(new Device())
        this.expressionClause.resolveStrategy = Closure.DELEGATE_FIRST
    }

    void having(@DelegatesTo(DeviceHolder) Closure havingClosure) {
        this.havingClause = (Closure)havingClosure.clone()
        this.havingClause.delegate = new DeviceHolder(new Device())
        this.havingClause.resolveStrategy = Closure.DELEGATE_FIRST
    }

    void window(@DelegatesTo(Window) Closure windowClosure) {
        this.window = new Window()
        def clone = (Closure)windowClosure.clone()
        clone.delegate = this.window
        clone.resolveStrategy = Closure.DELEGATE_FIRST
        clone()
    }

    void functions(Closure... functions) {
        this.allFunctions = functions
    }

    void export(Closure exportClosure) {
        exportClosure()
    }

    void format(String... formats) {
        this.formats = formats
    }

    void alert(Closure alertClosure) {
        alertClosure()
    }

    void criteria(Closure... criteria) {
        this.criteriaClauses = criteria
    }

    static StreamBuilder create(CreationType creationType, String name, @DelegatesTo(StreamBuilder) Closure body) {
        if (creationType == CreationType.stream) {
            def streamBuilder = new StreamBuilder(name)
            streamBuilder.init(body)
            return streamBuilder
        } else {
            throw new Exception("Only stream configuration allowed.")
        }
    }
}
