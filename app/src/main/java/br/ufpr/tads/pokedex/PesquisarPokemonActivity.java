package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import br.ufpr.tads.pokedex.tasks.GetPokemonsPesquisarTask;
import br.ufpr.tads.pokedex.tasks.PesquisarPokemonTask;

public class PesquisarPokemonActivity extends AppCompatActivity {

    private ListView listPokemons;
    private String url = "http://10.0.2.2:6060/pokemons";
    private ProgressDialog progressDialog;
    ArrayList<String> dados = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText filtroEditText;
    String filtro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_pokemon);

        Intent intent = getIntent();
        filtro = intent.getStringExtra("filtro");
        filtroEditText = findViewById(R.id.filtroEditText);

        listPokemons = findViewById(R.id.nomePokemonsListView);
        progressDialog = new ProgressDialog(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
        listPokemons.setAdapter(adapter);

        new GetPokemonsPesquisarTask(progressDialog, listPokemons, getApplicationContext(), adapter, dados).execute(url);
    }

    public void pesquisar(View view) {
        String valor = filtroEditText.getText().toString();

        String pesquisarUrl = "http://10.0.2.2:6060/pokemons?filtro=" + filtro.toLowerCase() + "&valor=" + valor;

        new PesquisarPokemonTask(progressDialog, listPokemons, getApplicationContext(), adapter, dados).execute(pesquisarUrl);
    }

}