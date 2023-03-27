package com.facens.cameraandgeolocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //Instancia uma variável timer e timertask.
    private final Timer timer = new Timer();
    TimerTask timerTask;

    //Cria a activity e fala qual layout vai ser usado, no caso, "activity_splash".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Executa o método "gotoMainActivity()" em um certo período de tempo.
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //Adiciona o delay para executar a tarefa.
        timer.schedule(timerTask, 1800);
    }

    //Método !gotoMainAcitivity()", cria um novo intent com o objetivo de executar a "MainActivity".
    private void gotoMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
