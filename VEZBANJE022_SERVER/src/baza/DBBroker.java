/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Poruka;
import model.User;
/**
 *
 * @author Jovana
 */
public class DBBroker {

    public Admin login(Admin a) {
        try {
            String upit = "SELECT * FROM ADMIN WHERE email=? AND lozinka=?;";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, a.getEmail());
            ps.setString(2, a.getLozinka());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                a.setId(id);
                a.setIme(ime);
                a.setPrezime(prezime);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User loginKorisnik(User u) {
         try {
            String upit = "SELECT * FROM KORISNIK WHERE username=? AND lozinka=?;";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getLozinka());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                u.setId(id);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean postojiUsername(String username) {
        try {
            String upit = "SELECT * FROM KORISNIK WHERE username=?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean sacuvajKorisnika(User u) {
        try {
            String upit = "INSERT INTO korisnik (username,lozinka) VALUES (?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getLozinka());
            /*ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
                return true;*/
            int rows = ps.executeUpdate();
            if(rows>0){
                Konekcija.getInstance().getConnection().commit();
                return true;
            }
            
        } catch (SQLException ex) {
            try {
                Konekcija.getInstance().getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Poruka> ucitajSvePoruke(int offset) {
        List<Poruka> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM poruka p JOIN korisnik k1 ON (p.posiljalac=k1.id) \n" +
                    "JOIN korisnik k2 ON (p.primalac=k2.id) LIMIT 5 OFFSET "+offset;
            System.out.println(upit);
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                //posiljllac
                int id = rs.getInt("k1.id");
                String username = rs.getString("k1.username");
                String lozinka = rs.getString("k1.lozinka");
                User posiljalac = new User(id, username, lozinka);
                //primalac
                int id2 = rs.getInt("k2.id");
                String username2 = rs.getString("k2.username");
                String lozinka2 = rs.getString("k2.lozinka");
                User primalac = new User(id2, username2, lozinka2);
                //poruka
                int idporuka = rs.getInt("p.id");
                String text = rs.getString("p.tekst"); 
                //datum
                Timestamp datumets = rs.getTimestamp("datum");
                java.util.Date datum = new java.util.Date(datumets.getTime());
                Poruka p = new Poruka(idporuka, posiljalac, primalac, datum, text);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean posaljiSvima(Poruka p) {
        try {
            //uzmi listu svih korisnika
            List<User> primaoci = vratiSveKorisnike();
            String upit = "INSERT INTO poruka (posiljalac,primalac,tekst,datum) VALUES (?,?,?,?);";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, p.getPosiljalac().getId());
            //primalac- petlja - 2 
            ps.setString(3, p.getTekst());
            //datum
            Timestamp datumts = new Timestamp(p.getDatum().getTime());
            ps.setTimestamp(4, datumts);
            for (User user : primaoci) {
                //svima osim sebi
                if(user.getId()!=p.getPosiljalac().getId()){
                    ps.setInt(2, user.getId());
                    ps.addBatch();
                }
                
            }
            int[] affectedRows = ps.executeBatch();
            if(affectedRows.length>0){
                Konekcija.getInstance().getConnection().commit();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<User> vratiSveKorisnike() {
        List<User> lista =new ArrayList<>();
        try {
            String upit = "SELECT * FROM korisnik";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String lozinka = rs.getString("lozinka");
                User korisnik = new User(id, username, lozinka);
                
                lista.add(korisnik);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean poslajijendomKorisniku(Poruka poruka) {
        try {
            String upit = "INSERT INTO poruka (posiljalac,primalac,tekst,datum) VALUES (?,?,?,?);";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, poruka.getPosiljalac().getId());
            ps.setString(3, poruka.getTekst());
            //datum
            Timestamp datumts = new Timestamp(poruka.getDatum().getTime());
            ps.setTimestamp(4, datumts);
            ps.setInt(2, poruka.getPrimalac().getId());
            
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                Konekcija.getInstance().getConnection().commit();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Poruka> vratiPorukeKlijenta(User primalac) {
         List<Poruka> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM poruka p JOIN korisnik k1 ON (p.posiljalac=k1.id) \n" +
                    "JOIN korisnik k2 ON (p.primalac=k2.id) WHERE k2.id="+primalac.getId();
            System.out.println(upit);
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                //posiljllac
                int id = rs.getInt("k1.id");
                String username = rs.getString("k1.username");
                String lozinka = rs.getString("k1.lozinka");
                User posiljalac = new User(id, username, lozinka);
                //primalac - imam ga
                //poruka
                int idporuka = rs.getInt("p.id");
                String text = rs.getString("p.tekst"); 
                //datum
                Timestamp datumets = rs.getTimestamp("datum");
                java.util.Date datum = new java.util.Date(datumets.getTime());
                Poruka p = new Poruka(idporuka, posiljalac, primalac, datum, text);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    
    
}
