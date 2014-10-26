//package pl.edu.agh.iiet.se.model.tryingWith_jLog;
//
//import pl.edu.agh.iiet.se.model.tryingWith_jLog.jCompound;
//import ubc.cs.JLog.Foundation.jRule;
//import ubc.cs.JLog.Terms.*;
//import ubc.cs.JLog.Foundation.jKnowledgeBase;
//
//public class KnowledgeBase extends jKnowledgeBase {
//
//    /**
//     * Small city car
//     */
//    public static final jAtom RENAULT_CLIO = new jAtom("Renault Clio");
//    /**
//     * City bus
//     */
//    public static final jAtom SOLARIS_URBINO = new jAtom("Solaris Urbino");
//
//    /**
//     * Big transport truck
//     */
//    public static final jAtom SCANIA_V8 = new jAtom("Scania V8");
//
//    public static final String CARGO_CAPACITY = "cargoCapacity";
//    public static final String PEOPLE_CAPACITY = "peopleCapacity";
//
//    public KnowledgeBase() {
//        addFact(new jCompound());
//    }
//
//    private void addFact(jPredicateTerms fact) {
//        this.addRule(new jRule(null, fact));
//    }
//}
