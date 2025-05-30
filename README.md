"# klijentServerCetApliakcija" 

# Chat Sistem Klijent-Server

## Opis

Ova aplikacija predstavlja jednostavan **klijent-server sistem za razmenu poruka** između korisnika. Klijentska strana omogućava korisnicima da međusobno komuniciraju, dok serverska strana upravlja konekcijama, porukama i statusima korisnika.

---

## Funkcionalnosti

### Klijentska aplikacija

- ✅ Slanje poruke **svim korisnicima** u sistemu
- ✅ Slanje poruke **jednom prijavljenom korisniku**
- ✅ **Odjava** sa servera jednim klikom
- ✅ Prikaz:
  - Poslednje **3 primljene poruke**
  - **Sve poruke** koje je korisnik primio
  - Lista **aktivnih korisnika**

---

### Serverska aplikacija

- ✅ **Pokretanje** i **zaustavljanje** servera
- ✅ **Odjava svih korisnika** iz sistema
- ✅ **Odjava izabranog korisnika** (klikom na korisnika u listi)
- ✅ Prikaz poruka iz baze:
  - Prikazuje se **po 5 poruka** svaki put kada se pritisne **Enter** (kada je fokus na tabeli)
- ✅ **Unos novog korisnika** direktno u bazu putem serverske forme

---

## Tehničke napomene

- Server koristi **višedretveno programiranje** za podršku više istovremenih korisnika.
- Komunikacija između klijenta i servera odvija se putem **soketa**.
- Poruke se čuvaju u **bazi podataka**, a prikaz se optimizuje za efikasan pregled.


