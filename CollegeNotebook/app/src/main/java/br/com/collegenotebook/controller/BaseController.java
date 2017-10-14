package br.com.collegenotebook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.collegenotebook.dao.BaseDAO;
import br.com.collegenotebook.model.Materia;
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

    public boolean insertSubject(Materia materia) {
        if (!verifyIfSubjectExists(materia)) {
            ContentValues values = new ContentValues();
            values.put(BaseDAO.MATERIA_NOME, materia.getNome());
            values.put(BaseDAO.MATERIA_PROFESSOR, materia.getProfessor());
            values.put(BaseDAO.MATERIA_PASTA, materia.getPasta());

            boolean createSuccessful = db.insert(BaseDAO.TABLE_MATERIA, null, values) > 0;
            close();
            return createSuccessful;
        }
        return false;
    }


    public List<Materia> getAll() {
        open();
        List<Materia> materias = new ArrayList<Materia>();
        String sqlQuery = "SELECT * FROM "+BaseDAO.TABLE_MATERIA+" ORDER BY "+BaseDAO.ID+" ASC;";
        cursorSubject = db.rawQuery(sqlQuery, null);
        if  (cursorSubject != null && cursorSubject.moveToFirst()) {
            while(cursorSubject.moveToNext()) {
                Materia user = new Materia(cursorSubject.getLong(cursorSubject.getColumnIndex(BaseDAO.ID)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATERIA_NOME)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATERIA_PROFESSOR)),
                        cursorSubject.getString(cursorSubject.getColumnIndex(BaseDAO.MATERIA_PASTA)));
                materias.add(user);

            }
        }
        cursorSubject.close();
        close();

        return materias;
    }

    public void deleteSubject(Materia materia) {
        open();
        long id = materia.getId();
        db.delete(BaseDAO.TABLE_MATERIA, BaseDAO.ID + " = " + id, null);
        close();
    }

    public  boolean verifyIfSubjectExists(Materia materia) {
        open();
        String nome = materia.getNome();
        String Query = "SELECT * FROM " +BaseDAO.TABLE_MATERIA+ " WHERE " +BaseDAO.MATERIA_NOME+ "= '" + nome +"'";
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

        boolean userSuccessful = db.insert(BaseDAO.TABLE_USER, null, values) > 0;
        return userSuccessful;

    }


    public  boolean verifyIfUserExists(User user) {
        open();
        String emailUser = user.getUserName();
        String Query = "SELECT * FROM " +BaseDAO.TABLE_USER+ " WHERE " +BaseDAO.USER_EMAIL+ "= '" + emailUser +"'";
        Cursor cursorUser = db.rawQuery(Query, null);
        cursorUser.moveToLast();
        if(cursorUser.getCount() <= 0){
            return false;
        }
        return true;
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

}
