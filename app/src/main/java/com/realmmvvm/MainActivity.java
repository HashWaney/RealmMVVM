package com.realmmvvm;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.realmmvvm.databinding.ActivityMainBinding;
import com.realmmvvm.model.StoreInfo;
import com.realmmvvm.utiles.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        isStoragePermissionGranted();

        activityMainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListActivity.class));
            }
        });


        activityMainBinding.export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    exportDatabase();
            }
        });

    }

    private void exportDatabase() {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            File f = new File(realm.getPath());
            if (f.exists()) {
                try {
                    FileUtil.copy(f, new File(Environment.getExternalStorageDirectory()+"/default.realm"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            if (realm != null)
                realm.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        readCSV();
    }

    public void readCSV() {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("superstore.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String data[] = line.split(",");
                if (data.length > 0) {
                    saveData(data);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData(String[] data) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(realm1 -> {

                StoreInfo storeInfo = new StoreInfo(Long.valueOf(data[0]), data[1], data[2], data[5], data[6], data[7], data[8], data[9],
                        data[13], data[14], data[15], data[16], Double.valueOf(data[17]), Long.valueOf(data[18]),
                        Double.valueOf(data[19]), Double.valueOf(data[20]));
                realm1.copyToRealmOrUpdate(storeInfo);
                Log.d("Test", "saveData: " + realm1);

            });
        } catch (Exception e) {
            e.printStackTrace();
            if (realm != null) {
                realm.close();
            }
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
