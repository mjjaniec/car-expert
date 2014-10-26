package pl.edu.agh.iiet.se.model;


import jpl.Atom;
import jpl.Query;
import jpl.Term;

public class KnowledgeBase {
    private static final String CONSULT = "consult";

    public static final String FIT_PASSENGERS = "fit_passengers";
    public static final String FIT_CARGO = "fit_cargo";
    public static final String BEST_CAR = "best_car";


    public KnowledgeBase(String knowledgeBaseFileName) {
        Query loadQuery = new Query(CONSULT, new Term[]{new Atom(knowledgeBaseFileName)});
        if (!loadQuery.query()) {
            throw new RuntimeException("unable to lad knowledge base");
        }
    }
}
