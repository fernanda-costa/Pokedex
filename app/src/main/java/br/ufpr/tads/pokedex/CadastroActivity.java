package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.tasks.AddPokemonTask;
import br.ufpr.tads.pokedex.tasks.LoginTask;
import br.ufpr.tads.pokedex.tasks.RemovePokemonTask;

public class CadastroActivity extends AppCompatActivity {

    Pokemon pokemon;
    boolean estaCadastrando = true;

    EditText nome, tipo, habilidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        pokemon = (Pokemon) getIntent().getSerializableExtra("POKEMON");

        nome = findViewById(R.id.nomeEditText);
        tipo = findViewById(R.id.tipoEditText);
        habilidade = findViewById(R.id.habilidadeEditText);

        if(estaCadastrando) {
            nome.setText(pokemon.getNome());
            tipo.setText(String.valueOf(pokemon.getTipo()));
            habilidade.setText(pokemon.getHabilidades());
        }
    }

    public void remover(View view) {
        pokemon = (Pokemon) getIntent().getSerializableExtra("POKEMON");
    }

    public void salvarPokemon(View view) {
        AddPokemonTask task = new AddPokemonTask(this);
        task.execute("http://10.0.2.2:6060/pokemons");
    }
}