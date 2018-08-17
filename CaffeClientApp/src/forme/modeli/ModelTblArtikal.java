/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeli;

import domen.Artikal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alekd_000
 */
public class ModelTblArtikal extends AbstractTableModel {
    
    List<Artikal> artikli;
    String columns[] = {"Naziv artikal", "Jedinica mere", "Cena"};

    public ModelTblArtikal(List<Artikal> artikli) {
        this.artikli = artikli;
    }

    @Override
    public int getRowCount() {
        return artikli.size();
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
        Artikal artikal = artikli.get(rowIndex);
        switch(columnIndex){
            case 0:
                return artikal.getNazivArtikla();
            case 1:
                return artikal.getJedinicaMere();
            case 2:
                return artikal.getCena();
            default:
                return "N/A";
        }
    }
    
    public void dodajArtikal(Artikal artikal) {
        artikli.add(artikal);
        fireTableDataChanged();
    }  

    public void obrisiArtikal(int izabraniRed) {
        if(izabraniRed >= 0 || izabraniRed < artikli.size()){
            artikli.remove(izabraniRed);
            fireTableDataChanged();
        }        
    }

    public void updateArtikal(Artikal artikal) {
        for (Artikal a : artikli) {
            if(a.getArtikalID() == artikal.getArtikalID()){
                a = artikal;
                fireTableDataChanged();
            }
        }
    }
    
}
