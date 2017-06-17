package com.silas.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.silas.agenda.converter.AlunoConverter;
import com.silas.agenda.dao.AlunoDAO;
import com.silas.agenda.model.Aluno;

import java.util.List;

/**
 * Created by silas on 6/17/17.
 */

public class EnviaAlunosTask extends AsyncTask<Void, Void, String>{

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.listAlunos();
        dao.close();
        AlunoConverter conversor = new AlunoConverter();
        WebClient client = new WebClient();

        return client.post(conversor.toJson(alunos));
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }
}