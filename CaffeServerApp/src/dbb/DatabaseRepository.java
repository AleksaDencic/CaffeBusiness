/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import domen.IDomainEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alekd_000
 */
public class DatabaseRepository {
    
    public domen.IDomainEntity save(domen.IDomainEntity ide) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(ide.getTableName())
                .append("(")
                .append(ide.getColumnNamesForInsert())
                .append(")")
                .append(" VALUES ")
                .append("(")
                .append(ide.getColumnValuesForInsert())
                .append(")");

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        return findById(ide);
    }        
        
        
    public domen.IDomainEntity findById(domen.IDomainEntity ide) throws SQLException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(ide.getTableName())
                .append(" WHERE ")
                .append(ide.getIDNamesAndValues());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(query);
        if (rs.next()) {
            ide = ide.setValues(rs);
        }
        else {
           throw new Exception(ide.getTableName() +" koju trazite ne postoji.");
        }
        return ide;
    }

    public List<domen.IDomainEntity> findAll(domen.IDomainEntity ide) throws Exception {
        List<domen.IDomainEntity> lista = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + ide.getTableName();
            System.out.println("Query: " + query);
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                lista.add(ide.setValues(rs));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<domen.IDomainEntity> findAllWithCond(domen.IDomainEntity ide) throws Exception {
        List<domen.IDomainEntity> lista = new ArrayList<>();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(ide.getTableName())
                    .append(" WHERE ")
                    .append(ide.getCond());
            String query = sb.toString();
            System.out.println("Query: " + query);
            Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                lista.add(ide.setValues(rs));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public IDomainEntity update(domen.IDomainEntity ide) throws SQLException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(ide.getTableName())
                .append(" SET ")
                .append(ide.getColumnNamesForUpdate())
                .append(" WHERE ")
                .append(ide.getIDForUpdate());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        return null;
    }
    
    public IDomainEntity storn(domen.IDomainEntity ide) throws SQLException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(ide.getTableName())
                .append(" SET ")
                .append("stornirana = true")
                .append(" WHERE ")
                .append(ide.getIDNamesAndValues());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
        return null;
    }

    public void delete(domen.IDomainEntity ide) throws SQLException, Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(ide.getTableName())
                .append(" WHERE ")
                .append(ide.getIDNamesAndValues());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = DatabaseConnection.getInstance().getConnection().createStatement();
        s.executeUpdate(query);
    }
    
    public void startTransaction() throws SQLException, Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    public void commit() throws Exception {
        DatabaseConnection.getInstance().getConnection().commit();
    }

    public void rollback() throws Exception {
        DatabaseConnection.getInstance().getConnection().rollback();
    }
    
}
