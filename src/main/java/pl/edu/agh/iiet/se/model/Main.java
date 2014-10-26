package pl.edu.agh.iiet.se.model;

import jpl.*;
import jpl.Float;
import jpl.Integer;

import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        KnowledgeBase knowledgeBase = new KnowledgeBase("prolog/KnowledgeBase.pl");

        float passengers;
        float cargo;

        while (true) {
            passengers = getFloat("How many passengers?\n: ");
            cargo = getFloat("How many m3 of cargo?\n: ");

            Variable car = new Variable("Car");

            Compound passT = new Compound(KnowledgeBase.FIT_PASSENGERS, new Term[] {car, new Integer((long) passengers)});
            Compound cargoT = new Compound(KnowledgeBase.FIT_CARGO, new Term[] {car, new Float(cargo)});


        }
    }

    private static float getFloat(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextFloat();
            } catch (Exception e) {
                System.out.println("please provide number value");
            }
        }
    }
}
