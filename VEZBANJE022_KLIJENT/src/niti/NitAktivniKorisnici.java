/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import fomre.KlijentskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovana
 */
public class NitAktivniKorisnici extends Thread{
    private KlijentskaForma kf;

    public NitAktivniKorisnici(KlijentskaForma kf) {
        this.kf = kf;
    }

    @Override
    public void run() {
        while (true) {            
            kf.popuniTabelu();//aktivni korisnici
            kf.popuniTabelePoruke();
            kf.proveriDaLiJeKorisnikOdjavljenOdStraneServera();
            System.out.println("klasa NitAktivniKorisnici");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitAktivniKorisnici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
