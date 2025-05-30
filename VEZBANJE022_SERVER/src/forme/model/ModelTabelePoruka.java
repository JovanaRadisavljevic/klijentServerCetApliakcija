/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Poruka;

/**
 *
 * @author Jovana
 */
public class ModelTabelePoruka extends AbstractTableModel{
   private List<Poruka> lista = new ArrayList<>();
   private String[] kolone = {"primalac", "posiljalac","tekst","datum"};
   
   public ModelTabelePoruka(List<Poruka> lista){
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
        Poruka p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:return p.getPrimalac().getUsername();
            case 1:return p.getPosiljalac().getUsername();
            //samo prvih 20 karaktera
            case 2:
                if(p.getTekst().length()<=20)
                    return p.getTekst();
                else
                    return p.getTekst().substring(0,20);
            case 3:return p.getDatum();
                
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Poruka> getLista() {
        return lista;
    }
    
    
    
}
