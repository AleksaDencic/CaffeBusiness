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
public class Sto implements Serializable, IDomainEntity {
    
    private int stoID;
    private String polozaj;

    public Sto() {
    }

    public Sto(int stoID, String polozaj) {
        this.stoID = stoID;
        this.polozaj = polozaj;
    }

    public String getPolozaj() {
        return polozaj;
    }

    public void setPolozaj(String polozaj) {
        this.polozaj = polozaj;
    }

    public int getStoID() {
        return stoID;
    }

    public void setStoID(int stoID) {
        this.stoID = stoID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.stoID;
        hash = 59 * hash + Objects.hashCode(this.polozaj);
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
        final Sto other = (Sto) obj;
        if (this.stoID != other.stoID) {
            return false;
        }
        if (!Objects.equals(this.polozaj, other.polozaj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return stoID + ". " + polozaj;
    }

    @Override
    public String getTableName() {
        return "sto";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "stoID, polozaj";
    }

    @Override
    public String getColumnValuesForInsert() {
        return stoID + ", '" + polozaj + "'";
    }

    @Override
    public String getColumnNamesForUpdate() {
        return "stoID = " + stoID + ", polozaj = '" + polozaj + "'";
    }

    @Override
    public String getIDNamesAndValues() {
        return "stoID = " + stoID;
    }

    @Override
    public IDomainEntity setValues(ResultSet rs) {
        Sto sto = new Sto();
        try {
            sto.setStoID(rs.getInt("stoID"));
            sto.setPolozaj(rs.getString("polozaj"));
        } catch (SQLException ex) {
            Logger.getLogger(Sto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
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
        return "stoID = " + stoID;
    }
    
    
    
}
