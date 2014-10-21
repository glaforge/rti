package rti.dsl

import groovy.transform.CompileStatic
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer
import org.codehaus.groovy.control.customizers.ImportCustomizer
import spock.lang.Specification

import static rti.dsl.CreationType.stream
import static rti.dsl.StreamBuilder.*
import static rti.dsl.WindowType.*

class BuilderSpec extends Specification {
    def "create a builder"() {
        given:
        def f1 = { 'f1' }
        def c1 = { 'c1' }
        def s =

        create stream, "stream name", {
            include 'sms.mo.2g.*', 'sms.mo.3g.*'
            exclude 'sms.mo.2g.fail.user', 'sms.mo.3g.fail.user'
            groupBy {
                expression { device.manufacturer && device.model }
                having {
                    (device.manufacturer == 'Apple' && device.model ==~ 'iPhone.*') ||
                            (device.manufacturer == 'Samsung' && device.model ==~ 'Galaxy.*')
                }
                window {
                    name 'deviceBySecondWindow'
                    type tumbling
                    interval 1.sec
                    laggingInterval 10.sec
                    leadingInterval 10.sec
                }
                functions f1, { 'f2' }
                export {
                    format 'field1', 'field2'
                }
                alert {
                    criteria c1, { 'alert0' }
                }
            }
        }

        expect:
        s.name == "stream name"
        s.includes == ['sms.mo.2g.*', 'sms.mo.3g.*']
        s.excludes == ['sms.mo.2g.fail.user', 'sms.mo.3g.fail.user']
        s.allFunctions[0]() == 'f1'
        s.allFunctions[1]() == 'f2'
        s.window.interval == 1.sec
        s.window.laggingInterval == 10.sec
        s.window.leadingInterval == 10.sec
        s.formats == ['field1', 'field2']
        s.criteriaClauses[0]() == 'c1'
        s.criteriaClauses[1]() == 'alert0'
    }

    def "create a stream builder with a shell"() {
        given:
        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(
                new ImportCustomizer().addStaticStars('rti.dsl.CreationType', 'rti.dsl.StreamBuilder', 'rti.dsl.WindowType'),
                new ASTTransformationCustomizer(CompileStatic)
        )

        def sh = new GroovyShell(this.class.classLoader, new Binding(), configuration)

        def s = (StreamBuilder) sh.evaluate('''
            create stream, "stream name", {
                include 'sms.mo.2g.*', 'sms.mo.3g.*\'
                exclude 'sms.mo.2g.fail.user', 'sms.mo.3g.fail.user\'
                groupBy {
                    expression { device.manufacturer && device.model }
                    having {
                        (device.manufacturer == 'Apple' && device.model ==~ 'iPhone.*') ||
                                (device.manufacturer == 'Samsung' && device.model ==~ 'Galaxy.*')
                    }
                    window {
                        name 'deviceBySecondWindow\'
                        type tumbling
                        interval 1.sec
                        laggingInterval 10.sec
                        leadingInterval 10.sec
                    }
                    functions { 'f2' }
                    export {
                        format 'field1', 'field2\'
                    }
                    alert {
                        criteria { 'alert0' }
                    }
                }
            }
        ''')

        expect:
        s.name == "stream name"
        s.includes == ['sms.mo.2g.*', 'sms.mo.3g.*']
        s.excludes == ['sms.mo.2g.fail.user', 'sms.mo.3g.fail.user']
        s.allFunctions[0]() == 'f2'
        s.window.interval == 1.sec
        s.window.laggingInterval == 10.sec
        s.window.leadingInterval == 10.sec
        s.formats == ['field1', 'field2']
        s.criteriaClauses[0]() == 'alert0'
    }

}
