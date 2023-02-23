package br.ufpr.tads.pokedex.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.List;

public class PesquisarPokemonTask extends AsyncTask<String, Void, List<String>> {

        private ListView listPokemons;
        private ProgressDialog progressDialog;
        ArrayAdapter adapter;
        private ArrayList<String> dados;
        private Context context;

    public PesquisarPokemonTask(ProgressDialog progressDialog, ListView listPokemons, Context applicationContext, ArrayAdapter<String> adapter, ArrayList dados) {
        this.progressDialog = progressDialog;
        this.listPokemons = listPokemons;
        this.context = applicationContext;
        this.adapter = adapter;
        this.dados = dados;
    }

        @Override
        protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Aguarde...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

        @Override
        protected void onPostExecute(List<String> pokemons) {
        super.onPostExecute(pokemons);

        listPokemons.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

        @Override
        protected List<String> doInBackground(String... strings) {
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
            List<String> pokemons = new ArrayList<>();
            JSONArray jsonList = new JSONArray(buffer.toString());
            dados.clear();
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject object = jsonList.getJSONObject(i);
                String nome = object.getString("poke_nome");
                dados.add(nome);
            }

            return pokemons;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
