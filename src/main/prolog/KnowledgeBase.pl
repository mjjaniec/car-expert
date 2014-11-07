:- dynamic
    max_passengers/1,
    cargo_capacity/1,
    transport/0,
    personal/0,
    off_road/0.


car(renault_clio):- fit_passengers(5),
                    fit_cargo(1.2),
                    personal.

car(jeep_renegade):- or(personal, off_road),
                     fit_passengers(5),
                     fit_cargo(0).

car(solaris_urbino):- transport,
                      fit_passengers(120),
                      fit_cargo(0).

car(scania_v8):- transport,
                 fit_passengers(3),
                 fit_cargo(80).

car(test):- transport,
            fit_passengers(0),
            fit_cargo(0).

or(A, B):- A;B.
fit_passengers(Passengers):- max_passengers(P), P =< Passengers.
fit_cargo(Cargo):- cargo_capacity(C), C =< Cargo.

int_assertz(T):- T; \+ T, assertz(T).

best_car([], X):- car(X).
best_car([H|T], Car):- int_assertz(H), best_car(T, Car).

