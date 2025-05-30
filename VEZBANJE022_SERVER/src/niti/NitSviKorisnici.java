/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovana
 */
public class NitSviKorisnici extends Thread{
    private ServerskaForma sf;

    public NitSviKorisnici(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {            
            try {
                sf.popuniTabeluKorisnici();
                System.out.println("klasa NitSviKorisnici");
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitSviKorisnici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
