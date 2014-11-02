package pl.edu.agh.iiet.se.service;

import jpl.*;
import jpl.Float;
import jpl.Integer;
import pl.edu.agh.iiet.se.dto.KBAnswer;
import pl.edu.agh.iiet.se.dto.KBParameters;
import pl.edu.agh.iiet.se.model.KnowledgeBase;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class KnowledgeBaseService {
    private KnowledgeBase knowledgeBase = new KnowledgeBase("src/main/prolog/KnowledgeBase.pl");

    public List<String> matchingCars(KBParameters parameters) {
        Variable car = new Variable("Car");

        Query bestCar = new Query(KnowledgeBase.BEST_CAR, new Term[] {Util.termArrayToList(new Term[] {new Compound("fit_passengers", new Term[] {new Integer((long) parameters.getPassengers()), car}),
                new Compound("fit_cargo", new Term[]{new Float(parameters.getCargo()), car})}), car});
        Hashtable[] solutions = bestCar.allSolutions();
        List<String> result = new LinkedList<String>();
        for (Hashtable solution : solutions) {
            result.add(solution.get(car.name()).toString());
        }

        return result;
    }
}
