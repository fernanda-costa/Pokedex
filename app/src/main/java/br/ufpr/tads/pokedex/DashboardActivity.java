package br.ufpr.tads.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case   R.id.add_pokemon_menu_item:
                intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
                break;
            case   R.id.listar_pokemon_menu_item:
                intent = new Intent(this, ListarActivity.class);
                startActivity(intent);
                break;
            case R.id.sair_menu_item:
                finishAffinity();
                System.exit(0);
                break;
        }



        return super.onOptionsItemSelected(item);
    }


}