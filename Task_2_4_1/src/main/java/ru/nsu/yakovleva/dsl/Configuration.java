package ru.nsu.yakovleva.dsl;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.GroovyShell;
import groovy.lang.MetaProperty;
import groovy.util.DelegatingScript;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import lombok.SneakyThrows;
import org.apache.groovy.groovysh.Main;
import org.codehaus.groovy.control.CompilerConfiguration;

/**
 * Represents a configuration class for running Groovy scripts.
 */
public class Configuration extends GroovyObjectSupport {

    /**
     * The path to the script.
     */
    public URI scriptPath;

    /**
     * List of essential properties.
     */
    public List<String> essentials = List.of(new String[]{"tasks", "allStudents"});

    /**
     * Runs a Groovy script from the specified URI.
     *
     * @param uri The URI of the script to run.
     */
    @SneakyThrows
    public void runFrom(URI uri) {
        this.scriptPath = uri;
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
        DelegatingScript script = (DelegatingScript) sh.parse(uri);
        script.setDelegate(this);
        script.run();
    }

    /**
     * Handles invocation of unknown methods dynamically.
     *
     * @param name The name of the method.
     * @param args The arguments of the method.
     */
    @SneakyThrows
    public void methodMissing(String name, Object args) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(name);
        if (metaProperty != null) {
            Closure<?> closure = (Closure<?>) ((Object[]) args)[0];
            Class<?> type = metaProperty.getType();
            Constructor<?> constructor = type.getConstructor();
            Object value = getProperty(name) == null
                    ? constructor.newInstance() :
                    getProperty(name);
            closure.setDelegate(value);
            closure.setResolveStrategy(Closure.DELEGATE_FIRST);
            closure.call();
            setProperty(name, value);
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    /**
     * Performs post-processing on the configuration.
     */
    public void postProcess() {
        for (String propName : essentials) {
            postProcessSpecific(propName);
        }
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            postProcessSpecific(metaProperty.getName(), metaProperty);
        }
    }

    /**
     * Performs post-processing on a specific property.
     *
     * @param propName The name of the property.
     */
    public void postProcessSpecific(String propName) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(propName);
        if (metaProperty == null) {
            return;
        }
        postProcessSpecific(propName, metaProperty);
    }

    /**
     * Performs post-processing on a specific property with metadata.
     *
     * @param propName     The name of the property.
     * @param metaProperty The metadata of the property.
     */
    @SneakyThrows
    public void postProcessSpecific(String propName, MetaProperty metaProperty) {
        Object value = getProperty(propName);
        if (Collection.class.isAssignableFrom(metaProperty.getType())
                && value instanceof Collection) {
            ParameterizedType collectionType = (ParameterizedType) getClass()
                    .getDeclaredField(metaProperty.getName()).getGenericType();
            Class<?> itemClass = (Class<?>) collectionType.getActualTypeArguments()[0];
            if (Configuration.class.isAssignableFrom(itemClass)) {
                Collection<?> collection = (Collection<?>) value;
                @SuppressWarnings("unchecked") Collection<Object> newValue = collection
                        .getClass().getDeclaredConstructor().newInstance();
                for (Object o : collection) {
                    if (o instanceof Closure<?>) {
                        Object item = itemClass.getDeclaredConstructor().newInstance();
                        ((Configuration) item).setProperty("scriptPath", scriptPath);
                        ((Closure<?>) o).setDelegate(item);
                        ((Closure<?>) o).setResolveStrategy(Closure.DELEGATE_FIRST);
                        ((Closure<?>) o).call();
                        ((Configuration) item).postProcess();
                        newValue.add(item);
                    } else {
                        newValue.add(o);
                    }
                }
                setProperty(metaProperty.getName(), newValue);
            }
        }
    }
}
