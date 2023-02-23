package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.pokedex.adapter.AdapterPokemons;
import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.model.Usuario;
import br.ufpr.tads.pokedex.tasks.GetPokemonsPesquisarTask;
import br.ufpr.tads.pokedex.tasks.GetPokemonsTask;
import br.ufpr.tads.pokedex.tasks.RemovePokemonTask;

public class ListarPokemonsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    List<Pokemon> pokemonList;
    AdapterPokemons adapterPokemons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pokemons);

        recyclerView = findViewById(R.id.listarPokemonsRecyclerView);
        progressDialog = new ProgressDialog(this);

        pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon("teste", 1, "habilidade", new Usuario()));
        pokemonList.add(new Pokemon("teste", 1, "habilidade", new Usuario()));
        adapterPokemons = new AdapterPokemons(pokemonList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterPokemons);

        new GetPokemonsTask(progressDialog, recyclerView, getApplicationContext(), adapterPokemons, pokemonList)
                .execute("http://10.0.2.2:6060/pokemons");

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Pokemon pokemon = pokemonList.get(position);
                        Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                        intent.putExtra("POKEMON", pokemon);
                        startActivity(intent);



                    }
                })
        );

    }
}