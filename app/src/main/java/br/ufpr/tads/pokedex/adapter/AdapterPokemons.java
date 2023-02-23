package br.ufpr.tads.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufpr.tads.pokedex.R;
import br.ufpr.tads.pokedex.model.Pokemon;

public class AdapterPokemons extends RecyclerView.Adapter<AdapterPokemons.MyViewHolder> {

    List<Pokemon> pokemons;

    public AdapterPokemons(List<Pokemon> pokemonList) {
        this.pokemons = pokemonList;
    }

    @NonNull
    @Override
    public AdapterPokemons.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_pokemon, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPokemons.MyViewHolder holder, int position) {
        Pokemon pokemon = this.pokemons.get(position);
        holder.nomeTextView.setText(pokemon.getNome());
        holder.habilidadeTextView.setText(pokemon.getHabilidades());
        holder.tipoTextView.setText(String.valueOf(pokemon.getTipo()));
    }

    @Override
    public int getItemCount() {
        return this.pokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView, habilidadeTextView, tipoTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomePokemonItemTextView);
            habilidadeTextView = itemView.findViewById(R.id.habilidadesTextView);
            tipoTextView = itemView.findViewById(R.id.tipoTextView);
        }
    }
}
