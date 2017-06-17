package com.silas.agenda.converter;

import com.silas.agenda.model.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by silas on 6/17/17.
 */

public class AlunoConverter {
    public String toJson(List<Aluno> alunos) {
        try {
            JSONStringer js = new JSONStringer();
            js.object().key("list").array().object().key("aluno").array();

            for (Aluno aluno: alunos) {
                js.object();
                js.key("id").value(aluno.getId());
                js.key("nome").value(aluno.getNome());
                js.key("telefone").value(aluno.getTelefone());
                js.key("endereco").value(aluno.getEndereco());
                js.key("site").value(aluno.getSite());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }

            js.endArray().endObject().endArray().endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
