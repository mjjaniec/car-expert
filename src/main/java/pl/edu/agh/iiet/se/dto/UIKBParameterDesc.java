package pl.edu.agh.iiet.se.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
public class UIKBParameterDesc {
    @NonNull
    private String element;
    private String unit;
    private List<String> values;
    private String step;
    @NonNull
    private String name;

    public static List<UIKBParameterDesc> params() {
        List<UIKBParameterDesc> params = new LinkedList<>();
        params.add(intConstraints(new UIKBParameterDesc("input", "max_passengers")));
        params.add(m3(floatConstraints(new UIKBParameterDesc("input", "max_capacity"))));
        params.add(ton(floatConstraints(new UIKBParameterDesc("input", "mass"))));
        params.add(values(new UIKBParameterDesc("select", "category"), "a", "b", "c"));
        params.add(values(new UIKBParameterDesc("select", "type"), "off_road", "personal", "sport", "transport_passengers", "transport_cargo"));
        params.add(values(new UIKBParameterDesc("select", "chassis"), "low", "high"));
        params.add(values(new UIKBParameterDesc("select", "gearbox"), "manual", "automatic"));
        params.add(values(new UIKBParameterDesc("select", "power"), "low", "medium", "high"));
        params.add(values(new UIKBParameterDesc("select", "feature")));

        return params;
    }

    private static UIKBParameterDesc intConstraints(UIKBParameterDesc parameterDesc) {
        parameterDesc.step = "1";
        return parameterDesc;
    }

    private static UIKBParameterDesc floatConstraints(UIKBParameterDesc parameterDesc) {
        parameterDesc.step = "0.01";
        return parameterDesc;
    }

    private static UIKBParameterDesc m3(UIKBParameterDesc desc) {
        desc.setUnit("m3");
        return desc;
    }

    private static UIKBParameterDesc ton(UIKBParameterDesc desc) {
        desc.setUnit("T");
        return desc;
    }

    private static UIKBParameterDesc values(UIKBParameterDesc desc, String... values) {
        desc.setValues(Arrays.asList(values));
        return desc;
    }
}
