U Data base fajlu imate SQL kod koji je potrebno pokrenuti u MySQL bazi podataka radi daljeg testiranja projekta.
Prilikom prvog pokretanja programa u dev rezimu rada tabela ce se popunuti sa nekim odredjenim podacima.
To je definisano pomocu liquibase-a (resources/liquibase/master.xml), a dodatno mozete i Vi popunjavati tabelu ili rucno ili uz pomoc api servisa.

Sve vrste podataka koji se prosledjuju mozete naciu kodu po nazivu koji je dat, da ne bih ovdje previse duzio.
Sve api servise mozete na najlaksi nacin testirati u postman aplikaciji nakon pokretanja projekta.

User-----------------------------------------				
(post) - http://localhost:8080/api/user/store				(prosledjujemo UserWithDetailDTO u jason formatu preko bodija)
(get) - http://localhost:8080/api/user/all				(dobijamo sve korisnike)
(get) - http://localhost:8080/api/user/exists/by-username/{username}	(prosledjujemo username od UserDTO-a, vraca mapu string boolean)
(put) - http://localhost:8080/api/user/4/add-role			(prosledjujemo id od UserDTO-a kojem dodjeljujemo RoleDTO, u jason format preko bodija saljemo podatke)
(get) - http://localhost:8080/api/user/custom-search
(Prosledjujemo bilo koji, ili svaki parametar i po njemu ce biti pretrazivanje nad tabelama User i role.
Kao kljuceve za path varijable koristimo promjenjive iz klase UserSearch koju mozete naci u kodu. )

Role-----------------------------------------
(post) - http://localhost:8080/api/role/store 		(prosledjujemo RoleDTO u jason formatu preko bodija)
(put) - http://localhost:8080/api/role/update/{id} 	(path varijabla je id role koju abdejtujemo)
(get) - http://localhost:8080/api/role/one/{id}		(path varijabla je id role koju zelimo da prikazemo)

(get) - http://localhost:8080/api/role/search/by-name-or-desc
(prosledjuje se parameter sa kojim pocinje ili naziv ili deskripcija (pretrazivanje po imenu ili deskripciji), kljuc je param)
U testnom dijelu ima integracionih testova za RoleRepository

Country-------------------------------------
(get) - http://localhost:8080/api/country/pageable?page=0&size=3		(vracamo pageable)
(get) - http://localhost:8080/api/country/pageable/better/method?page=0&size=3	(vracamo listu i imamo brzi odziv na vece kolicine podataka)
//(?page=0&size=3 ovo je samo primjer za Pageable, moze se opciono dodati i sortiranje kao parametar)

(post) - http://localhost:8080/api/country			(Prosledjujemo CountryDTO u bodiju koju zelimo da dodamo)
(put) - http://localhost:8080/api/country/update		(Prosledjujemo CountryDTO sa id-em role koju zelimo da abdejtujemo u bodiju)
(delete) - http://localhost:8080/api/country/{id}		(path varijabla id drzave koju zelimo da izbrisemo)

Fake--------------------------------------
//Odje nema repository sloja vec servi komunicira sa https://fakestoreapi.com/
(get) - http://localhost:8080/api/fake/all		(izlistava sve)
(post) - http://localhost:8080/api/fake/insert		(prosledjujemo ProductFakeInsertDTO)
(delete) - http://localhost:8080/api/fake/{id}		(parh varijabla je id ProductFakeDTO kojeg zelimo da obrisemo)
(get) - http://localhost:8080/api/fake/card/{id}	(parh varijabla je id CartExtendedDTO kojeg zelimo da prikazemo)

City--------------------------------------
Odje su korisceni custom validatori i custom exception, i svaka metoda u city koristi kesiranje podataka

(post) - http://localhost:8080/api/city/custom-validator/custom-exception/create
(prosledjujemo CityDTO pa se nad njim vrsi custom validacija i ako nesto nije u redu ide custom exception)

(get) - http://localhost:8080/api/city/caching-if-id>2/{id}				(path varijabla id grada i kesiranje se radi pod odredjenim uslovom)
(get) - http://localhost:8080/api/city/get-all						(kesiranje svih podataka)
(Delete) - http://localhost:8080/api/city/{id}						(brisanje iz kesa i iz baze)
(get) - http://localhost:8080/api/city/evict-all-cache					(brisanje citavog city kesa)
U testnom dijelu ima integracionih testova za CityRepository

authenticate-------------------------------
(post) - http://localhost:8080/api/authenticate/register	(Kada na ovaj nacin dodamo korisnika, njegov password se u tabeli kodira BCrypt algoritmom. prosledjujemo UserCreateDTO)
(post) - http://localhost:8080/api/authenticate/login		(Prosledjujemo UserLoginDTO u bodiju i dobijamo jwt token koji kasnije prosledjujemo u svakoj metodi u hederu
(kljuc: Authorisation, vrijednost: Bearer pa_token) da bi mogli da joj pristupimo.
Ova provjera je iskljucena radi lakseg testiranja. Ako zelite da je aktivirate, u SecyrityConfiguration odkomentarisite zakomentarisani dio koda.)


	
