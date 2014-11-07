package pl.edu.agh.iiet.se.dto;

import jpl.Term;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public enum KBParameterDesc {
    max_passengers(jpl.Integer.class, Long.TYPE), cargo_capacity(jpl.Float.class, Double.TYPE), personal, off_road, transport;

    private KBParameterDesc(Class type, Class constructorType) {
        this.type = type;
        this.constructorType = constructorType;
        this.predicateName = name();
    }

    private KBParameterDesc() {
        this.predicateName = name();
        this.type = null;  // ugly but should work for now
    }

    private Class type;
    private Class constructorType;
    private String predicateName;

    @SneakyThrows
    public Term createWithValue(Object value) {
        if(!hasValue()) {
            throw new IllegalStateException("Should not be called -- has no value");
        }

        Constructor constructor =  type.getConstructor(constructorType);
        return (Term) constructor.newInstance(value);
    }

    public String predicateName() {
        return predicateName;
    }

    public boolean hasValue() {
        return type != null;
    }
}
