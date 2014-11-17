package pl.edu.agh.iiet.se.service;

import jpl.*;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.dto.KBParameterDesc;
import pl.edu.agh.iiet.se.dto.UIKBParameterDesc;

import java.util.*;

public class KnowledgeBaseService {
    private static final String CONSULT = "consult";
    private static final String BEST_CAR = "best_car";
    private static final String CLEANUP = "cleanup";
    //private static final String kbLocation = "/home/mjjaniec/car-expert/src/main/prolog/KnowledgeBase.pl";
    private static final String kbLocation = "/home/mjjaniec/car-expert/src/main/prolog/test.pl";

    {
        loadEngine();
    }

    private void loadEngine() {
        Query loadQuery = new Query(CONSULT, new Term[]{new Atom(kbLocation)});
        if (!loadQuery.hasSolution()) {
            throw new RuntimeException("unable to load knowledge base");
        }
    }

    public List<String> matchingCars(List<KBParameter> parameters) {
        Variable car = new Variable("Car");
        List<Term> requestedCarFeatures = new LinkedList<>();
        for (KBParameter parameter : parameters) {
            KBParameterDesc parameterDesc = parameter.getDesc();
            if (parameterDesc.hasValue()) {
                requestedCarFeatures.add(new Compound(parameterDesc.predicateName(), new Term[]{parameterDesc.createWithValue(parameter.getValue())}));
            } else {
                requestedCarFeatures.add(new Atom(parameterDesc.predicateName()));
            }
        }

        Query bestCar = new Query(BEST_CAR, new Term[]{
                Util.termArrayToList(requestedCarFeatures.toArray(new Term[requestedCarFeatures.size()])), car});
        Hashtable[] solutions = bestCar.allSolutions();
        Set<String> result = new HashSet<>();
        for (Hashtable solution : solutions) {
            String matchingCar = solution.get(car.name()).toString();
            result.add(matchingCar);
        }

        cleanKb();

        return new ArrayList<>(result);
    }

    private void cleanKb() {
        Query cleanup = new Query(new Atom(CLEANUP));
        if (!cleanup.hasSolution()) {
            throw new RuntimeException("unable to clean knowledge base");
        }
    }

    public List<UIKBParameterDesc> parameters() {
        return UIKBParameterDesc.params();
    }
}
