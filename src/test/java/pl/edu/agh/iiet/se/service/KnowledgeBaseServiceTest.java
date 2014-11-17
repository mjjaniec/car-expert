package pl.edu.agh.iiet.se.service;

import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Test;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.dto.KBParameterDesc;
import pl.edu.agh.iiet.se.enums.Car;
import pl.edu.agh.iiet.se.enums.Feature;
import pl.edu.agh.iiet.se.enums.Gearbox;

import java.util.Collections;
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
        List<String> result = new KnowledgeBaseService().matchingCars(Collections.emptyList());
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
        assertThat(result, hasItem(Car.bmw_3.name()));

    }

    @Test
    public void gearbox() {
        KBParameter gearbox = new KBParameter();
        gearbox.setDesc(KBParameterDesc.gearbox);
        gearbox.setValue(Gearbox.automatic);
        List<String> result = new KnowledgeBaseService().matchingCars(Lists.newArrayList(gearbox));
        assertThat(result, is(Car.toStringList(Car.bmw_3, Car.jeep_renegade, Car.renault_clio, Car.scania_v8, Car.volkswagen_lt46)));
    }

}