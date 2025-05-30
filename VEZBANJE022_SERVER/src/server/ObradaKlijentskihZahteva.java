/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Poruka;
import model.User;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Jovana
 */
public class ObradaKlijentskihZahteva extends Thread {
     private Socket s;
     private User ulogovan=null ;
     private boolean odjavljen=false;
     private boolean  kraj =false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
             while (!kraj) {
                KlijentskiZahtev kz = primiZahtev();
                ServerskiOdgovor so = new ServerskiOdgovor();
                switch (kz.getOperacija()) {
                    case operacije.Operacije.LOGIN:
                        User u = (User) kz.getParametar();
                        u=controller.Controller.getInstance().loginKorisnik(u);
                        if(u!=null && u.getId()!=-2){
                            ulogovan=u;
                        }
                        so.setOdgovor(u);
                        break;
                    case operacije.Operacije.POSALJI_SVIMA:
                        Poruka p = (Poruka) kz.getParametar();
                        boolean uspeh =controller.Controller.getInstance().posaljiSvima(p);
                        
                        so.setOdgovor(uspeh);
                        break;
                    case operacije.Operacije.VRATI_ULOGOVANE:
                        List<User> ulogovani = controller.Controller.getInstance().getUlogovaniKorisnici();
                        so.setOdgovor(ulogovani);
                        break;
                    case operacije.Operacije.POSALJI_JEDNOM_KORISNIKU:
                        Poruka poruka = (Poruka) kz.getParametar();
                        boolean uspesnoPoslato = controller.Controller.getInstance().poslajijendomKorisniku(poruka);
                        so.setOdgovor(uspesnoPoslato);
                        break;
                    case operacije.Operacije.VRATI_PORUKE_ODBRANOG_KLIJENTA:
                        User primalac = (User) kz.getParametar();
                        List<Poruka> porukeKorisnika = controller.Controller.getInstance().vratiPorukeKlijenta(primalac);
                        so.setOdgovor(porukeKorisnika);
                        break;
                    case operacije.Operacije.ODJAVA_OD_STRANE_SERVERA:
                        so.setOdgovor(odjavljen);
                        if(odjavljen && ulogovan!=null){
                            //izbaci ga iz liste
                            controller.Controller.getInstance().getUlogovaniKorisnici().remove(ulogovan);
                            controller.Controller.getInstance().obrisiKorisnika(ulogovan);
                            kraj=true;
                            /*try {
                                s.close();
                            } catch (IOException ex) {
                                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                        }
                        break;
                    case operacije.Operacije.ODJAVA_KLIJENT:
                            //izbaci ga iz listi
                            controller.Controller.getInstance().getUlogovaniKorisnici().remove(ulogovan);
                            controller.Controller.getInstance().obrisiKorisnika(ulogovan);
                            kraj=true;
                            /*try {
                                s.close();
                            } catch (IOException ex) {
                                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                        break;
                    
                    default:
                        throw new AssertionError();
                }
                posaljiOdgovor(so);
            }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (EOFException e) {
            System.out.println("klijent odjavljen");
        }
        catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        }catch (EOFException e) {
            System.out.println("klijent odjavljen");
        }
        catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUlogovan() {
        return ulogovan;
    }

    public void odjavi() {
       odjavljen=true;
    }
    
}
