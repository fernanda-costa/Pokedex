package br.ufpr.tads.pokedex.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.ufpr.tads.pokedex.adapter.AdapterPokemons;
import br.ufpr.tads.pokedex.model.Pokemon;
import br.ufpr.tads.pokedex.model.Usuario;

public class GetPokemonsTask extends AsyncTask<String, Void, List<Pokemon>> {

    RecyclerView recyclerViewPokemons;
    ProgressDialog progressDialog;
    private Context context;
    AdapterPokemons adapter;
    List<Pokemon> pokemons;
    public GetPokemonsTask(ProgressDialog progressDialog, RecyclerView recyclerViewPokemons, Context applicationContext, AdapterPokemons adapter, List<Pokemon> dados) {
        this.progressDialog = progressDialog;
        this.pokemons = dados;
        this.context = applicationContext;
        this.adapter = adapter;
        this.recyclerViewPokemons = recyclerViewPokemons;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Aguarde...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Pokemon> pokemons) {
        super.onPostExecute(pokemons);
        recyclerViewPokemons.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    @Override
    protected List<Pokemon> doInBackground(String... strings) {
        String stringURL = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer buffer = null;

        try {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            buffer = new StringBuffer();
            String line = "";
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pokemons.clear();
            JSONArray jsonList = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject object = jsonList.getJSONObject(i);
                String nome = object.getString("poke_nome");
                int tipo = object.getInt("tipo");
                String habilidade = object.getString("habilidades");
                String usuario = object.getString("usuario");
                String foto = object.getString("foto");
                pokemons.add(new Pokemon(nome, tipo, habilidade, new Usuario()));
            }

            return pokemons;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
