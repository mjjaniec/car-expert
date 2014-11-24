package pl.edu.agh.iiet.se.service;

import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Sets;
import org.junit.Test;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.dto.KBParameterDesc;
import pl.edu.agh.iiet.se.enums.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class KnowledgeBaseServiceTest {

    @Test
    public void shouldLoadWithoutErrors() {
        new KnowledgeBaseService();
    }

    @Test
    public void shouldReturnAllCartsWhenNoParameters() {
        List<String> result = new KnowledgeBaseService().matchingCars(Collections.<KBParameter>emptyList());
        assertThat(result, is(Car.toStringList(Car.values())));
    }

    @Test
    public void shouldReturnOnlyBmwForTintedWindows() {
        KBParameter tintedWindows = new KBParameter();
        tintedWindows.setDesc(KBParameterDesc.feature);
        tintedWindows.setValue(Feature.tinted_windows);
        List<String> result = new KnowledgeBaseService().matchingCars(Lists.newArrayList(tintedWindows));
        assertThat(result, is(Car.toStringList(Car.bmw_3)));
    }

    @Test
    public void jeepShouldHaveGPS() {
        KBParameter gps = new KBParameter();
        gps.setDesc(KBParameterDesc.feature);
        gps.setValue(Feature.gps);
        List<String> result = new KnowledgeBaseService().matchingCars(Lists.newArrayList(gps));
        assertThat(result, hasItem(Car.jeep_renegade.name()));
    }

    @Test
    public void shouldHandleNumericParameters() {
        KnowledgeBaseService kbs = new KnowledgeBaseService();
        KBParameter passengers = new KBParameter(25, KBParameterDesc.max_passengers);
        KBParameter mass = new KBParameter(5.3, KBParameterDesc.mass);

        assertThat(kbs.matchingCars(Lists.newArrayList(passengers)),
                is(Car.toStringList(Car.solaris_urbino, Car.volkswagen_lt46)));

        assertThat(kbs.matchingCars(Lists.newArrayList(mass)),
                is(Car.toStringList(Car.bmw_3, Car.fiat_panda, Car.jeep_renegade, Car.mercedes__benz_sprinter,
                        Car.renault_clio, Car.suzuki_jimny, Car.volkswagen_lt46)));

        assertThat(kbs.matchingCars(Lists.newArrayList(mass, passengers)),
                is(Car.toStringList(Car.volkswagen_lt46)));
    }

    @Test
    public void gearbox() {
        KnowledgeBaseService kbs = new KnowledgeBaseService();
        KBParameter gearboxAuto = new KBParameter();
        gearboxAuto.setDesc(KBParameterDesc.gearbox);
        gearboxAuto.setValue(Gearbox.automatic);
        KBParameter gearboxManual = new KBParameter();
        gearboxManual.setDesc(KBParameterDesc.gearbox);
        gearboxManual.setValue(Gearbox.manual);

        List<String> automatic = kbs.matchingCars(Lists.newArrayList(gearboxAuto));
        assertThat(automatic, is(Car.toStringList(Car.bmw_3, Car.jeep_renegade, Car.renault_clio, Car.scania_v8,
                Car.solaris_urbino, Car.volkswagen_lt46)));

        List<String> manual = kbs.matchingCars(Lists.newArrayList(gearboxManual));
        assertTrue(manual.size() > 0);

        List<String> both = kbs.matchingCars(Lists.newArrayList(gearboxAuto, gearboxManual));

        assertEquals(new HashSet<>(both),
                new HashSet<>(Sets.intersection(new HashSet<>(manual), new HashSet<>(automatic))));
    }

    @Test
    public void complex() {
        KnowledgeBaseService kbs = new KnowledgeBaseService();

        KBParameter passengersType = new KBParameter(Type.transport_passengers, KBParameterDesc.type);
        KBParameter highEquipment = new KBParameter(Equipment.high, KBParameterDesc.equipment);
        KBParameter categoryC = new KBParameter(Category.c, KBParameterDesc.category);

        assertEquals(3, kbs.matchingCars(Lists.newArrayList(categoryC)).size());
        assertEquals(5, kbs.matchingCars(Lists.newArrayList(highEquipment)).size());
        assertEquals(2, kbs.matchingCars(Lists.newArrayList(passengersType)).size());

        assertEquals(1, kbs.matchingCars(Lists.newArrayList(categoryC, highEquipment)).size());
        assertEquals(0, kbs.matchingCars(Lists.newArrayList(categoryC, passengersType)).size());
        assertEquals(1, kbs.matchingCars(Lists.newArrayList(highEquipment, passengersType)).size());

        assertEquals(0, kbs.matchingCars(Lists.newArrayList(highEquipment, passengersType, categoryC)).size());
    }

}