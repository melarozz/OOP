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

public class Configuration extends GroovyObjectSupport {
    public URI scriptPath;
    public List<String> essentials = List.of(new String[]{"tasks", "allStudents"});

    //запуск скрипта по uri
    @SneakyThrows
    public void runFrom(URI uri) {
        this.scriptPath = uri;
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName()); //базовый класс сценария
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc); //оболочка GroovyShell
        DelegatingScript script = (DelegatingScript) sh.parse(uri); //парсим скрипт
        script.setDelegate(this); //делегат скрипта
        script.run(); //запуск скрипта
    }

    //вызов неизвестного метода
    @SneakyThrows
    public void methodMissing(String name, Object args) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(name);
        if (metaProperty != null) {
            Closure<?> closure = (Closure<?>) ((Object[]) args)[0]; //замыкание
            Class<?> type = metaProperty.getType(); //получаем тип метаполя
            Constructor<?> constructor = type.getConstructor(); //получаем конструктор
            Object value = getProperty(name) == null
                    ? constructor.newInstance() :
                    getProperty(name); //значение свойства или создание нового объекта
            closure.setDelegate(value); //делегат для замыкания
            closure.setResolveStrategy(Closure.DELEGATE_FIRST); //стратегия разрешения
            closure.call(); //вызываем замыкание
            setProperty(name, value); //значение свойства
        } else {
            throw new IllegalArgumentException("No such field: " + name);
        }
    }

    //постобработка
    public void postProcess() {
        for (String propName : essentials) {
            postProcessSpecific(propName);
        }
        for (MetaProperty metaProperty : getMetaClass().getProperties()) {
            postProcessSpecific(metaProperty.getName(), metaProperty);
        }
    }

    public void postProcessSpecific(String propName) {
        MetaProperty metaProperty = getMetaClass().getMetaProperty(propName);
        if (metaProperty == null) {
            return;
        }
        postProcessSpecific(propName, metaProperty);
    }

    @SneakyThrows
    public void postProcessSpecific(String propName, MetaProperty metaProperty) {
        Object value = getProperty(propName); //значение свойства
        if (Collection.class.isAssignableFrom(metaProperty.getType()) //коллекция?
                && value instanceof Collection) {
            ParameterizedType collectionType = (ParameterizedType) getClass()
                    .getDeclaredField(metaProperty.getName()).getGenericType();//парам тип коллекции
            Class<?> itemClass = (Class<?>) collectionType.getActualTypeArguments()[0];//тип элемента коллекции
            if (Configuration.class.isAssignableFrom(itemClass)) { //наследник конфига?
                Collection<?> collection = (Collection<?>) value; //к коллекции
                @SuppressWarnings("unchecked") Collection<Object> newValue = collection
                        .getClass().getDeclaredConstructor().newInstance();  //новая коллекция
                for (Object o : collection) {
                    if (o instanceof Closure<?>) { //замыкание?
                        Object item = itemClass.getDeclaredConstructor().newInstance(); //новый объект
                        ((Configuration) item).setProperty("scriptPath", scriptPath);
                        ((Closure<?>) o).setDelegate(item);//делегат для замыкания
                        ((Closure<?>) o).setResolveStrategy(Closure.DELEGATE_FIRST); //стратегия
                        ((Closure<?>) o).call(); //вызов
                        ((Configuration) item).postProcess(); //постобработка
                        newValue.add(item); //добавляем айтем
                    } else {
                        newValue.add(o); //добавляем объект
                    }
                }
                setProperty(metaProperty.getName(), newValue);
            }
        }
    }
}
