package com.example.gustavooliveira.empiretitanssociotorcedor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.Alterar;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.Duvidas;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.FaleConosco;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.Historico;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.Ingresso;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments.Inicio;
import com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Login;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Inicio()).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            confirmacaoSair();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Inicio()).commit();
        } else if (id == R.id.nav_ingresso) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Ingresso()).commit();
        } else if (id == R.id.nav_historico) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Historico()).commit();
        } else if (id == R.id.nav_alterar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Alterar()).commit();
        } else if (id == R.id.nav_fale_conosco) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new FaleConosco()).commit();
        } else if (id == R.id.nav_duvidas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_principal, new Duvidas()).commit();
        } else if (id == R.id.nav_sair) {
            confirmacaoSair();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void confirmacaoSair() {
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setTitle("Deseja realmente sair?");
        msg.setIcon(R.drawable.icon_sair);
        msg.setMessage("Tem certeza que deseja sair da sua sessão atual do Empire Titans Sócio Torcedor?");
        msg.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        msg.setPositiveButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Nada acontece
            }
        });

        msg.show();
    }

}
