package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import br.ufpr.tads.pokedex.adapter.AdapterListItemPokemon;
import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.model.Usuario;
import br.ufpr.tads.pokedex.tasks.GetPokemonsTask;
import br.ufpr.tads.pokedex.tasks.LoginTask;

public class ListarActivity extends AppCompatActivity {

    RecyclerView recyclerViewPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        recyclerViewPokemons = findViewById(R.id.recyclerViewPokemons);

        ArrayList pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("teste", 1, "testes", new Usuario()));

        AdapterListItemPokemon adapter = new AdapterListItemPokemon(pokemons);
        recyclerViewPokemons.setHasFixedSize(true);
        recyclerViewPokemons.setAdapter(adapter);

       // GetPokemonsTask task = new GetPokemonsTask(this, adapter);
        //task.execute("http://10.0.2.2:6060/pokemons");
    }
}