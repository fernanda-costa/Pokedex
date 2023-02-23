package br.ufpr.tads.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.ufpr.tads.pokedex.tasks.GetTopTiposTask;
import br.ufpr.tads.pokedex.tasks.GetTotalPokemonsTask;

public class DashboardActivity extends AppCompatActivity {

    TextView totalPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GetTotalPokemonsTask task = new GetTotalPokemonsTask(this);
        totalPokemons = findViewById(R.id.totalTextView);
        task.execute("http://10.0.2.2:6060/pokemons/total");

        GetTopTiposTask task2 = new GetTopTiposTask(this);
        task2.execute("http://10.0.2.2:6060/pokemons/top-tipos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.add_pokemon_menu_item:
                intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
                break;
            case R.id.listar_pokemon_menu_item:
                intent = new Intent(this, ListarPokemonsActivity.class);
                startActivity(intent);
                break;
            case R.id.pesquisar_habilidade_menu_item:
                intent = new Intent(this, PesquisarPokemonActivity.class);
                intent.putExtra("filtro", "HABILIDADE");
                startActivity(intent);
                break;
            case R.id.pesquisar_tipo_menu_item:
                intent = new Intent(this, PesquisarPokemonActivity.class);
                intent.putExtra("filtro", "TIPO");
                startActivity(intent);
                break;
            case R.id.sair_menu_item:
                finishAffinity();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}