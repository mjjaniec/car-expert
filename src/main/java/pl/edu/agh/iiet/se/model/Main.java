package pl.edu.agh.iiet.se.model;

import jpl.*;
import jpl.Float;
import jpl.Integer;

import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        KnowledgeBase knowledgeBase = new KnowledgeBase("src/main/prolog/KnowledgeBase.pl");

        float passengers;
        float cargo;

        while (true) {
            passengers = getFloat("How many passengers?\n: ");
            cargo = getFloat("How many m3 of cargo?\n: ");

            Variable car = new Variable("Car");

            Query bestCar = new Query(KnowledgeBase.BEST_CAR, new Term[] {car, new Integer((long) passengers), new Float(cargo)});
            Hashtable solution = bestCar.oneSolution();
            if(solution != null) {
                System.out.println(solution.get(car.name()));
            } else {
                System.out.println("No car that meet all needs");
            }


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
