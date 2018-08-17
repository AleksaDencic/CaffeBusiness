/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeli;

import domen.Artikal;
import domen.Konobar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alekd_000
 */
public class ModelTblKonobar extends AbstractTableModel {
    
    List<Konobar> konobari;
    String[] columns = {"Ime", "Prezime", "Adresa", "Broj telefona", "Username"};

    public ModelTblKonobar(List<Konobar> konobari) {
        this.konobari = konobari;
    }

    public List<Konobar> getKonobari() {
        return konobari;
    }
    
    @Override
    public int getRowCount() {
        return konobari.size();
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
        Konobar konobar = konobari.get(rowIndex);
        switch(columnIndex){
            case 0:
                return konobar.getIme();
            case 1:
                return konobar.getPrezime();
            case 2:
                return konobar.getAdresa();
            case 3:
                return konobar.getBrojTelefona();
            case 4: 
                return konobar.getUsername();
            default:
                return "N/A";
        }
    }

    public void dodajKonobara(Konobar konobar) {
        konobari.add(konobar);
        fireTableDataChanged();
    }    

    public void obrisiKonobara(int izabraniRed) {
        if(izabraniRed >= 0 || izabraniRed < konobari.size()){
            konobari.remove(izabraniRed);
            fireTableDataChanged();
        }  
    }

    public void updateKonobar(Konobar konobar) {
        for (Konobar k : konobari) {
            if(k.getKonobarID() == konobar.getKonobarID()){
                k = konobar;
                fireTableDataChanged();
            }
        }
    }

}
