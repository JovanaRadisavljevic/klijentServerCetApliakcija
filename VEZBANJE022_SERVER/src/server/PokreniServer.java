/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovana
 */
public class PokreniServer extends Thread {
    private ServerSocket serverskiSoket;
    private boolean kraj = false;

    @Override
    public void run() {
        try {
            serverskiSoket = new ServerSocket(9000);
            System.out.println("SERVER CEKA...");
            while (!kraj) {
                Socket s = serverskiSoket.accept();
                System.out.println("KLIJENT JE POVEZAN");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                controller.Controller.getInstance().getPovezaniKlijenti().add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustavi() {
        kraj=true;
        try {
            serverskiSoket.close();
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
