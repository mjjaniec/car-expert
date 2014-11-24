package pl.edu.agh.iiet.se.service;

import jpl.*;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.dto.KBParameterDesc;
import pl.edu.agh.iiet.se.dto.UIKBParameterDesc;

import java.util.*;

public class KnowledgeBaseService {
    private static final String CONSULT = "consult";
    private static final String GET_CARS = "get_cars";
    private static final String kbPath = "src/main/prolog/";
    private static final String[] kbFiles = new String[] {"ApiHeader.pl", "KnowledgeBase.pl", "ApiCore.pl"};

    {
        loadEngine();
    }

    private void loadEngine() {
        for (String kbFile: kbFiles) {
            String file = kbPath + kbFile;
            Query loadQuery = new Query(CONSULT, new Term[]{new Atom(file)});
            if (!loadQuery.hasSolution()) {
                throw new RuntimeException("unable to load knowledge base: unable to load file \"" + file + "\"");
            }
        }
    }

    public List<String> matchingCars(List<KBParameter> parameters) {
        Variable car = new Variable("Car");
        List<Term> requestedCarFeatures = new LinkedList<>();
        for (KBParameter parameter : parameters) {
            KBParameterDesc parameterDesc = parameter.getDesc();
            if (parameterDesc.hasValue()) {
                requestedCarFeatures.add(new Compound(parameterDesc.predicateName(),
                        new Term[]{parameterDesc.createWithValue(parameter.getValue())}));
            } else {
                requestedCarFeatures.add(new Atom(parameterDesc.predicateName()));
            }
        }

        Query bestCar = new Query(GET_CARS, new Term[]{
                Util.termArrayToList(requestedCarFeatures.toArray(new Term[requestedCarFeatures.size()])), car});
        Hashtable[] solutions = bestCar.allSolutions();
        List<String> result = new ArrayList<>(solutions.length);
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
