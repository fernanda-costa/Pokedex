package br.ufpr.tads.pokedex.tasks;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;

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
import br.ufpr.tads.pokedex.ListarPokemonsActivity;
import br.ufpr.tads.pokedex.R;

public class AddPokemonTask  extends AsyncTask<String, Void, Boolean> {
    public static final String PREFS_NAME = "USUARIO_LOGADO";

    Spinner tipo;
    EditText nomeEditText, habilidadeEditText;
    CadastroActivity cadastroActivity;

    public AddPokemonTask(CadastroActivity cadastroActivity) {
        this.cadastroActivity = cadastroActivity;
        this.nomeEditText = cadastroActivity.findViewById(R.id.nomeEditText);
        this.tipo = cadastroActivity.findViewById(R.id.tipoSpinner);
        this.habilidadeEditText = cadastroActivity.findViewById(R.id.habilidadeEditText);
    }


    @Override
    protected void onPostExecute(Boolean logado) {
        super.onPostExecute(logado);

        Intent irParaLista = new Intent(cadastroActivity.getApplicationContext(), ListarPokemonsActivity.class);
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

            SharedPreferences sp1 = cadastroActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String usuario = sp1.getString("usuario", null);

            postDataParams.put("nome", this.nomeEditText.getText().toString());
            postDataParams.put("tipo",  String.valueOf(this.tipo.getSelectedItemPosition()));
            postDataParams.put("habilidades",  this.habilidadeEditText.getText().toString());
            postDataParams.put("usuario", usuario);
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
