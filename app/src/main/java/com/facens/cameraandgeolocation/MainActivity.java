package com.facens.cameraandgeolocation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaração de variável.
    private ImageView imageViewFoto;
    private Button btnGeo;
    //cria a interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Busca referência do botão usando id "btn_gps".
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
//Espera input do usuário.
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //Assim que clicar, instancia um novo GPSTracker e pega a localização do usuário utilizando o método "GetLocation()".
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
//Caso a localização não esteje vazia, latitude é igual "l.getLatitude()" e longitude igual "l.getLongitude()".
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
//Verificação de permissão de utilizar a câmera.
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, 0);
        }
//Referência ImageView usando o id "image_foto", e espera input de usuário.
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            //Inicia Método "TirarFoto()".
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    //Método "TirarFoto(), cria uma nova intent com o objetivo de abrir a câmera e tirar uma foto, assim que finalizar guardar foto com id 1."
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Confere intent que pediu, ou seja confere se ação foi realizada.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//Se intent foi satisfatório, armazena a foto tirada, e coloca a foto no lugar do ImageView.
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
