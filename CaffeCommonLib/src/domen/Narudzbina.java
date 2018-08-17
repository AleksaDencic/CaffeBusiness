
package domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alekd_000
 */
public class Narudzbina implements Serializable, IDomainEntity {
    
    private int narudzbinaID;
    private LocalDate datum;
    private double ukupnaVrednost;
    private boolean stornirana;
    private Konobar konobar;
    private Sto sto;
    private List<StavkaNarudzbine> stavkeNarudzbine;

    public Narudzbina() {
        konobar = new Konobar();
        sto = new Sto();
        stavkeNarudzbine = new ArrayList<>();
    }

    public Narudzbina(int narudzbinaID, LocalDate datum, double ukupnaVrednost, boolean stornirana, Konobar konobar, Sto sto, List<StavkaNarudzbine> stavkeNarudzbine) {
        this.narudzbinaID = narudzbinaID;
        this.datum = datum;
        this.ukupnaVrednost = ukupnaVrednost;
        this.stornirana = stornirana;
        this.konobar = konobar;
        this.sto = sto;
        this.stavkeNarudzbine = stavkeNarudzbine;
    }

    public List<StavkaNarudzbine> getStavkeNarudzbine() {
        return stavkeNarudzbine;
    }

    public void setStavkeNarudzbine(List<StavkaNarudzbine> stavkeNarudzbine) {
        this.stavkeNarudzbine = stavkeNarudzbine;
    }

    public int getNarudzbinaID() {
        return narudzbinaID;
    }

    public void setNarudzbinaID(int narudzbinaID) {
        this.narudzbinaID = narudzbinaID;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public boolean isStornirana() {
        return stornirana;
    }

    public void setStornirana(boolean stornirana) {
        this.stornirana = stornirana;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.narudzbinaID;
        hash = 83 * hash + Objects.hashCode(this.datum);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.ukupnaVrednost) ^ (Double.doubleToLongBits(this.ukupnaVrednost) >>> 32));
        hash = 83 * hash + (this.stornirana ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.konobar);
        hash = 83 * hash + Objects.hashCode(this.sto);
        hash = 83 * hash + Objects.hashCode(this.stavkeNarudzbine);
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
        final Narudzbina other = (Narudzbina) obj;
        if (this.narudzbinaID != other.narudzbinaID) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupnaVrednost) != Double.doubleToLongBits(other.ukupnaVrednost)) {
            return false;
        }
        if (this.stornirana != other.stornirana) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.konobar, other.konobar)) {
            return false;
        }
        if (!Objects.equals(this.sto, other.sto)) {
            return false;
        }
        if (!Objects.equals(this.stavkeNarudzbine, other.stavkeNarudzbine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return narudzbinaID + "";
    }

    @Override
    public String getTableName() {
        return "narudzbina";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "narudzbinaID, datum, ukupnaVrednost, stornirana, konobarID, stoID";
    }

    @Override
    public String getColumnValuesForInsert() {
        return narudzbinaID + ", '" + Date.valueOf(datum) + "', " + ukupnaVrednost + ", " + stornirana + ", " + konobar.getKonobarID() + ", " + sto.getStoID();
    }

    @Override
    public String getColumnNamesForUpdate() {
        return "narudzbinaID = " + narudzbinaID + ", datum = '" + Date.valueOf(datum) + "', ukupnaVrednost = " + ukupnaVrednost + ", stornirana = " + stornirana + ", konobarID = " + konobar.getKonobarID() + ", stoID = " + sto.getStoID();
    }

    @Override
    public String getIDNamesAndValues() {
        return "narudzbinaID = " + narudzbinaID;
    }
    
    @Override
    public IDomainEntity setValues(ResultSet rs) {
        Narudzbina narudzbina = new Narudzbina();
        try {
            narudzbina.setNarudzbinaID(rs.getInt("narudzbinaID"));
            narudzbina.setDatum(rs.getDate("datum").toLocalDate());
            narudzbina.setUkupnaVrednost(rs.getDouble("ukupnaVrednost"));
            narudzbina.setStornirana(rs.getBoolean("stornirana"));
            narudzbina.getKonobar().setKonobarID(rs.getInt("konobarID"));
            narudzbina.getSto().setStoID(rs.getInt("stoID"));
        } catch (SQLException ex) {
            Logger.getLogger(Narudzbina.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return narudzbina;        
    }

    @Override
    public String getCond() {
        return "stornirana,true";
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
        return "narudzbinaID = " + narudzbinaID;
    }
    
    
}
