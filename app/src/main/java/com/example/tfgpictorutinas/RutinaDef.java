package com.example.tfgpictorutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RutinaDef extends AppCompatActivity {
    private TextView tvNombre;
<<<<<<< Updated upstream
=======
    private GridLayout gridRepeticiones;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("pictorutinas").child("rutinas");

    Long idRutina;
    String nombre;
    String repeticiones;
    String hora_ini;
    CheckBox cbl, cbm, cbx, cbj, cbv, cbs, cbd;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_def);

        Bundle extras = getIntent().getExtras();
        tvNombre = (TextView) findViewById(R.id.nombreRutina);
        if (extras!=null) {
<<<<<<< Updated upstream
            String nombre = extras.getString("nombre");
=======
            nombre = extras.getString("nombre");
            idRutina = extras.getLong("idRutina");
            repeticiones = extras.getString("repeticiones");
            hora_ini = extras.getString("hora_ini");
>>>>>>> Stashed changes
            tvNombre.setText(nombre);
        }


        Button editarBtn = findViewById(R.id.idBtEditarRutina);

        // onclick listener para el boton editar tarea.
        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RutinaDef.this, EditorTareas.class);
                Bundle extras = getIntent().getExtras();
                if (extras!=null) {
<<<<<<< Updated upstream
                    i.putExtra("idRutina", extras.getInt("idRutina"));
=======
                    i.putExtra("idRutina", extras.getLong("idRutina"));
                    i.putExtra("nombre", extras.getString("nombre"));
                    i.putExtra("repeticiones", extras.getString("repeticiones"));
                    i.putExtra("hora_ini", extras.getString("hora_ini"));
                }
                startActivity(i);
            }
        });

        Button participantesBtn = findViewById(R.id.idBtParticipantes);

        // onclick listener para el boton editar participantes.
        participantesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RutinaDef.this, Participantes.class);
                Bundle extras = getIntent().getExtras();
                if (extras!=null) {
                    i.putExtra("idRutina", extras.getLong("idRutina"));
>>>>>>> Stashed changes
                    i.putExtra("nombre", extras.getString("nombre"));
                }
                startActivity(i);
            }
        });

    }
}