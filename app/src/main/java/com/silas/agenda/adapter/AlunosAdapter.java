package com.silas.agenda.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silas.agenda.MainActivity;
import com.silas.agenda.R;
import com.silas.agenda.model.Aluno;

import java.util.List;

/**
 * Created by silas on 6/17/17.
 */

public class AlunosAdapter extends BaseAdapter{

    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if (view == null){
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView nomeTV = (TextView) view.findViewById (R.id.item_nome);
        nomeTV.setText(aluno.getNome());
        TextView telefoneTV = (TextView) view.findViewById (R.id.item_telefone);
        telefoneTV.setText(aluno.getTelefone());
        ImageView fotoIV = (ImageView) view.findViewById(R.id.item_foto);

        if (aluno.getCaminhoFoto()!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            fotoIV.setImageBitmap(bitmapReduzido);
            fotoIV.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
