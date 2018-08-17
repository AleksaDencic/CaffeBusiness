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
public class Konobar implements Serializable, IDomainEntity {
    
    private int konobarID;
    private String ime;
    private String prezime;
    private String adresa;
    private String brojTelefona;
    private boolean admin;
    private String username;
    private String password;

    public Konobar() {
    }

    public Konobar(int konobarID, String ime, String prezime, String adresa, String brojTelefona, boolean admin, String username, String password) {
        this.konobarID = konobarID;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.admin = admin;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKonobarID() {
        return konobarID;
    }

    public void setKonobarID(int konobarID) {
        this.konobarID = konobarID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.konobarID;
        hash = 53 * hash + Objects.hashCode(this.ime);
        hash = 53 * hash + Objects.hashCode(this.prezime);
        hash = 53 * hash + Objects.hashCode(this.adresa);
        hash = 53 * hash + Objects.hashCode(this.brojTelefona);
        hash = 53 * hash + (this.admin ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
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
        final Konobar other = (Konobar) obj;
        if (this.konobarID != other.konobarID) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
   
    @Override
    public String getTableName() {
        return "konobar";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "konobarID, ime, prezime, adresa, brojTelefona, admin, username, password";
    }

    @Override
    public String getColumnValuesForInsert() {
        //int adminBaza = 0;
        //if(admin) adminBaza = 1;
        return konobarID + ",'" + ime + "', '" + prezime + "', '" + adresa + "', '" + brojTelefona + "', " + admin + ", '" + username + "', '" + password + "'";
    }

    @Override
    public String getColumnNamesForUpdate() {
        return "konobarID = " + konobarID + ", ime = '" + ime + "', prezime = '" + prezime+ "', adresa = '" + adresa + "', brojTelefona = '" + brojTelefona + "', admin = " + admin + ", username = '" + username + "', password = '" + password + "'";
    }

    @Override
    public String getIDNamesAndValues() {
        return "konobarID = " + konobarID;
    }

    @Override
    public IDomainEntity setValues(ResultSet rs) {
        Konobar konobar = new Konobar();
        try {
            konobar.setKonobarID(rs.getInt("konobarID"));
            konobar.setIme(rs.getString("ime"));
            konobar.setPrezime(rs.getString("prezime"));
            konobar.setAdresa(rs.getString("adresa"));
            konobar.setBrojTelefona(rs.getString("brojTelefona"));
            konobar.setAdmin(rs.getBoolean("admin"));
            konobar.setUsername(rs.getString("username"));
            konobar.setPassword(rs.getString("password"));
        } catch (SQLException ex) {
            Logger.getLogger(Konobar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return konobar;
    }

    @Override
    public String getCond() {
        return "N/A";
    }

    @Override
    public void setAutoincrementId(int id) {
        konobarID = id;
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public String getIDForUpdate() {
        return "konobarID = " + konobarID;
    }
    
    
    
    
    
}
