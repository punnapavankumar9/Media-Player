package com.example.mediaplayer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.mediaplayer.Activities.SongsListActivity;
import com.example.mediaplayer.Helpers.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ArrayList<Uri> songUris = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Button btnShowSongs = findViewById(R.id.btnShowSongs);

        btnShowSongs.setOnClickListener(v ->{
            Intent intent = new Intent(this, SongsListActivity.class);
            intent.putExtra("SONG_LIST_URIS" , songUris);
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
                handleOpenFolder();
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
        if(ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED){
            Log.i("permission", "permission already granted");
        }else{
            requestPermissionLauncher.launch(perm);
        }
    }
    public void handleOpenFolder(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        requestOpenDocumentTreeLauncher.launch(intent);
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->{
        if(isGranted){
            Log.i("permission", "permission granted");
        }else{
            Log.i("permission", "permission denied");
        }
    });

    private final ActivityResultLauncher<Intent> requestOpenDocumentTreeLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode() == Activity.RESULT_OK){
                    assert result.getData() != null;
                    Uri uri = Uri.parse(result.getData().getDataString());
                    DocumentFile docTree = DocumentFile.fromTreeUri(this, uri);
                    assert docTree != null;
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    for(DocumentFile df1 : docTree.listFiles()){
                        if(df1.isFile() && getMimeType(this, df1.getUri())){
                            songUris.add(df1.getUri());
                        }
                    }
                }
            });

    public static Boolean getMimeType(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getMimeTypeFromExtension(mime.getExtensionFromMimeType(context.getContentResolver().getType(uri)));

        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }
        return extension.startsWith("audio");
    }

}