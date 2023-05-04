package com.example.tfgpictorutinas;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
=======
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


<<<<<<< Updated upstream
import java.util.ArrayList;

public class EditorTareas extends AppCompatActivity {
    private TextView tvNombre;

    AdaptadorTareas adapter;
    ArrayList<Tarea> listaTareas = new ArrayList(30);
=======
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class EditorTareas extends AppCompatActivity {
    private DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();

    private long id_rutina;

    private String nombre;
    private String hora_ini;
    private String repeticiones;

    RecyclerView recyclerView;
    AdaptadorTareas adaptadorTareas;

    TextView hora_ini_rutina;

    EditText nombreTarea;
    Button new_tarea;
    Button guaradar_rutina;
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_tareas);
<<<<<<< Updated upstream

        Bundle extras = getIntent().getExtras();
        tvNombre = (TextView) findViewById(R.id.nombreRut);
        if (extras!=null) {
            String nombre = extras.getString("nombre");
            tvNombre.setText(nombre);
        }


        //Generación de listView
        ListView list = (ListView) findViewById(R.id.listaTareas);
        this.adapter = new AdaptadorTareas(this, this.listaTareas);

        // boton de NuevaTarea
        Button NuevaTareaBtn = findViewById(R.id.idBtnNuevaTarea);

        // onclick listener para el boton de NuevaTarea
        NuevaTareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tarea t = new Tarea(listaTareas.size()+1,"Tarea" + (listaTareas.size()+1), "" + R.drawable.alumno); //@todo Revisar
                listaTareas.add(t);
                list.setAdapter(adapter);
=======
        new_tarea = findViewById(R.id.idBtnNuevaTarea);
        nombreTarea = findViewById(R.id.nombreRut);
        recyclerView = findViewById(R.id.listaTareas);
        hora_ini_rutina = findViewById(R.id.comienzoRutina);
        guaradar_rutina = findViewById(R.id.idBtnGuardarRutina);


        Bundle extras;
        extras = getIntent().getExtras();
        id_rutina = extras.getLong("idRutina");
        nombre = extras.getString("nombre");
        hora_ini = extras.getString("hora_ini");
        repeticiones = extras.getString("repeticiones");

        nombreTarea.setText(nombre);
        hora_ini_rutina.setText(hora_ini);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Tarea> options=
                new FirebaseRecyclerOptions.Builder<Tarea>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pictorutinas").child("tareas").orderByChild("rutina_id").equalTo(id_rutina), Tarea.class)
                        .build();
        this.adaptadorTareas = new AdaptadorTareas(options);
        recyclerView.setAdapter(adaptadorTareas);
        hora_ini_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTimePicker(v,hora_ini_rutina);
            }
        });
        new_tarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id_new_tarea = adaptadorTareas.getItemCount()+1;
                Intent intent = new Intent(EditorTareas.this, TareaDef.class);

                intent.putExtra("is_old",0);
                intent.putExtra("idTarea",id_new_tarea);
                intent.putExtra("idRutina", id_rutina);

                startActivity(intent);
>>>>>>> Stashed changes
            }
        });
        guaradar_rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_rutina(hora_ini_rutina,nombreTarea,id_rutina);
            }
        });


    }

    public void guardar_rutina(TextView hora_ini_rutina,EditText nombreTarea, long id_rutina){
        Map<String,Object> map = new HashMap<>();
        map.put("idRutina",id_rutina);
        map.put("foto","2131165305");
        map.put("nombre",nombreTarea.getText().toString());
        map.put("repeticiones",repeticiones);
        map.put("hora_ini",hora_ini_rutina.getText().toString());


        mDataBase.child("pictorutinas/rutinas").child(String.valueOf(id_rutina))
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditorTareas.this,"Tarea guardada",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EditorTareas.this, HomeActivity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditorTareas.this,"ERROR",Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void abrirTimePicker(View v, TextView hour_) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String am_pm = "AM";
                if(hour>12){
                    am_pm = "PM";
                    hour = hour-12;
                }
                //Showing the picked value in the textView
                //hora.setText(String.valueOf(hour)+ ":"+String.valueOf(minute)+" "+am_pm);
                String hora = hour+ ":"+minute+" "+am_pm;
                hour_.setText(hora);
            }
        }, 00, 30, false);

        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        timePickerDialog.show();
    }
}