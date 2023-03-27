package com.facens.cameraandgeolocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GPStracker implements LocationListener {
    Context context;

    //Construtor da classe "GPStracker, serve para buscar a localização do smartphone"
    public GPStracker(Context c) {
        context = c;
    }

    //Método "GetLocation()".
    public Location getLocation() {
//Verifica se possui permissão para utilizar a localização do celular.
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
//Caso não tenha, aparece uma mensagem.
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//Verifica se o GPS está ligado.
        if (isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
//Caso não esteja pedir para o usuário.
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
//Caso não conseguiu pegar a localização, volta com um valor nulo.
        return null;
    }

    //Caso desligue a localização, inicia método "onPorviderDisabled()".
    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    //Caso localização mude, inicia método "onLocationChanged()".
    @Override
    public void onLocationChanged(@NonNull Location location) {
    }

    //Caso status do provedor mude(GPS), internet e afins, inicia método "onStatusChanged()".
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    private class ACCESS_FINE_LOCATION {
    }
}