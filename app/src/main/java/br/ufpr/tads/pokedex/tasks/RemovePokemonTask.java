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
import java.util.HashMap;
import java.util.List;

import br.ufpr.tads.pokedex.adapter.AdapterPokemons;
import br.ufpr.tads.pokedex.model.Pokemon;

public class RemovePokemonTask extends AsyncTask<String, Void, Void> {
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private Context applicationContext;
    private AdapterPokemons adapterPokemons;
    private List<Pokemon> pokemonList;

    public RemovePokemonTask(ProgressDialog progressDialog, RecyclerView recyclerView, Context applicationContext, AdapterPokemons adapterPokemons, List<Pokemon> pokemonList) {
        this.progressDialog = progressDialog;
        this.recyclerView = recyclerView;
        this.applicationContext = applicationContext;
        this.adapterPokemons = adapterPokemons;
        this.pokemonList = pokemonList;
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        recyclerView.setAdapter(adapterPokemons);
        adapterPokemons.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    @Override
    protected Void doInBackground(String... strings) {
        String stringURL = strings[0];

        try {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
