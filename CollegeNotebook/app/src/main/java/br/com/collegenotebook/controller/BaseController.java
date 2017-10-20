package br.com.collegenotebook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.dao.BaseDAO;
import br.com.collegenotebook.model.Matter;
import br.com.collegenotebook.model.User;

/**
 * Created by GRodrigues17 on 27/09/2016.
 */
public class BaseController {

    private SQLiteDatabase db;
    private BaseDAO banco;
    Cursor cursorSubject;


    public BaseController(Context context){
        banco = new BaseDAO(context);
    }

    public void open() throws SQLException {
        db = banco.getWritableDatabase();

    }

    public void close() {
        banco.close();
    }

    public boolean insertSubject(Matter matter) {
        if (!verifyIfSubjectExists(matter)) {
            ContentValues values = new ContentValues();
            values.put(BaseDAO.MATTER_TITLE, matter.getTitle());
            values.put(BaseDAO.MATTER_INSTRUCTOR, matter.getInstructor());
            values.put(BaseDAO.MATTER_FOLDER, matter.getFolder());
            values.put(BaseDAO.MATTER_DATE, matter.getDate());
            values.put(BaseDAO.MATTER_LIKE, matter.getLike());
            values.put(BaseDAO.MATTER_COLOR, matter.getColor());

            boolean createSuccessful = db.insert(BaseDAO.TABLE_MATTER, null, values) > 0;
            close();
            return createSuccessful;
        }
        return false;
    }


    public List<Matter> getAll() {
        open();
        List<Matter> matters = new ArrayList<Matter>();
        String sqlQuery = "SELECT * FROM "+BaseDAO.TABLE_MATTER+" ORDER BY "+BaseDAO.ID+" ASC;";
        cursorSubject = db.rawQuery(sqlQuery, null);
        if  (cursorSubject != null && cursorSubject.moveToFirst()) {
            while(cursorSubject.moveToNext()) {
                Matter user = new Matter(cursorSubject.getLong(cursorSubject.getColumnIndex(BaseDAO.ID)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATTER_TITLE)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATTER_INSTRUCTOR)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATTER_FOLDER)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATTER_DATE)),
                        cursorSubject.getInt(cursorSubject.getColumnIndex(BaseDAO.MATTER_LIKE)),
                        cursorSubject.getInt(cursorSubject.getColumnIndex(BaseDAO.MATTER_COLOR)));
                matters.add(user);

            }
        }
        cursorSubject.close();
        close();

        return matters;
    }

    public void deleteSubject(Matter matter) {
        open();
        long id = matter.getId();
        db.delete(BaseDAO.TABLE_MATTER, BaseDAO.ID + " = " + id, null);
        close();
    }

    public  boolean verifyIfSubjectExists(Matter matter) {
        open();
        String nome = matter.getTitle();
        String Query = "SELECT * FROM " +BaseDAO.TABLE_MATTER+ " WHERE " +BaseDAO.MATTER_TITLE+ "= '" + nome +"'";
        cursorSubject = db.rawQuery(Query, null);
        cursorSubject.moveToLast();
        if(cursorSubject.getCount() <= 0){
            return false;
        }
        return true;
    }


    public boolean insertUser(User user) {
        open();
        // if (!verifyIfUserExists(user)) {
        ContentValues values = new ContentValues();
        values.put(BaseDAO.USER_EMAIL, user.getUserEmail());
        values.put(BaseDAO.USER_NAME, user.getUserName());
        values.put(BaseDAO.USER_PASSWORD, user.getUserPassword());
        values.put(BaseDAO.USER_PHOTO, user.getUserPhoto());
        values.put(BaseDAO.USER_EMAIL, user.getUserEmail());
        values.put(BaseDAO.USER_LOCALE, user.getUserLocale());

        boolean userSuccessful = db.insert(BaseDAO.TABLE_USER, null, values) > 0;
        return userSuccessful;

    }


    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query(BaseDAO.TABLE_USER, null, " userEmail=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(BaseDAO.USER_PASSWORD));
        cursor.close();
        return password;

    }
    public void isLike(int like, long matterId){
        open();
        Cursor cursor= db.rawQuery("UPDATE "+ BaseDAO.TABLE_MATTER+" SET "+BaseDAO.MATTER_LIKE+" = '"+ like +"' WHERE "+ BaseDAO.ID+" = " + matterId,null);
        cursor.moveToLast();
        close();
    }


}
