package pl.edu.agh.iiet.se.service;

import jpl.*;
import jpl.Float;
import jpl.Integer;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.dto.KBParameterDesc;
import pl.edu.agh.iiet.se.dto.UIKBParameterDesc;
import pl.edu.agh.iiet.se.model.KnowledgeBase;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class KnowledgeBaseService {
    static {
        new KnowledgeBase("src/main/prolog/KnowledgeBase.pl");
    }

    public List<String> matchingCars(List<KBParameter> parameters) {
        Variable car = new Variable("Car");
        List<Term> requestedCarFeatures = new LinkedList<Term>();
        for(KBParameter parameter: parameters) {
            KBParameterDesc parameterDesc = parameter.getDesc();
            if(parameterDesc.hasValue()) {
                requestedCarFeatures.add(new Compound(parameterDesc.predicateName(), new Term[]{parameterDesc.createWithValue(parameter.getValue())}));
            } else {
                requestedCarFeatures.add(new Atom(parameterDesc.predicateName()));
            }
        }

        Query bestCar = new Query(KnowledgeBase.BEST_CAR, new Term[] {Util.termArrayToList(requestedCarFeatures.toArray(new Term[0])), car});
        Hashtable[] solutions = bestCar.allSolutions();
        List<String> result = new LinkedList<String>();
        for (Hashtable solution : solutions) {
            String matchingCar = solution.get(car.name()).toString();
            result.add(matchingCar);
        }

        return result;
    }

    public List<UIKBParameterDesc> parameters() {
        return UIKBParameterDesc.params();
    }
}
