package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.ufpr.tads.pokedex.model.Pokemon;

public class DetalhesPokemonActivity extends AppCompatActivity {

    Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);


    }
}