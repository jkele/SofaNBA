# SofaNBA

Ovaj repozitorij korišten je za rješavanje i predaju završnog projekta SofaScore Android akademije. 
Zavšni projekt sastoji se od Android mobilne aplikacije za prikaz podataka NBA igrača, timova, utakmica i statistika.

### Funkcionalnosti
- **Prijava**
  - Korisnik se može prijaviti u aplikaciju korištenjem svojeg Google računa
- **Pregled liste timova i igrača**
  - Nakon prijave u aplikaciju, korisniku se prikazuje lista svih igrača i timova NBA lige
- **Favoriti**
  - Igrače i timove moguće je spremiti kao favorite, koji se zatim prikazuju u zasebnoj listi favorita
- **Igrač**
  - Klik na nekog igrača otvara novi prozor koji sadrži detalje, statistiku i odigrane utakmice za odabranog igrača
  - Korisnik može dodati i obrisati slike za igrača
- **Tim**
  - Klik na neki tim otvara novi prozor koji prikazuje detalje i utakmice za odabrani tim
  - Utakmice je moguće filtrirati prema sezoni i fazi natjecanja
  - Sadrži i Google Maps proširenje za prikaz lokacije stadiona tima
- **Sezone**
  - Korisniku se prikazuje lista utakmica za trenutnu sezonu, a moguće je i filtriranje prema sezoni i fazi natjecanja
- **Utakmica**
  - Klikom na neku utakmicu korisniku se prikazuju osnovni detalji, statistike timova i YouTube highlights-i za odabranu utakmicu
  - Korisnik može dodati i obrisati YouTube highlight-e
  - Klikom na neki od highlight-a otvara se odabrani video u YouTube aplikaciji ili u web pregledniku ukoliko YouTube aplikacija nije dostupna
- **Postavke**
  - Unutar postavki, korisnik može obrisati sve favorite i pregledati informacije o aplikaciji i developeru
  

### Tehnologije korištene u projektu
- Retrofit za networking
- Room baza podataka
- Firebase
- Google Maps
- LiveData
- Kotlin Coroutines
- Paging

### API dokumentacija
- https://www.balldontlie.io/#introduction
- https://academy-2022.dev.sofascore.com/docs
