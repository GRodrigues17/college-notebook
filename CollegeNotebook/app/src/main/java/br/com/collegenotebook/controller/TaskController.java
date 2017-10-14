package br.com.collegenotebook.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.collegenotebook.dao.BaseDAO;
import br.com.collegenotebook.model.Task;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class TaskController {

    private SQLiteDatabase db;
    private BaseDAO banco;
    Cursor cursorSubject;


    public TaskController(Context context){
        banco = new BaseDAO(context);
    }

    public void open() throws SQLException {
        db = banco.getWritableDatabase();

    }

    public void close() {
        banco.close();
    }

    public List<Task> buscarTitulo(Date date){
        List<Task> lista = new ArrayList<Task>();


        return null;
    }
}
