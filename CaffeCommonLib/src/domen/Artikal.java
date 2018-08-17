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
import transfer.util.JedinicaMere;

/**
 *
 * @author alekd_000
 */
public class Artikal implements Serializable, IDomainEntity {
    
    private int artikalID;
    private String nazivArtikla;
    private double cena;
    private JedinicaMere jedinicaMere;

    public Artikal() {
    }

    public Artikal(int artikalID, String nazivArtikla, double cena, JedinicaMere jedinicaMere) {
        this.artikalID = artikalID;
        this.nazivArtikla = nazivArtikla;
        this.cena = cena;
        this.jedinicaMere = jedinicaMere;
    }

    public int getArtikalID() {
        return artikalID;
    }

    public void setArtikalID(int artikalID) {
        this.artikalID = artikalID;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.artikalID;
        hash = 73 * hash + Objects.hashCode(this.nazivArtikla);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.cena) ^ (Double.doubleToLongBits(this.cena) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.jedinicaMere);
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
        final Artikal other = (Artikal) obj;
        if (this.artikalID != other.artikalID) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (!Objects.equals(this.nazivArtikla, other.nazivArtikla)) {
            return false;
        }
        if (this.jedinicaMere != other.jedinicaMere) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivArtikla;
    }

    @Override
    public String getTableName() {
        return "artikal";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "artikalID, nazivArtikla, cena, jedinicaMere";
    }

    @Override
    public String getColumnValuesForInsert() {
        return artikalID + ", '" + nazivArtikla + "', " + cena + ", '" + jedinicaMere + "'";
    }
    
    @Override
    public String getColumnNamesForUpdate() {
        return "artikalID = " + artikalID + ", nazivArtikla = '" + nazivArtikla + "', cena = " + cena + ", jedinicaMere = '" + jedinicaMere + "'";
    }

    @Override
    public String getIDNamesAndValues() {
        return "artikalID = " + artikalID;
    }

    @Override
    public IDomainEntity setValues(ResultSet rs) {
        Artikal artikal = new Artikal();
        try {
            artikal.setArtikalID(rs.getInt("artikalID"));
            artikal.setNazivArtikla(rs.getString("nazivArtikla"));
            artikal.setCena(rs.getDouble("cena"));
            artikal.setJedinicaMere(JedinicaMere.valueOf(rs.getString("jedinicaMere")));
        } catch (SQLException ex) {
            Logger.getLogger(Artikal.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return artikal;
    }

    @Override
    public String getCond() {
        return "N/A";
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
        return "artikalID = " + artikalID;
    }
    
    
}
