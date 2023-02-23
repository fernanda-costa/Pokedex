package br.ufpr.tads.pokedex.tasks;


import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.ufpr.tads.pokedex.DashboardActivity;
import br.ufpr.tads.pokedex.R;

public class GetTotalPokemonsTask extends AsyncTask<String, Void, Integer>  {

    TextView total;

    public GetTotalPokemonsTask(DashboardActivity dashboardActivity) {
        total = dashboardActivity.findViewById(R.id.totalTextView);
    }

    @Override
    protected void onPostExecute(Integer totalPokemons) {
        super.onPostExecute(totalPokemons);
        total.setText("Total de pokemons" + totalPokemons.toString());
    }

    @Override
    protected Integer doInBackground(String... strings) {
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
            JSONObject object = new JSONObject(buffer.toString());
            Integer total = object.getInt("total");

            return total;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
