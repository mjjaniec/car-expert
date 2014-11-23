/*
 * When prolog tries to prove a car then it looks through it's traits
 * when a trait is checked then: 
 *	if trait was required then 
 *		it is accepted and counted, 
 *	else - the trait was not required -
 *		it is silently accepted.
 *
 * At the end if number of counted traits is the same as number of
 * requireements - then car is accepted.
 *
 ***********************************************************************/

:- dynamic 
	checked/1, 
	required_passengers/1,
	required_cargo/1,
	required_mass/1,
	required_type/1,
	required_power/1,
	required_chassis/1,
	required_gearbox/1,
	required_category/1,
	required_feature/1,
	required_equipment/1.

max_passengers(_):- \+ required_passengers(_), !.
max_passengers(Actual):- required_passengers(Required), Actual @>= Required, inc_checked, !.
max_passengers(_):- required_passengers(_), clear_checked, false.

max_cargo(_):- \+ required_cargo(_), !.
max_cargo(Actual):- required_cargo(Required), Actual @>= Required, inc_checked, !.
max_cargo(_):- required_cargo(_), clear_checked, false.

mass(_):- \+ required_mass(_), !.
mass(Actual):- required_mass(Required), Actual @=< Required, inc_checked, !.
mass(_):- required_mass(_), clear_checked, false.

power(Power):- \+ required_power(Power), !.
power(Power):- required_power(Power), inc_checked.

chassis(Chassis):- \+ required_chassis(Chassis), !.
chassis(Chassis):- required_chassis(Chassis), inc_checked.

gearbox(Gearbox):- \+ required_gearbox(Gearbox), !.
gearbox(Gearbox):- required_gearbox(Gearbox), inc_checked.

category(Category):- \+ required_category(Category), !.
category(Category):- required_category(Category), inc_checked.

feature(Feature):- \+ required_feature(Feature), !.
feature(Feature):- required_feature(Feature), inc_checked.




inc_checked:- checked(N), retract(checked(N)), N1 is N + 1, assertz(checked(N1)).

clear_all:- clear_checked, clear_traits.
clear_checked:- retractall(checked(_)), assertz(checked(0)).
clear_traits:- retractall(required_passengers(_)),
				retractall(required_cargo(_)),
				retractall(required_mass(_)),
				retractall(required_type(_)), 
				retractall(required_equipment(_)),
				retractall(required_power(_)), 
				retractall(required_chassis(_)),
				retractall(required_gearbox(_)),
				retractall(required_feature(_)),
				retractall(required_category(_)).

require_trait(Trait):- Trait = max_passengers(Number), assertz(required_passengers(Number)), !.
require_trait(Trait):- Trait = max_cargo(Number), assertz(required_cargo(Number)), !.
require_trait(Trait):- Trait = mass(Number), assertz(required_mass(Number)), !.
require_trait(Trait):- Trait = type(Type), assertz(required_type(Type)), !.
require_trait(Trait):- Trait = equipment(Level), assertz(required_equipment(Level)), !.
require_trait(Trait):- Trait = power(Power), assertz(required_power(Power)), !.
require_trait(Trait):- Trait = chassis(Chassis), assertz(required_chassis(Chassis)), !.
require_trait(Trait):- Trait = gearbox(Gearbox), assertz(required_gearbox(Gearbox)), !.
require_trait(Trait):- Trait = feature(Feature), assertz(required_feature(Feature)), !.
require_trait(Trait):- Trait = category(Category), assertz(required_category(Category)), !.

require_traits([]):- true.
require_traits([H_trait | T_trait]):- require_trait(H_trait), require_traits(T_trait).


all_requirements_met(Traits):- checked(N), length(Traits, L), clear_checked, N =:= L.

get_cars(Traits, OUT_cars):- 
		clear_all,
		require_traits(Traits),
		car(OUT_cars),
		all_requirements_met(Traits).
