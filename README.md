# Systemy Expertowe. 

## Autorzy
* Bartosz Polnik
* Michał Janiec

## Temat 
Baza wiedzy dotycząca samochodów.

## Żródła PROLOG
`src/main/prolog`

## Instrukcja uruchomienia

#### wymagania:
* Java
* SWI-Prolog
* Maven
* Git

#### Kroki
* instalujemy powyższe komponenty
* `git colne https://github.com/mjjaniec/car-expert`
* Biblioteka SWI musi być widoczna.
 * Na linuxie: `export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:[path to swipl]`
 * u mnie: `export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib/swipl-6.6.6/lib/x86_64-linux/`
 * Na windows: chyba wystarczy skopiować plik `dll` do folderu car-expert, lub dodanie folderu zawierajcego dll do zmiennej `PATH` 
* `cd car-expert`
* `mvn jetty:run`
* Aplikcaja jest dostępna pod adresem `http://localhost:8080`

