package pl.edu.agh.iiet.se.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.*;

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
        List<UIKBParameterDesc> params = new LinkedList<UIKBParameterDesc>();
        params.add(intConstraints(new UIKBParameterDesc("input", "max_passengers")));
        params.add(m3(floatConstraints(new UIKBParameterDesc("input", "cargo_capacity"))));
        params.add(values(new UIKBParameterDesc("select", "type"), "off_road", "personal", "transport"));
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

    private static UIKBParameterDesc values(UIKBParameterDesc desc, String... values) {
        desc.setValues(Arrays.asList(values));
        return desc;
    }
}
