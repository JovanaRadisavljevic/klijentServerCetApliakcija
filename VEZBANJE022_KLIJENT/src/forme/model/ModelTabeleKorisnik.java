/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Poruka;
import model.User;

/**
 *
 * @author Jovana
 */
public class ModelTabeleKorisnik extends AbstractTableModel{
   private List<User> lista = new ArrayList<>();
   private String[] kolone = {"id", "username"};
   
   public ModelTabeleKorisnik(List<User> lista){
       this.lista=lista;
   }
   
   @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:return p.getId();
            case 1:return p.getUsername();
           
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<User> getLista() {
        return lista;
    }

    
    
    
}
