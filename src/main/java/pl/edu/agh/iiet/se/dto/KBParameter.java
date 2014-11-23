package pl.edu.agh.iiet.se.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KBParameter {
    private Object value;
    @NotNull
    private KBParameterDesc desc;
}
