package com.silas.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.silas.agenda.dao.AlunoDAO;

/**
 * Created by silas on 6/17/17.
 */

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");
        AlunoDAO dao = new AlunoDAO(context);
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        if (dao.isAluno(sms.getDisplayOriginatingAddress())){
            Toast.makeText(context, "Voce recebeu um sms do aluno", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
