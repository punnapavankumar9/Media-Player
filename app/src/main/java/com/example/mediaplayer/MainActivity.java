package com.example.mediaplayer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Button btnShowSongs = findViewById(R.id.btnShowSongs);

        btnShowSongs.setOnClickListener(v ->{
            Intent intent = new Intent(this, SongsListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_open_folder:
                Toast.makeText(this, "Open folder icon is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.main_menu_open_file:
                Toast.makeText(this, "Open file icon is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.main_menu_settings:
                Toast.makeText(this, "Menu settings icon is clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.project_about:
                Toast.makeText(this, "Project about icon is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void handlePermission(String perm){
        if(ContextCompat.checkSelfPermission(this , perm) != PackageManager.PERMISSION_GRANTED){
            requestPermissionLauncher.launch(perm);
        }
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
    registerForActivityResult(new ActivityResultContracts.RequestPermission(), result ->{
        if(result)
            Log.i("permission", "permission granted");
        else
            Log.i("permission", "permission denied");
    });



}