package pl.edu.agh.iiet.se.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;

@Getter
@ToString
@EqualsAndHashCode
@XmlRootElement
@AllArgsConstructor
public class KBAnswer {
    private String answer;
    private String imageUrl;
}
