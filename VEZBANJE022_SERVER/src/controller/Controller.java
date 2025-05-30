/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Poruka;
import model.User;
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author Jovana
 */
public class Controller {
    private static Controller instance;
    List<User> ulogovaniKorisnici = new ArrayList<>();
    List<ObradaKlijentskihZahteva> povezaniKlijenti = new ArrayList<>();
    private DBBroker dbb;
    
    private Controller() {
        dbb=new DBBroker();
    }

    public List<User> getUlogovaniKorisnici() {
        return ulogovaniKorisnici;
    }

    public List<ObradaKlijentskihZahteva> getPovezaniKlijenti() {
        return povezaniKlijenti;
    }
    
    
    
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }

    public Admin login(Admin a) {
        return dbb.login(a);
    }

    public User loginKorisnik(User u) {
        u= dbb.loginKorisnik(u);
        if(u!=null){ 
            if(ulogovaniKorisnici.contains(u)){
                u.setId(-2); //ako je vec ulogovan
                return null;
            }
            ulogovaniKorisnici.add(u);
        }
        return u;
    }

    public boolean postojiUsername(String username) {
        return dbb.postojiUsername(username);
    }

    public boolean sacuvajKorisnika(User u) {
        return dbb.sacuvajKorisnika(u);
    }

    public List<Poruka> ucitajSvePoruke(int offset) {
        return dbb.ucitajSvePoruke(offset);
    }

    public boolean posaljiSvima(Poruka p) {
        return dbb.posaljiSvima(p);
    }

    public List<User> vratiSveKorisnike() {
        return dbb.vratiSveKorisnike();
    }

    public boolean poslajijendomKorisniku(Poruka poruka) {
        return dbb.poslajijendomKorisniku(poruka);
    }

    public List<Poruka> vratiPorukeKlijenta(User primalac) {
        return dbb.vratiPorukeKlijenta(primalac);
    }

    public void odjaviKorisnika(User korisnik) {
        for (ObradaKlijentskihZahteva nit : povezaniKlijenti) {
            if(nit.getUlogovan()!=null && nit.getUlogovan().equals(korisnik)){
                //posalji izabranom korisniku poruku odjave
                nit.odjavi();
                return;
            }
        }
    }

    public void obrisiKorisnika(User ulogovan) {
        /*for (ObradaKlijentskihZahteva nit : povezaniKlijenti) {
            if(nit.getUlogovan()!=null && nit.getUlogovan().equals(ulogovan)){
                povezaniKlijenti.remove(nit);
            }
        }  OVAJ KOD BACA GRESKU JER NE MOGU DA IZBACIM NIT DOK VRSIM ITERACIJU*/
        povezaniKlijenti.removeIf(nit -> 
        nit.getUlogovan() != null && nit.getUlogovan().equals(ulogovan));
    }

    public void odjaviSveKlijente() {
        for (ObradaKlijentskihZahteva nit : povezaniKlijenti) {
            if(nit.getUlogovan()!=null){
                nit.odjavi();
            }
        }
    }



   
}
