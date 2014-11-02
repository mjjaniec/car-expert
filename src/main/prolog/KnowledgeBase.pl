car(renault_clio).       /* city car */
car(jeep_renegade).      /* off-road car */
car(solaris_urbino).     /* city bus */
car(scania_v8).          /* big cargo truck */


/*max_passengers(car, int)*/
max_passengers(renault_clio, 5).
max_passengers(jeep_renegade, 5).
max_passengers(solaris_urbino, 120).
max_passengers(scania_v8, 3).

/*cargo_capacity(car, int[m3]) */
cargo_capacity(renault_clio, 1.2).
cargo_capacity(solaris_urbino, 0).
cargo_capacity(scania_v8, 80).


personal(car(renault_clio)).
personal(car(jeep_renegade)).
off_road(car(jeep_renegade)).

fit_passengers(Passengers, X):- max_passengers(Car, P), Passengers =< P, X = car(Car).
fit_cargo(Cargo, X):- cargo_capacity(Car, C), Cargo =< C, X = car(Car).

best_car([], X):- car(Y), X = car(Y).
best_car([H|T], Car):- H, best_car(T, Car).

