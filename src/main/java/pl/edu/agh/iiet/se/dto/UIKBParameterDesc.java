package pl.edu.agh.iiet.se.dto;

import lombok.Data;
import lombok.NonNull;
import pl.edu.agh.iiet.se.enums.*;

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

    public UIKBParameterDesc(String element, String name) {
        this(element, name, Collections.emptyList());
    }

    public UIKBParameterDesc(String element, String name, List<String> values) {
        this.name = name;
        this.element = element;
        this.values = values;
    }

    public UIKBParameterDesc(Class<? extends Enum> enumType) {
        this("select", enumType.getSimpleName().toLowerCase(), getNames(enumType.getEnumConstants()));
    }

    public static List<UIKBParameterDesc> params() {
        List<UIKBParameterDesc> params = new LinkedList<>();
        params.add(new UIKBParameterDesc(Type.class));
        params.add(new UIKBParameterDesc(Equipment.class));
        params.add(intConstraints(new UIKBParameterDesc("input", "max_passengers")));
        params.add(m3(floatConstraints(new UIKBParameterDesc("input", "max_cargo"))));
        params.add(new UIKBParameterDesc(Category.class));
        params.add(new UIKBParameterDesc(Feature.class));
        params.add(new UIKBParameterDesc(Gearbox.class));
        params.add(new UIKBParameterDesc(Power.class));
        params.add(new UIKBParameterDesc(Chassis.class));
        params.add(ton(floatConstraints(new UIKBParameterDesc("input", "mass"))));

        return params;
    }

    private static List<String> getNames(Enum[] enumConstants) {
        List<String> result = new ArrayList<>(enumConstants.length);
        for (Enum value : enumConstants) {
            result.add(value.name());
        }
        return result;
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
}
