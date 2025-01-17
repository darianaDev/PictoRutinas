package com.example.tfgpictorutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Base64;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TareaDef extends AppCompatActivity {

    //variables picto(foto,archivo,picto)
    Uri imagenUri;
    ImageView picto;
    int TOMAR_FOTO = 100;
    int SELEC_IMAGEN = 200;

    Bundle extras;
    int ARRASAC = 300;

    TextView hora;
    EditText et_descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_def);

        extras = getIntent().getExtras();
        picto = findViewById(R.id.fotoTareaDef);
<<<<<<< Updated upstream
        hora = findViewById(R.id.tiempoTareaDef);
=======

        hora_ini = findViewById(R.id.tiempoTareaDef_ini);

>>>>>>> Stashed changes
        et_descripcion = findViewById(R.id.EtDescripcionTareaDef);
        setHoraActual();

        // onclick listener para la imagen del pictograma.
        /*picto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TareaDef.this, AraasacPics.class);
                startActivity(i);
            }
<<<<<<< Updated upstream
        });*/
=======
        });
    }

    private void showdata() {
        extras = getIntent().getExtras();

        idRutina = extras.getLong("idRutina");
        idTarea = extras.getLong("idTarea");

        String tarea_picto = extras.getString("tarea_picto");
        String tarea_descripcion = extras.getString("tarea_descripcion");
        String tarea_hora_ini = extras.getString("tarea_hora_ini");
        String tarea_hora_end = extras.getString("tarea_hora_end");


        byte[] imageAsBytes = Base64.decode(tarea_picto, Base64.DEFAULT);
        picto.setImageBitmap((BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)));

        hora_ini.setText(tarea_hora_ini);
        hora_end.setText(tarea_hora_end);

        et_descripcion.setText(tarea_descripcion);
        btn_actualizar.setText("Actualizar");

    }
    private void updateTarea(ImageView picto, TextView hora_end, TextView hora_ini, EditText et_descripcion){

        Map<String,Object> map = new HashMap<>();
        map.put("idTarea",idTarea);
        map.put("nombreTarea",et_descripcion.getText().toString());
        map.put("fotoTarea",getBitstreamPicto( picto));
        map.put("hora_ini",hora_ini.getText().toString());
        map.put("hora_end",hora_end.getText().toString());
        map.put("rutina_id",idRutina);

        mDataBase.child("pictorutinas/tareas").child(String.valueOf(idTarea)).updateChildren(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TareaDef.this,"Tarea actualizada",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(TareaDef.this, EditorTareas.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TareaDef.this,"ERROR",Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void  guardardataNew(ImageView picto, TextView hora_end, TextView hora_ini, EditText et_descripcion){
        String tarea_picto= getBitstreamPicto( picto);
        String tarea_hora_ini= hora_ini.getText().toString();
        String tarea_hora_end= hora_end.getText().toString();
        String tarea_descripcion= et_descripcion.getText().toString();
        if(tarea_picto.isEmpty() ||  tarea_hora_ini.isEmpty() || tarea_hora_end.isEmpty() || tarea_descripcion.isEmpty() ){
            String error = "";
            if (tarea_picto.isEmpty())
                error="PICTOGRAMA";
            if (tarea_picto.isEmpty())
                error="HORA DE INICIO";
            if (tarea_picto.isEmpty())
                error="HORA DE FINALIZACIÓN";
            if (tarea_picto.isEmpty())
                error="DESCRIPCION";
            if (tarea_picto.isEmpty())
                error="RUTINA";
            Toast.makeText(this,"Falta"+error+" datos por definir",Toast.LENGTH_SHORT).show();

        }else {

            Map<String,Object> map = new HashMap<>();
            map.put("idTarea",idTarea);
            map.put("nombreTarea",et_descripcion.getText().toString());
            map.put("fotoTarea",getBitstreamPicto( picto));
            map.put("hora_ini",hora_ini.getText().toString());
            map.put("hora_end",hora_end.getText().toString());
            map.put("rutina_id",idRutina);

            mDataBase.child("pictorutinas/tareas").child(String.valueOf(idTarea))
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TareaDef.this,"Tarea guardada",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TareaDef.this, EditorTareas.class);
                            i.putExtra("idRutina",idRutina);
                            startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TareaDef.this,"ERROR",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private String getBitstreamPicto(ImageView picto){
        Bitmap bitmap = ((BitmapDrawable) picto.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String pic = Base64.encodeToString(byteArray,Base64.DEFAULT);
        return pic;
>>>>>>> Stashed changes
    }
    private void setHoraActual() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat Hora = new SimpleDateFormat("hh");
        SimpleDateFormat minuto = new SimpleDateFormat("mm");
        SimpleDateFormat am_pm = new SimpleDateFormat("a");
        String HH = Hora.format(calendar.getTime());
        String min = minuto.format(calendar.getTime());
        String a = am_pm.format(calendar.getTime());
        hora.setText(HH+":"+min+" "+a);
    }


    public void abrirTimePicker(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String hora;
                if (hour == 1){
                    hora= hour+" hora y "+minute+"minutos";
                }else if(hour > 1){
                    hora= hour+" horas y "+minute+"minutos";
                }else{
                    hora= minute+" minutos";
                }
<<<<<<< Updated upstream
                //Showing the picked value in the textView
                //hora.setText(String.valueOf(hour)+ ":"+String.valueOf(minute)+" "+am_pm);
                String time = hour+ ":"+minute+" "+am_pm;
                hora.setText(time);
=======
                hour_.setText(hora);
>>>>>>> Stashed changes
            }
        }, 00, 30, true);

        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        timePickerDialog.show();
    }
    public void pedirPermisosCamara(){
        if(ContextCompat.checkSelfPermission(TareaDef.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TareaDef.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }
    //Menu popup de imagen
    public void popupmenu(View v) {
        ImageView picto = findViewById(R.id.fotoTareaDef);
        PopupMenu popup = new PopupMenu(this, picto);
        pedirPermisosCamara();
        popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        tomarFoto();
                        return true;
                    case R.id.nav_album:
                        seleccionarImagen();
                        return true;
                    case R.id.nav_araasac:
                        seleccionarARAASAC();
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }

    public void tomarFoto(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imagenUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
        startActivityForResult(cameraIntent, TOMAR_FOTO);
    }
    public void seleccionarImagen(){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria,SELEC_IMAGEN);
    }
    public void seleccionarARAASAC(){
        Intent araasac = new Intent(TareaDef.this, AraasacPics.class);
        startActivityForResult(araasac, ARRASAC);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SELEC_IMAGEN) {
            imagenUri = data.getData();
            picto.setImageURI(imagenUri);
        } else if(resultCode == RESULT_OK && requestCode == TOMAR_FOTO) {
            picto.setImageURI(imagenUri);
        }else if(resultCode == RESULT_OK && requestCode == ARRASAC) {
            if (extras!=null) {
                String nombre;
                String imagen;
                nombre = data.getStringExtra("idpicto");
                imagen = data.getStringExtra("imagen");

                if (imagen!=null) {
                    byte[] imageAsBytes = Base64.decode(imagen, Base64.DEFAULT);
                    picto.setImageBitmap((BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)));
                }
            }
            picto.setImageURI(imagenUri);
        }
    }
}


