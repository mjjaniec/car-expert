package pl.edu.agh.iiet.se.dto;

import jpl.Term;
import lombok.SneakyThrows;
import pl.edu.agh.iiet.se.enums.*;

import java.lang.reflect.Constructor;

public enum KBParameterDesc {

    // numeric items
    max_passengers(jpl.Integer.class, Long.TYPE),
    max_cargo(jpl.Float.class, Double.TYPE),
    mass(jpl.Float.class, Double.TYPE),

    // indirect features
    equipment(jpl.Atom.class, Equipment.class),
    type(jpl.Atom.class, Type.class),

    //enum features
    category(jpl.Atom.class, Category.class),
    chassis(jpl.Atom.class, Chassis.class),
    gearbox(jpl.Atom.class, Gearbox.class),
    power(jpl.Atom.class, Power.class),

    //boolean features
    feature(jpl.Atom.class, Feature.class);

    private KBParameterDesc(Class type, Class constructorType) {
        this.jplType = type;
        this.constructorType = constructorType;
        this.predicateName = name();
    }


    private Class jplType;
    private Class constructorType;
    private String predicateName;

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public Term createWithValue(Object value) {
        if (!hasValue()) {
            throw new IllegalStateException("Should not be called -- has no value");
        }

        if (constructorType.isEnum()) {
            Constructor constructor = jplType.getConstructor(String.class);
            return (Term) constructor.newInstance(value.toString());
        }
        Constructor constructor = jplType.getConstructor(constructorType);
        return (Term) constructor.newInstance(value);
    }

    public String predicateName() {
        return predicateName;
    }

    public boolean hasValue() {
        return jplType != null;
    }
}
