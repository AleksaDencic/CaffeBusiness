/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;

/**
 *
 * @author alekd_000
 */
public interface IDomainEntity {
    
    public String getTableName();
    public String getColumnNamesForInsert();
    public String getColumnValuesForInsert();
    public String getColumnNamesForUpdate();
    public String getIDNamesAndValues();
    
    public IDomainEntity setValues(ResultSet rs);

    public String getCond();
    
    public boolean isIdAutoincrement();
    
    public void setAutoincrementId(int id);
    
    public String getIDForUpdate();
    
}
