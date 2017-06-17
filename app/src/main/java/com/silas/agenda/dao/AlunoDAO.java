package com.silas.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.silas.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 13/04/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper{


    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL," +
                " endereco TEXT, telefone TEXT, site TEXT, nota REAL, caminho_foto TEXT);";

            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE alunos ADD COLUMN caminho_foto TEXT;";
                db.execSQL(sql);
        }



        //onCreate(db);
    }

    public void insert(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getContentValues(aluno);

        db.insert("alunos", null, values);
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues values = new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminho_foto", aluno.getCaminhoFoto());
        return values;
    }

    public List<Aluno> listAlunos(){
        String sql = "SELECT * FROM ALUNOS;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminho_foto")));

            alunos.add(aluno);
        }

        c.close();
        return alunos;
    }

    public void delete(Aluno aluno){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("alunos", "id = ?", params);
    }

    public void update(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getContentValues(aluno);
        String[] params = {aluno.getId().toString()};

        db.update("alunos", values, "id = ?", params);
    }
}
