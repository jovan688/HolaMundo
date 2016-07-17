package com.ni.sifac.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.ni.sifac.dao.Adquisicion;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADQUISICION".
*/
public class AdquisicionDao extends AbstractDao<Adquisicion, Void> {

    public static final String TABLENAME = "ADQUISICION";

    /**
     * Properties of entity Adquisicion.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property SivProductoID = new Property(0, Integer.class, "SivProductoID", false, "SIV_PRODUCTO_ID");
        public final static Property ClienteID = new Property(1, Integer.class, "ClienteID", false, "CLIENTE_ID");
    };


    public AdquisicionDao(DaoConfig config) {
        super(config);
    }
    
    public AdquisicionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADQUISICION\" (" + //
                "\"SIV_PRODUCTO_ID\" INTEGER," + // 0: SivProductoID
                "\"CLIENTE_ID\" INTEGER);"); // 1: ClienteID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADQUISICION\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Adquisicion entity) {
        stmt.clearBindings();
 
        Integer SivProductoID = entity.getSivProductoID();
        if (SivProductoID != null) {
            stmt.bindLong(1, SivProductoID);
        }
 
        Integer ClienteID = entity.getClienteID();
        if (ClienteID != null) {
            stmt.bindLong(2, ClienteID);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Adquisicion readEntity(Cursor cursor, int offset) {
        Adquisicion entity = new Adquisicion( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // SivProductoID
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1) // ClienteID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Adquisicion entity, int offset) {
        entity.setSivProductoID(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setClienteID(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Adquisicion entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Adquisicion entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
