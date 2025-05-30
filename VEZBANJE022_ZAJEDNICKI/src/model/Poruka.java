/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jovana
 */
public class Poruka implements Serializable {
    private int id;
    private User posiljalac;
    private User primalac;
    private Date datum;
    private String tekst;

    public Poruka() {
    }

    @Override
    public String toString() {
        return "Poruka{" + "posiljalac=" + posiljalac + ", primalac=" + primalac + ", datum=" + datum + ", tekst=" + tekst + '}';
    }

    public Poruka(int id, User posiljalac, User primalac, Date datum, String tekst) {
        this.id = id;
        this.posiljalac = posiljalac;
        this.primalac = primalac;
        this.datum = datum;
        this.tekst = tekst;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poruka other = (Poruka) obj;
        if (!Objects.equals(this.tekst, other.tekst)) {
            return false;
        }
        if (!Objects.equals(this.posiljalac, other.posiljalac)) {
            return false;
        }
        if (!Objects.equals(this.primalac, other.primalac)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(User posiljalac) {
        this.posiljalac = posiljalac;
    }

    public User getPrimalac() {
        return primalac;
    }

    public void setPrimalac(User primalac) {
        this.primalac = primalac;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
