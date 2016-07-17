package enterprise.com.ni.sifac.Actividades;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ni.sifac.dao.Cartera;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import enterprise.com.ni.sifac.R;
import enterprise.com.ni.sifac.fragmento.CarteraFragmentList;
import enterprise.com.ni.sifac.fragmento.CarteraFragmentListener;
import enterprise.com.ni.sifac.servicio.SifacService;

public class CobroActivity extends AppCompatActivity {


    SifacService service;
    CarteraFragmentListener listener;
    List<Cartera> list;
    private DrawerLayout drawerLayout;
    private String drawerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobro);
        ButterKnife.bind(this);

        list = new ArrayList<Cartera>();
        list.addAll(DaoAPP.getSession().getCarteraDao().loadAll());
        for (int i = 0; i <= 20; i++) {
            for (Cartera c : DaoAPP.getSession().getCarteraDao().loadAll()) {
                Log.e("Cartera", c.getNombreCompleto());
                list.add(c);
            }
        }

       /* for (Cartera c:   DaoAPP.getSession().getCarteraDao().loadAll()) {
            Log.e("Cartera" , c.getNombreCompleto());
            list.add(c);
        }

    */
        setToolbar(); // Setear Toolbar como action bar

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        drawerTitle = getResources().getString(R.string.cartera_item);
        if (savedInstanceState == null) {
            selectItem(drawerTitle);
        }


        CarteraFragmentList fragmentList = (CarteraFragmentList) getSupportFragmentManager().findFragmentById(R.id.main_content);
        fragmentList.setRetainInstance(true);
        fragmentList.setData(list);
        listener = (CarteraFragmentListener) fragmentList;

    }
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(String title) {
        if (drawerLayout!=null )
        // Enviar título como arguemento del fragmento
            drawerLayout.closeDrawers(); // Cerrar drawer

        setTitle(title); // Setear título actual

    }

}
