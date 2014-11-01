package pl.edu.agh.iiet.se.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class KBParameters {
    private int passengers;
    private float cargo;
}
