package br.ufpr.tads.pokedex.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import br.ufpr.tads.pokedex.CadastroActivity;
import br.ufpr.tads.pokedex.DashboardActivity;
import br.ufpr.tads.pokedex.ListarActivity;
import br.ufpr.tads.pokedex.LoginActivity;
import br.ufpr.tads.pokedex.R;

public class AddPokemonTask  extends AsyncTask<String, Void, Boolean> {

    EditText nomeEditText, tipoEditText, habilidadeEditText;
    CadastroActivity cadastroActivity;
    public AddPokemonTask(CadastroActivity cadastroActivity) {
        this.cadastroActivity = cadastroActivity;
        this.nomeEditText = cadastroActivity.findViewById(R.id.nomeEditText);
        this.tipoEditText = cadastroActivity.findViewById(R.id.tipoEditText);
        this.habilidadeEditText = cadastroActivity.findViewById(R.id.habilidadeEditText);
    }


    @Override
    protected void onPostExecute(Boolean logado) {
        super.onPostExecute(logado);

        Intent irParaLista = new Intent(cadastroActivity.getApplicationContext(), ListarActivity.class);
        cadastroActivity.startActivity(irParaLista);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String stringURL = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer buffer = null;

        try {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            HashMap<String, String> postDataParams = new HashMap<String, String>();
            postDataParams.put("nome", this.nomeEditText.getText().toString());
            postDataParams.put("tipo",  this.tipoEditText.getText().toString());
            postDataParams.put("habilidades",  this.habilidadeEditText.getText().toString());
            postDataParams.put("usuario", "bruninha");
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
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

        return true;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
