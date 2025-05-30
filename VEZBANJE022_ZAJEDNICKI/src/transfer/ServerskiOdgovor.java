/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Jovana
 */
public class ServerskiOdgovor implements Serializable {
    private Object odgovor;
    private int operacija;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, int operacija) {
        this.odgovor = odgovor;
        this.operacija = operacija;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }


    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
}
