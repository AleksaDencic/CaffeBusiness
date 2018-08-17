/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.form.modeli;

import client.Klijent;
import domen.Konobar;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import server.thread.ThreadClient;

/**
 *
 * @author alekd_000
 */
public class ModelTblKlijenti extends AbstractTableModel {
    
    List<Klijent> klijenti;
    String columns[] = {"Rbr", "Klijent"};

    public ModelTblKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }   

    @Override
    public int getRowCount() {
        return klijenti.size();
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
        Klijent klijent = klijenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return klijent.getKonobar().toString();
            default:
                return "N/A";
        }
    }

    public void dodajKlijenta(Klijent klijent) {
        fireTableDataChanged();
    }

    public void izbaciKlijenta(int izabraniRed) throws Exception {
        if (izabraniRed >= 0 && izabraniRed < klijenti.size()) {
            Klijent klijent = klijenti.get(izabraniRed);
            klijenti.remove(izabraniRed);
            fireTableDataChanged();
            klijent.getThread().interrupt();
            ((ThreadClient) klijent.getThread()).stopThread();
        } else{
            throw new Exception("Morate izabrati klijenta");
        }
    }

    public void izbaci(Thread currentThread) {
        klijenti.remove(currentThread);
        fireTableDataChanged();
    }
    
    public void izbaciKlijenta(Konobar konobar){
        for (int i = 0; i < klijenti.size(); i++) {
            if(klijenti.get(i).getKonobar().getKonobarID() == konobar.getKonobarID()){
                klijenti.remove(i);
                fireTableDataChanged();
            }
        }
    }

    public void izbaciSveKlijente() {
        klijenti.removeAll(klijenti);
    }
    
    
    
}
