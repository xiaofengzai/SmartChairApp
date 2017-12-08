package com.wen.config;

/**
 * Created by wenfeng on 2017/12/8.
 */
public class MySQL5DialectWithoutFK extends org.hibernate.dialect.MySQL5Dialect {
    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return " alter "+ foreignKey[0] +" set default ''" ;
    }
}