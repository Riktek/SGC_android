package com.example.root.sgc_dbflow.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.root.sgc_dbflow.Assets.DataManager;
import com.example.root.sgc_dbflow.Assets.fotoHandler;
import com.example.root.sgc_dbflow.Entity.autoescala;
import com.example.root.sgc_dbflow.R;
import com.example.root.sgc_dbflow.conector.ConectorEventListener;
import com.example.root.sgc_dbflow.conector.Response;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.root.sgc_dbflow.Assets.DataManager.ID_NUEVO;

public class nuevoAutoActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int orientation = 0;

    private EditText editEscala;
    private EditText editFabricante;
    private EditText editAnoFabricacion;
    private EditText editModelo;
    private EditText editAno;
    private EditText editColor;
    private EditText editEstado;
    private Spinner editCaja;

    private ImageView imgCamara;

    private CheckBox checkFaltante;

    private Button btnAgregar;
    private Button btnCancelar;

    private File imgFolder;
    private File fileSavedImage;
    private Uri uriSavedImage;

    private String uri = "";
    private String uriPath;

    private int id_auto;
    private boolean update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_auto);

        id_auto = getIntent().getIntExtra("ID", ID_NUEVO);

        if (id_auto == ID_NUEVO)
            update = false;
        else
            update = true;

        editEscala = findViewById(R.id.editEscala);
        editFabricante = findViewById(R.id.editFabricante);
        editAnoFabricacion = findViewById(R.id.editAnoFabricacion);
        editModelo = findViewById(R.id.editModelo);
        editAno = findViewById(R.id.editAno);
        editColor = findViewById(R.id.editColor);
        editEstado = findViewById(R.id.editEstado);
        editCaja = findViewById(R.id.editCaja);
        checkFaltante = findViewById(R.id.checkBox2);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);

        imgCamara = findViewById(R.id.imageView2);

        String[] valores = {"Sin empaque", "Blister", "Caja"};
        editCaja.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAuto();
                finish();
            }
        });

        Picasso.with(this).setLoggingEnabled(true);

        imgCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallCamera();
            }
        });

        cargar();

    }

    private void guardarAuto(){

        autoescala tmp = new autoescala();

        tmp.setEscala(editEscala.getText().toString());
        tmp.setFabricante(editFabricante.getText().toString());
        tmp.setAnio(Integer.parseInt(editAnoFabricacion.getText().toString()));
        tmp.setModelo(editModelo.getText().toString());
        tmp.setColor(editColor.getText().toString());
        tmp.setFaltante(checkFaltante.isChecked());
        tmp.setId_coleccion(DataManager.ID_COLECCION);
        tmp.setFoto(uri);

        tmp.save();

    }

    private void cargar(){

        if (update){

            autoescala tmp = autoescala.getByID(id_auto);

            editEscala.setText(tmp.getEscala());
            editFabricante.setText(tmp.getFabricante());
            editAno.setText(Integer.toString(tmp.getAnio()));
            editAnoFabricacion.setText(Integer.toString(tmp.getAnio()));
            editModelo.setText(tmp.getModelo());
            editColor.setText(tmp.getColor());
            checkFaltante.setChecked(tmp.isFaltante());
            //editEstado.setText(tmp.get);

            uri = tmp.getFoto();

            if (tmp.getFoto() != ""){
                Picasso.with(this).load(tmp.getFoto()).fit().centerInside().into(imgCamara);

            }



        }
    }

    public void CallCamera(){

        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK).format(new Date());
        createFolder(getString(R.string.directorio_fotos));
        String nombreGenerado = timeStamp;
        String nombreFoto = nombreGenerado + ".jpg";

        fileSavedImage = new File(imgFolder, nombreFoto);

        //Uri uriSavedImage = Uri.fromFile(imagen);
        uriSavedImage = FileProvider.getUriForFile
                (this, getApplicationContext().getPackageName() + ".fileprovider" , fileSavedImage);

        takePicIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(pickIntent, "Selecciona Opción de Ingreso");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {takePicIntent});

        startActivityForResult(chooserIntent, REQUEST_IMAGE_CAPTURE);

    }

    private void createFolder (String fileName){
        String folder = Environment.getExternalStorageDirectory() + "/" + fileName;
        imgFolder = new File(folder);
        if (!imgFolder.exists())
            if (!imgFolder.mkdir()){
                Log.d("Fallo Directorio", "Failed to create the parent dir:" + folder);
            }else
                Log.d("Exito Directorio", "Successfully created the parent dir:" + folder);
        else
            Log.d("Existe Directorio", "The parent dir:" + folder + " alredy exist");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE)
            Log.d("prueba", "retorno de imagen capturada");

        if (resultCode == RESULT_OK)
            Log.d("prueba", "Captura con resultado");



        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            try {

                Uri imageUri;

                if (data == null){
                    imageUri = uriSavedImage;
                    orientation = getExifOrientation(fileSavedImage.getAbsolutePath());

                }else {

                    imageUri = data.getData();
                    orientation = getOrientation(imageUri);
                    uriPath = getRealPathFromURI(imageUri);

                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK).format(new Date());
                    createFolder(getString(R.string.directorio_fotos));
                    String nombreGenerado = "SGC_" + "_" + timeStamp ;
                    String nombreFoto = nombreGenerado + ".jpg";

                    File from = new File(uriPath);
                    final File to = new File(imgFolder, nombreFoto);

                    from.renameTo(to);
                    fileSavedImage = to;

                }


                uri = String.valueOf(Uri.fromFile(fileSavedImage));

                new fotoHandler(imageUri, this.getContentResolver(), orientation, fileSavedImage, new ConectorEventListener() {
                    @Override
                    public void OnSuccess(Response response) {
                        Picasso.with(nuevoAutoActivity.this).load(Uri.fromFile(fileSavedImage)).fit().centerInside().into(imgCamara);
                        Runtime.getRuntime().gc();
                    }

                    @Override
                    public void OnFailure(Response response) {

                    }
                }).execute();



            }catch(Exception e){
                e.printStackTrace();

            }

        }
    }

    public static int getExifOrientation(String filepath) {// YOUR MEDIA PATH AS STRING
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }
        return degree;
    }

    private int getOrientation(Uri photoUri) {

        String[] orientationColumn = {MediaStore.Images.Media.ORIENTATION};

        Cursor cur = managedQuery(photoUri, orientationColumn, null, null, null);
        int orientation = -1;
        if (cur != null && cur.moveToFirst()) {
            try {
                orientation = cur.getInt(cur.getColumnIndex(orientationColumn[0]));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return orientation;
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return "file://" + cursor.getString(column_index);
    }
}
