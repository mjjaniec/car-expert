package pl.edu.agh.iiet.se.enums;

import java.util.ArrayList;
import java.util.List;

public enum Car {
    bmw_3,
    fiat_panda,
    jeep_renegade,
    mercedes__benz_atego,
    mercedes__benz_sprinter,
    renault_clio,
    scania_v8,
    solaris_urbino,
    suzuki_jimny,
    volkswagen_lt46;

    public static List<String> toStringList(Car... cars) {
        List<String> result = new ArrayList<>(cars.length);
        for (Car car : cars) {
            result.add(car.name());
        }
        return result;
    }

}
