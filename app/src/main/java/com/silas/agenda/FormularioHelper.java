package com.silas.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
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
    private final ImageView fotoIV;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nomeET = (EditText) activity.findViewById(R.id.form_nome);
        enderecoET = (EditText) activity.findViewById(R.id.form_endereco);
        siteET = (EditText) activity.findViewById(R.id.form_site);
        telefoneET = (EditText) activity.findViewById(R.id.form_telefone);
        notaRB = (RatingBar) activity.findViewById(R.id.form_nota);
        fotoIV = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno getAluno(){
        aluno.setNome(nomeET.getText().toString());
        aluno.setEndereco(enderecoET.getText().toString());
        aluno.setNota((double) notaRB.getNumStars());
        aluno.setTelefone(telefoneET.getText().toString());
        aluno.setSite(siteET.getText().toString());
        if(fotoIV.getTag()!=null){
            aluno.setCaminhoFoto((String)fotoIV.getTag());
        }

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        nomeET.setText(aluno.getNome());
        enderecoET.setText(aluno.getEndereco());
        siteET.setText(aluno.getSite());
        telefoneET.setText(aluno.getTelefone());
        notaRB.setProgress((int) aluno.getNota().doubleValue());
        if (aluno.getCaminhoFoto()!=null){
            carregaImagem(aluno.getCaminhoFoto());
        }

        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        fotoIV.setImageBitmap(bitmapReduzido);
        fotoIV.setScaleType(ImageView.ScaleType.FIT_XY);
        fotoIV.setTag(caminhoFoto);
    }
}
