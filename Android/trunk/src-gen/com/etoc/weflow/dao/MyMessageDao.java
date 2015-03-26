package com.etoc.weflow.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.etoc.weflow.dao.MyMessage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MY_MESSAGE.
*/
public class MyMessageDao extends AbstractDao<MyMessage, String> {

    public static final String TABLENAME = "MY_MESSAGE";

    /**
     * Properties of entity MyMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Msgid = new Property(0, String.class, "msgid", true, "MSGID");
        public final static Property Userid = new Property(1, String.class, "userid", false, "USERID");
        public final static Property Type = new Property(2, String.class, "type", false, "TYPE");
        public final static Property Picurl = new Property(3, String.class, "picurl", false, "PICURL");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(5, String.class, "content", false, "CONTENT");
        public final static Property Flowcoins = new Property(6, String.class, "flowcoins", false, "FLOWCOINS");
        public final static Property Time = new Property(7, String.class, "time", false, "TIME");
        public final static Property Pageurl = new Property(8, String.class, "pageurl", false, "PAGEURL");
        public final static Property Productid = new Property(9, String.class, "productid", false, "PRODUCTID");
        public final static Property Extradata = new Property(10, String.class, "extradata", false, "EXTRADATA");
    };


    public MyMessageDao(DaoConfig config) {
        super(config);
    }
    
    public MyMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MY_MESSAGE' (" + //
                "'MSGID' TEXT PRIMARY KEY NOT NULL ," + // 0: msgid
                "'USERID' TEXT NOT NULL ," + // 1: userid
                "'TYPE' TEXT," + // 2: type
                "'PICURL' TEXT," + // 3: picurl
                "'TITLE' TEXT," + // 4: title
                "'CONTENT' TEXT," + // 5: content
                "'FLOWCOINS' TEXT," + // 6: flowcoins
                "'TIME' TEXT," + // 7: time
                "'PAGEURL' TEXT," + // 8: pageurl
                "'PRODUCTID' TEXT," + // 9: productid
                "'EXTRADATA' TEXT);"); // 10: extradata
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MY_MESSAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MyMessage entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getMsgid());
        stmt.bindString(2, entity.getUserid());
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(3, type);
        }
 
        String picurl = entity.getPicurl();
        if (picurl != null) {
            stmt.bindString(4, picurl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
 
        String flowcoins = entity.getFlowcoins();
        if (flowcoins != null) {
            stmt.bindString(7, flowcoins);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(8, time);
        }
 
        String pageurl = entity.getPageurl();
        if (pageurl != null) {
            stmt.bindString(9, pageurl);
        }
 
        String productid = entity.getProductid();
        if (productid != null) {
            stmt.bindString(10, productid);
        }
 
        String extradata = entity.getExtradata();
        if (extradata != null) {
            stmt.bindString(11, extradata);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MyMessage readEntity(Cursor cursor, int offset) {
        MyMessage entity = new MyMessage( //
            cursor.getString(offset + 0), // msgid
            cursor.getString(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // picurl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // content
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // flowcoins
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pageurl
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // productid
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // extradata
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MyMessage entity, int offset) {
        entity.setMsgid(cursor.getString(offset + 0));
        entity.setUserid(cursor.getString(offset + 1));
        entity.setType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPicurl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setContent(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFlowcoins(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPageurl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProductid(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setExtradata(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(MyMessage entity, long rowId) {
        return entity.getMsgid();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(MyMessage entity) {
        if(entity != null) {
            return entity.getMsgid();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
