/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.modeli;

import domen.Artikal;
import domen.Narudzbina;
import domen.StavkaNarudzbine;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alekd_000
 */
public class ModelTblStavkeNarudzbine extends AbstractTableModel {

    Narudzbina narudzbina;
    String columns[] = {"Rb", "Artikal", "Kolicina"};
    Class columnTypes[] = {Integer.class, Artikal.class, Integer.class,};
    List<StavkaNarudzbine> pomocneZaEdit;

    public ModelTblStavkeNarudzbine(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
        pomocneZaEdit = new ArrayList<>();
        postaviPomocneZaEdit();
    }

    private void postaviPomocneZaEdit() {
        for (StavkaNarudzbine stavkaNarudzbine : narudzbina.getStavkeNarudzbine()) {
            pomocneZaEdit.add(stavkaNarudzbine);
        }
    }

    @Override
    public int getRowCount() {
        return narudzbina.getStavkeNarudzbine().size();
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
        StavkaNarudzbine stavka = narudzbina.getStavkeNarudzbine().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getNoviRedniBroj();
            case 1:
                return stavka.getArtikal().toString();
            case 2:
                return stavka.getKolicina();
            default:
                return "N/A";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) { //RB
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaNarudzbine stavka = narudzbina.getStavkeNarudzbine().get(rowIndex);
        switch (columnIndex) {
            case 1:
                stavka.setArtikal((Artikal) aValue);
                break;
            case 2:
                stavka.setKolicina((int) aValue);
                promeniUkupno();
                fireTableDataChanged();
                break;
        }
        if (stavka.getStatus() == 'N') {
            System.out.println("setovano na U");
            stavka.setStatus('U');
        }
    }

    public void dodajPraznuStavkuNarudzbine() {
        if (narudzbina.getStavkeNarudzbine().isEmpty()
                || (narudzbina.getStavkeNarudzbine().get(narudzbina.getStavkeNarudzbine().size() - 1).getArtikal().getArtikalID() != 0
                && narudzbina.getStavkeNarudzbine().get(narudzbina.getStavkeNarudzbine().size() - 1).getKolicina() > 0)) {
            StavkaNarudzbine stavkaNarudzbine = new StavkaNarudzbine();
            stavkaNarudzbine.setStatus('I');
            System.out.println("setovano na I");
            stavkaNarudzbine.setRb(narudzbina.getStavkeNarudzbine().size() + 1);
            stavkaNarudzbine.setNoviRedniBroj(stavkaNarudzbine.getRb());
            stavkaNarudzbine.setNarudzbina(narudzbina);
            narudzbina.getStavkeNarudzbine().add(stavkaNarudzbine);
            pomocneZaEdit.add(stavkaNarudzbine);
            fireTableDataChanged();
        }
    }

    public void obrisiStavku(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < narudzbina.getStavkeNarudzbine().size()) {
            System.out.println("setovano na D");
            StavkaNarudzbine stavkaNarudzbine = narudzbina.getStavkeNarudzbine().get(selectedRow);
            if (stavkaNarudzbine.getStatus() == 'I') {
                pomocneZaEdit.remove(stavkaNarudzbine);
            } else {
                stavkaNarudzbine.setStatus('D');
            }
            narudzbina.getStavkeNarudzbine().remove(selectedRow);
            promeniUkupno();
            srediRedneBrojeve();
            fireTableDataChanged();
        }
    }

    private void srediRedneBrojeve() {
        for (int i = 0; i < narudzbina.getStavkeNarudzbine().size(); i++) {
            StavkaNarudzbine stavkaNarudzbine = narudzbina.getStavkeNarudzbine().get(i);
            if (stavkaNarudzbine.getNoviRedniBroj() != i + 1 && stavkaNarudzbine.getStatus() != 'I') {
                stavkaNarudzbine.setStatus('U');
            }
            stavkaNarudzbine.setNoviRedniBroj(i + 1);
        }
    }

    private void promeniUkupno() {
        double ukupnaVrednost = 0;
        for (StavkaNarudzbine stavkaNarudzbine : narudzbina.getStavkeNarudzbine()) {
            ukupnaVrednost += stavkaNarudzbine.getKolicina() * stavkaNarudzbine.getArtikal().getCena();
        }
        narudzbina.setUkupnaVrednost(ukupnaVrednost);
    }

    public void izmeniNarudzbinu() {
        narudzbina.setStavkeNarudzbine(pomocneZaEdit);
    }

}
