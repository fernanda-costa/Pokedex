package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.ufpr.tads.pokedex.tasks.AddPokemonTask;
import br.ufpr.tads.pokedex.tasks.LoginTask;

public class CadastroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void salvarPokemon(View view) {
        AddPokemonTask task = new AddPokemonTask(this);
        task.execute("http://10.0.2.2:6060/pokemons");
    }
}