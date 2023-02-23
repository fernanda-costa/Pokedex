package br.ufpr.tads.pokedex.tasks;


import android.os.AsyncTask;
import android.widget.TextView;

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

import br.ufpr.tads.pokedex.DashboardActivity;
import br.ufpr.tads.pokedex.R;

public class GetTopTiposTask extends AsyncTask<String, Void, HashMap<Integer, Integer>>  {

    TextView topTipos, topTipos2, topTipos3;

    public GetTopTiposTask(DashboardActivity dashboardActivity) {
        topTipos = dashboardActivity.findViewById(R.id.topTipoTextView);
        topTipos2 = dashboardActivity.findViewById(R.id.topTipoTextView2);
        topTipos3 = dashboardActivity.findViewById(R.id.topTipoTextView3);
    }

    @Override
    protected void onPostExecute(HashMap<Integer, Integer> totalPokemons) {
        super.onPostExecute(totalPokemons);
        Object[] keys = totalPokemons.keySet().toArray();

        if(keys.length == 3) {
            topTipos.setText(getTipoValue((int) keys[0]) + " - " + totalPokemons.get(keys[0]));
            topTipos2.setText(getTipoValue((int)  keys[1]) + " - " + totalPokemons.get(keys[1]));
            topTipos3.setText(getTipoValue((int)  keys[2]) + " - " + totalPokemons.get(keys[2]));
        }
    }

    private String getTipoValue(int tipo) {
        String[] tipos = {"fogo", "ar", "agua", "terra"};
        return tipos[tipo];
    }

    @Override
    protected HashMap<Integer, Integer> doInBackground(String... strings) {
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
            HashMap<Integer, Integer> tipos = new HashMap<>();
            JSONArray jsonList = new JSONArray(buffer.toString());
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject object = jsonList.getJSONObject(i);
                Integer tipo = object.getInt("tipo");
                Integer quantidade = object.getInt("total");
                tipos.put(tipo, quantidade);
            }

            return tipos;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
