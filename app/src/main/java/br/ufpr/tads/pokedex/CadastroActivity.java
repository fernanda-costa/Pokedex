package br.ufpr.tads.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.tasks.AddPokemonTask;
import br.ufpr.tads.pokedex.tasks.LoginTask;
import br.ufpr.tads.pokedex.tasks.RemovePokemonTask;

public class CadastroActivity extends AppCompatActivity {

    Pokemon pokemon;
    boolean estaCadastrando = true;

    EditText nome, habilidade;
    Spinner tipo;
    String[] tipos = {"fogo", "ar", "agua", "terra"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        pokemon = (Pokemon) getIntent().getSerializableExtra("POKEMON");

        nome = findViewById(R.id.nomeEditText);
        tipo = findViewById(R.id.tipoSpinner);
        habilidade = findViewById(R.id.habilidadeEditText);

        estaCadastrando = pokemon == null;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        tipo.setAdapter(adapter);

        if(!estaCadastrando) {
            nome.setText(pokemon.getNome());
            tipo.setSelection(pokemon.getTipo());
            habilidade.setText(pokemon.getHabilidades());
        }
    }

    private String getTipoValue(int tipo) {
        return tipos[tipo];
    }
    public void remover(View view) {
        pokemon = (Pokemon) getIntent().getSerializableExtra("POKEMON");
    }

    public void salvarPokemon(View view) {
        AddPokemonTask task = new AddPokemonTask(this);
        task.execute("http://10.0.2.2:6060/pokemons");
    }
}