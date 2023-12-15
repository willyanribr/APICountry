package com.example.trabsub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.CountryAdapter;
import Controller.CountryController;
import Model.Country;

public class Listagem extends AppCompatActivity {
    private TextView tvCodigo;
    private TextView tvDescricao;
    private CountryController controller;
    private RecyclerView rvPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        rvPais = findViewById(R.id.rvPais);
        carregarListaAlunos();
    }

    private void carregarListaAlunos() {
        ArrayList<Country> listaPais = controller.retornar(this);
        CountryAdapter adapter = new CountryAdapter(this, listaPais);
        rvPais.setLayoutManager(new LinearLayoutManager(this));
        rvPais.setAdapter(adapter);
    }
}