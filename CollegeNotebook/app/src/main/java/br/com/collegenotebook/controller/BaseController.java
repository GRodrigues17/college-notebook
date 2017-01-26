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

/**
 * Created by GRodrigues17 on 27/09/2016.
 */
public class BaseController {

    private SQLiteDatabase db;
    private BaseDAO banco;
    Cursor cursor;


    public BaseController(Context context){
        banco = new BaseDAO(context);
    }

    public void open() throws SQLException {
        db = banco.getWritableDatabase();

    }

    public void close() {
        banco.close();
    }

    public boolean insereDado(Materia materia) {
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
        cursor = db.rawQuery(sqlQuery, null);
        if  (cursor != null && cursor.moveToFirst()) {
            while(cursor.moveToNext()) {

                Materia user = new Materia(cursor.getLong(cursor.getColumnIndex(BaseDAO.ID)),
                        cursor.getString(cursor.getColumnIndex(BaseDAO.MATERIA_NOME)),
                        cursor.getString(cursor.getColumnIndex(BaseDAO.MATERIA_PROFESSOR)),
                        cursor.getString(cursor.getColumnIndex(BaseDAO.MATERIA_PASTA)));
                materias.add(user);

            }
        }
        cursor.close();
        close();

        return materias;
    }


    public void deletaRegistro(Materia materia) {
        open();
        long id = materia.getId();
        db.delete(BaseDAO.TABLE_MATERIA, BaseDAO.ID + " = " + id, null);
        close();
    }

    public  boolean verifyIfSubjectExists(Materia materia) {
        open();
        String nome = materia.getNome();
        String Query = "SELECT * FROM " +BaseDAO.TABLE_MATERIA+ " WHERE " +BaseDAO.MATERIA_NOME+ "= '" + nome +"'";
        cursor = db.rawQuery(Query, null);
        cursor.moveToLast();
        if(cursor.getCount() <= 0){
            return false;
        }
        return true;
    }



}
