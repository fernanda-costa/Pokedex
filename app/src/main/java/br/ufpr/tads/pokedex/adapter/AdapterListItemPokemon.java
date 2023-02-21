package br.ufpr.tads.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.pokedex.R;
import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.model.Usuario;

public class AdapterListItemPokemon extends RecyclerView.Adapter<AdapterListItemPokemon.MyViewHolder> {

    private List<Pokemon> pokemons;

    public AdapterListItemPokemon(List<Pokemon> pokemons) {

    }

    public void updateReceiptsList(List<Pokemon> newlist) {
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adpter_list_item_pokemon, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomeTextView.setText(pokemons.get(position).getNome());
        holder.tipoTextView.setText(String.valueOf(pokemons.get(position).getTipo()));
        holder.habilidadeTextView.setText(pokemons.get(position).getHabilidades());
       //holder.pokemonImageView.setImageResource("teste");
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView, tipoTextView, habilidadeTextView;
        ImageView pokemonImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewItemNome);
            habilidadeTextView = itemView.findViewById(R.id.textViewItemHabilidade);
            tipoTextView = itemView.findViewById(R.id.textViewItemTipo);
            pokemonImageView = itemView.findViewById(R.id.imageViewItemPokemon);
        }
    }
}
