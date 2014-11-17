package pl.edu.agh.iiet.se.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Car {
    bmw_3("BMW", "3"),
    fiat_panda("Fiat", "Panda"),
    jeep_renegade("Jeep", "Renegade"),
    mercedes_benz_atego("Mercedes-Benz", "Atego"),
    mercedes_benz_sprinter("Mercedes-Benz", "Sprinter"),
    renault_clio("Renault", "Clio"),
    scania_v8("Scania", "V8"),
    solaris_urbino("Solaris", "Urbino"),
    suzuki_jimny("Suzuki", "Jimny"),
    volkswagen_lt46("Volkswagen", "LT46");

    private final String manufacturer;
    private final String model;

    private Car(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public static List<String> toStringList(Car... cars) {
        List<String> result = new ArrayList<>(cars.length);
        for (Car car : cars) {
            result.add(car.name());
        }
        return result;
    }

}
