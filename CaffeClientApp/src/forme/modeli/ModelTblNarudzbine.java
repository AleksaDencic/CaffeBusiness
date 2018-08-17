/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeli;

import domen.Narudzbina;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alekd_000
 */
public class ModelTblNarudzbine extends AbstractTableModel {
    
    List<Narudzbina> narudzbine;
    String columns[] = {"NarudzbinaID", "Datum", "Ukupna vrednost", "Stornirana", "Konobar", "Sto"};

    public ModelTblNarudzbine(List<Narudzbina> narudzbine) {
        this.narudzbine = narudzbine;
    }  

    @Override
    public int getRowCount() {
        return narudzbine.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Narudzbina narudzbina = narudzbine.get(rowIndex);
        switch(columnIndex){
            case 0:
                return narudzbina.getNarudzbinaID();
            case 1:
                return narudzbina.getDatum();
            case 2:
                return narudzbina.getUkupnaVrednost();
            case 3:
                if(narudzbina.isStornirana()) return "da";
                else return "ne";
            case 4:
                return narudzbina.getKonobar().toString();
            case 5:
                return narudzbina.getSto().toString();
            default:
                return "N/A";
        }
    }

    public void obrisiNarudzbinu(int izabraniRed) {
        if(izabraniRed >= 0 || izabraniRed < narudzbine.size()){
            narudzbine.remove(izabraniRed);
            fireTableDataChanged();
        } 
    }

    public void dodajNarudzbinu(Narudzbina narudzbina) {
        narudzbine.add(narudzbina);
        fireTableDataChanged();
    }

    public void stornirajNarudzbinu(int izabraniRed) {
        narudzbine.get(izabraniRed).setStornirana(true);
        fireTableDataChanged();
    }
    
}
