renault_clio.       /* city car */
jeep_renegade.      /* off-road car */
solaris_urbino.     /* city bus */
scania_v8.          /* big cargo truck */


/*max_passengers(car, int)*/
max_passengers(renault_clio, 5).
max_passengers(jeep_renegade, 5).
max_passengers(solaris_urbino, 120).
max_passengers(scania_v8, 3).

/*cargo_capacity(car, int[m3]) */
cargo_capacity(renault_clio, 1.2).
cargo_capacity(solaris_urbino, 0).
cargo_capacity(scania_v8, 80).


personal(renault_clio).
personal(jeep_renegade).
off_road(jeep_renegade).

fit_passengers(Car, Passengers):- max_passengers(Car, P), Passengers =< P.
fit_cargo(Car, Cargo):- cargo_capacity(Car, X), Cargo =< X.

best_car(Car, Passengers, Cargo):- fit_passengers(Car, Passengers), fit_cargo(Car, Cargo).