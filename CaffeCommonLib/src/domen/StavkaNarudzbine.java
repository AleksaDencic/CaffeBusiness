/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alekd_000
 */
public class StavkaNarudzbine implements Serializable, IDomainEntity {
    
    private int rb;
    private int kolicina;
    private Artikal artikal;
    private Narudzbina narudzbina;
    private char status;
    private int noviRedniBroj;

    public int getNoviRedniBroj() {
        return noviRedniBroj;
    }

    public void setNoviRedniBroj(int noviRedniBroj) {
        this.noviRedniBroj = noviRedniBroj;
    }

    public StavkaNarudzbine() {
        artikal = new Artikal();
        narudzbina = new Narudzbina();
        status = 'N';
        noviRedniBroj = rb;
    }

    public StavkaNarudzbine(int rb, int kolicina, Artikal artikal, Narudzbina narudzbina, char status) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.artikal = artikal;
        this.narudzbina = narudzbina;
        this.status = status;
    }

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }
    
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.rb;
        hash = 19 * hash + this.kolicina;
        hash = 19 * hash + Objects.hashCode(this.artikal);
        hash = 19 * hash + Objects.hashCode(this.narudzbina);
        hash = 19 * hash + this.status;
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
        final StavkaNarudzbine other = (StavkaNarudzbine) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (this.kolicina != other.kolicina) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.artikal, other.artikal)) {
            return false;
        }
        if (!Objects.equals(this.narudzbina, other.narudzbina)) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return rb + ". " + artikal;
    }

    @Override
    public String getTableName() {
        return "stavkaNarudzbine";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "narudzbinaID, rb, kolicina, artikalID";
    }

    @Override
    public String getColumnValuesForInsert() {
        return narudzbina.getNarudzbinaID()  + ", " + noviRedniBroj + ", " + kolicina + ", " + artikal.getArtikalID();
    }

    @Override
    public String getColumnNamesForUpdate() {
        return "narudzbinaID = " + narudzbina.getNarudzbinaID() + ", rb=" + noviRedniBroj + ", kolicina = " + kolicina + ", artikalID = " + artikal.getArtikalID();
    }

    @Override
    public String getIDNamesAndValues() {
         return "rb =" + noviRedniBroj + "&& narudzbinaID = " + narudzbina.getNarudzbinaID();
    }

    @Override
    public IDomainEntity setValues(ResultSet rs) {
        StavkaNarudzbine stavkaNarudzbine = new StavkaNarudzbine();
        try {
            stavkaNarudzbine.getNarudzbina().setNarudzbinaID(rs.getInt("narudzbinaID"));
            stavkaNarudzbine.setRb(rs.getInt("rb"));
            stavkaNarudzbine.setNoviRedniBroj(stavkaNarudzbine.getRb());
            stavkaNarudzbine.setKolicina(rs.getInt("kolicina"));
            stavkaNarudzbine.getArtikal().setArtikalID(rs.getInt("artikalID"));
        } catch (SQLException ex) {
            Logger.getLogger(StavkaNarudzbine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stavkaNarudzbine;
    }

    @Override
    public String getCond() {
        return "narudzbinaID = " + narudzbina.getNarudzbinaID();
    }
    
       @Override
    public void setAutoincrementId(int id) {
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }
    
    @Override
    public String getIDForUpdate() {
        return "rb =" + rb + "&& narudzbinaID =" + narudzbina.getNarudzbinaID();
    }
}
