/*
*  Each car has
*       + 3 numeric features - max_passengers, cargo_capacity [m3], mass [T]
*       + type, power, chassis, gearbox, and any number of features.
*
*  type:
*       + sport
*       + personal
*       + off_road
*       + transport_cargo
*       + transport_passengers
*
*  power: (exactly one)
*       + high
*       + medium
*       + low
*
*  chassis: (exactly one)
*       + high
*       + low
*
*  gearbox:
*       + manual
*       + automatic
*
*  feature:
*       + gps
*       + power_steering
*       + heated_seats
*       + four_x_four
*       + radio
*       + air_condition
*       + central_locking
*       + tinted_windows
*/


:- dynamic
    max_passengers/1,
    cargo_capacity/1,
    transport/0,
    personal/0,
    off_road/0.




car(bmw_3):- max_passengers(5), max_cargo(0.46),  mass(1.5),
             or([type(sport), type(personal)]),
             or([gearbox(manual), gearbox(automatic)]),
             equipment(exclusive), feature(tinted_windows).

car(fiat_panda):- max_passengers(4), max_cargo(0.2), mass(0.8),
                  type(personal), equipment(basic), power(low),
                  gearbox(manual), chassis(low).


car(jeep_renegade):- max_passengers(5), max_cargo(0.35), mass(2.4),
                     or([type(personal), type(off_road)]),
                     or([gearbox(manual), gearbox(automatic)]),
                     equipment(high), feature(leather_upholstery).


car(mercedes_benz_atego):- max_passengers(2), max_cargo(30), mass(7),
                           type(transport_cargo), gearbox(manual),
                           equipment(standard), feature(power_steering), power(medium).


car(mercedes_benz_sprinter):- max_passengers(2), max_cargo(12), mass(3),
                              type(transport_cargo), gearbox(manual),
                              equipment(high), feature(gps), power(medium).


car(renault_clio):- max_passengers(5), max_cargo(0.26), mass(0.9)
                    type(personal), equipment(high),
                    or([gearbox(manual), gearbox(automatic)]),
                    feature(low_chassis), power(medium1), chassis(low).


car(scania_v8):- max_passengers(3), max_cargo(90), mass(15)
                 type(transport_cargo), gearbox(automatic),
                 equipment(basic),
                 feature(power_steering),
                 feature(gps), power(high).


car(solaris_urbino):- max_passengers(120), max_cargo(0), mass(8),
                      type(transport_passengers), chassis(low),
                      equipment(standard),
                      feature(gps), gearbox(automatic), power(medium).


car(suzuki_jimny):- max_passengers(5), max_cargo(0.12), mass(1.1)
                    or([type(off_road), type(personal)]),
                    equipment(standard), gearbox(manual), feature(power_steering).

car(volkswagen_lt46):- max_passengers(26), max_cargo(0.6), mass(2.8),
                       type(transport_passengers),
                       chassis(high), equipment(high),
                       or([gearbox(manual), gearbox(automatic)]).


equipment(exclusive):- equipment(high), feature(leather_upholstery), feature(heated_seats).
equipment(high):- equipment(basic), feature(air_condition), feature(power_steering).
equipment(standard):- feature(radio), feature(central_locking).
equipment(low):- true.

type(personal):- feature(easy_to_park), category(B).
type(sport):- power(high), feature(four_x_four), chassis(low_chassis).
type(off_road):- power(high), feature(four_x_four), feature(gps), chassis(high).
type(transport_cargo):- category(C), chassis(high).
type(transport_passengers):- category(D).


or([]):- false.
or([H|T]):- H; or(T).
fit_passengers(Passengers):- max_passengers(P), P =< Passengers.
fit_cargo(Cargo):- cargo_capacity(C), C =< Cargo.

int_assertz(T):- T; \+ T, assertz(T).

best_car([], X):- car(X).
best_car([H|T], Car):- int_assertz(H), best_car(T, Car).

cleanup:- cleanup([max_passengers, cargo_capacity, transport, personal, off_road]).

cleanup([]).
cleanup([H|T]):- retractall(H), cleanup(T).

