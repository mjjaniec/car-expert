package pl.edu.agh.iiet.se.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class KBParameter {
    private Object value;
    @NotNull
    private KBParameterDesc desc;
}
