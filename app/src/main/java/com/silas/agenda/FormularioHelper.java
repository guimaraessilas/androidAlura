package com.silas.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.silas.agenda.model.Aluno;

/**
 * Created by Silas on 13/04/2017.
 */

public class FormularioHelper {
    private final EditText nomeET;
    private final EditText enderecoET;
    private final EditText siteET;
    private final EditText telefoneET;
    private final RatingBar notaRB;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nomeET = (EditText) activity.findViewById(R.id.form_nome);
        enderecoET = (EditText) activity.findViewById(R.id.form_endereco);
        siteET = (EditText) activity.findViewById(R.id.form_site);
        telefoneET = (EditText) activity.findViewById(R.id.form_telefone);
        notaRB = (RatingBar) activity.findViewById(R.id.form_nota);
        aluno = new Aluno();
    }

    public Aluno getAluno(){
        aluno.setNome(nomeET.getText().toString());
        aluno.setEndereco(enderecoET.getText().toString());
        aluno.setNota((double) notaRB.getNumStars());
        aluno.setTelefone(telefoneET.getText().toString());

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        nomeET.setText(aluno.getNome());
        enderecoET.setText(aluno.getEndereco());
        siteET.setText(aluno.getSite());
        telefoneET.setText(aluno.getTelefone());
        notaRB.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }

}
