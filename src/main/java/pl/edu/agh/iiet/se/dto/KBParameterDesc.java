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
    equipment(jpl.Compound.class, Equipment.class),
    type(jpl.Compound.class, Type.class),

    //enum features
    category(jpl.Compound.class, Category.class),
    chassis(jpl.Compound.class, Chassis.class),
    gearbox(jpl.Compound.class, Gearbox.class),
    power(jpl.Compound.class, Power.class),

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
