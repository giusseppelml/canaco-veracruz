package com.beta.giusseppe.canacoveracruz.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.SharedPrefManager;
import com.beta.giusseppe.canacoveracruz.email.Config;
import com.beta.giusseppe.canacoveracruz.fragments.AcercaFragment;
import com.beta.giusseppe.canacoveracruz.fragments.AfiliacionFragment;
import com.beta.giusseppe.canacoveracruz.fragments.BolsaDeTrabajoFragment;
import com.beta.giusseppe.canacoveracruz.fragments.InstitutoFragment;
import com.beta.giusseppe.canacoveracruz.fragments.OwlFragment;
import com.beta.giusseppe.canacoveracruz.fragments.VacantesFragment;
import com.beta.giusseppe.canacoveracruz.fragments.CapacitacionesFragment;
import com.beta.giusseppe.canacoveracruz.fragments.ContactanosFragment;
import com.beta.giusseppe.canacoveracruz.fragments.ContenidoFragment;
import com.beta.giusseppe.canacoveracruz.fragments.ContenidoSiemFragment;
import com.beta.giusseppe.canacoveracruz.fragments.ConveniosFragment;
import com.beta.giusseppe.canacoveracruz.fragments.DirectorioFragment;
import com.beta.giusseppe.canacoveracruz.fragments.LoginFragment;
import com.beta.giusseppe.canacoveracruz.fragments.PerfilFragment;
import com.beta.giusseppe.canacoveracruz.fragments.PrincipalFragment;
import com.beta.giusseppe.canacoveracruz.fragments.RegistrarseFragment;
import com.beta.giusseppe.canacoveracruz.fragments.TurismoFragment;
import com.beta.giusseppe.canacoveracruz.models.User;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawableLayout;
    private NavigationView navigationView;

    private MenuItem login;
    private MenuItem signup;
    private MenuItem close;
    private MenuItem per;

    private final int PHONE_CALL_CODE = 100;

    private Toolbar myToolbar;

    private Fragment fragmentMenu;
    private String tituloMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();


        drawableLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        headerText();

        setFragmentByDefault();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTramsaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.Perfil:
                        //checa si no esta logueado y deja las opciones en default
                        //de lo contrario desactiva el login y el registro
                        if (!SharedPrefManager.getInstance(MainActivity.this).isLoggedIn()) {
                            Toast.makeText(MainActivity.this, "Usted no es Afiliado", Toast.LENGTH_SHORT).show();
                        } else {
                            //Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            //startActivity(intent);

                            fragment = new PerfilFragment();
                            fragmentTramsaction = true;
                            getSupportActionBar().setElevation(4);
                            drawableLayout.closeDrawers();
                        }
                        break;
                    case R.id.school:
                        fragment = new InstitutoFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_afiliacion:
                        fragment = new AfiliacionFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(0);
                        break;
                    case R.id.menu_contenido:
                        fragment = new ContenidoFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(0);
                        break;
                    case R.id.menu_turismo:
                        fragment = new TurismoFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_convenio:
                        fragment = new ConveniosFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_directorio:
                        fragment = new DirectorioFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_contactanos:
                        fragment = new ContactanosFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_opcion_1:
                        fragment = new OwlFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        drawableLayout.closeDrawers();
                        break;
                    case R.id.menu_opcion_2:
                        drawableLayout.closeDrawers();
                        break;
                    case R.id.acerca:
                        fragment = new AcercaFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_siem:
                        fragment = new ContenidoSiemFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(0);
                        break;
                    case R.id.menu_cursos:
                        fragment = new CapacitacionesFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(4);
                        break;
                    case R.id.menu_bolsa:
                        fragment = new BolsaDeTrabajoFragment();
                        fragmentTramsaction = true;
                        getSupportActionBar().setElevation(0);
                        break;
                    case R.id.menu_location:
                        Intent map = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(map);
                        getSupportActionBar().setElevation(4);
                        break;
                }

                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                if (fragmentTramsaction) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            //la linea de abajo es para poder volver al fragment
                            .addToBackStack(null)
                            .commit();
                    //item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawableLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    private void setToolbar() {
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new PrincipalFragment())
                .commit();

        MenuItem item = navigationView.getMenu().getItem(0);
        getSupportActionBar().setTitle("Canaco Ver.");
    }

    //Aqui hemos inflado el action bar en nuestro layout principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        this.login = menu.findItem(R.id.ingresarid);
        this.signup = menu.findItem(R.id.registrarseid);
        this.close = menu.findItem(R.id.logSalirOut);
        this.per = menu.findItem(R.id.perfilesid);

        //checa si no esta logueado y deja las opciones en default
        //de lo contrario desactiva el login y el registro
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            this.login.setVisible(true);
            this.signup.setVisible(true);
            this.close.setVisible(false);
            this.per.setVisible(false);
        } else {
            this.login.setVisible(false);
            this.signup.setVisible(false);
            this.close.setVisible(true);
            this.per.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ingresarid:
                //nos lleva al fragment de login
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setElevation(4);
                Config.x = true;
                fragmentMenu = new LoginFragment();
                tituloMenu = "Ingresar";
                fragmentsOn(fragmentMenu, tituloMenu);
                return true;

            case R.id.registrarseid:
                //nos lleva al activity de registro
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportActionBar().setElevation(4);
                Config.x = true;
                fragmentMenu = new RegistrarseFragment();
                tituloMenu = "Registrarse";
                fragmentsOn(fragmentMenu, tituloMenu);
                return true;

            case R.id.phone:
                showInfoAlertCall();
                return true;
            case R.id.perfilesid:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentMenu = new PerfilFragment();
                getSupportActionBar().setElevation(4);
                tituloMenu = "Perfil";
                fragmentsOn(fragmentMenu, tituloMenu);
                return true;
            //Cierra la sesion eliminando los datos del SharedPreferences
            case R.id.logSalirOut:
                showInfoAlert();
                return true;
            case R.id.Ubicacion:
                Intent map = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(map);
                getSupportActionBar().setElevation(4);
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                return true;
            case android.R.id.home:
                drawableLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void headerText() {
        // obtener el usuario actual
        User user = SharedPrefManager.getInstance(this).getUser();

        //direcciona al header el nombre del usuario logueado
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_nombre);
        navUsername.setText(user.getUsername());
    }

    private void showInfoAlert() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Cerrar Sesión?")
                .setMessage("Seguro que desea cerrar sesion?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void showInfoAlertCall() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Hacer llamada a Canaco Servytur Veracruz?")
                .setMessage("¿Seguro que desea realizar esta acción?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        llamada();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (Config.x == true) {
            getSupportActionBar().setElevation(4);
            getSupportActionBar().setTitle("Canaco Ver.");
        } else {
            getSupportActionBar().setElevation(0);
            Config.x = true;
            getSupportActionBar().setTitle("Afiliación");
        }

        drawableLayout.closeDrawers();
    }

    private void fragmentsOn(Fragment fragment, String titlesMenu) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                //la linea de abajo es para poder volver al fragment
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(titlesMenu);
    }


    private void llamada() {
        String phoneNumber = "2299890270";
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            //Comprobaremos la version de android que corre el programa
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //edicion de la clase 41, si ha aceptado, si no lo ha hecho o si nunca se le pregunto
                if (!CheckPermission(Manifest.permission.CALL_PHONE)) {
                    //ha aceptado
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                        return;
                    startActivity(i);
                } else {
                    //no ha aceptado o es la primera vez que se le pregunta
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        // ha denagado o es la primera vez que se pregunta
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        //no denegado
                        Toast.makeText(MainActivity.this, "please, enable the request permission", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        i.setData(Uri.parse("package:" + getPackageName()));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(i);
                    }
                }
            } else {
                OlderVersions(phoneNumber);
            }
        } else {
            Toast.makeText(MainActivity.this, "Insert a Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    private void OlderVersions(String phoneNumber) {
        //checa los permisos de la forma antigua para versiones anteriores de android
        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (CheckPermission(Manifest.permission.CALL_PHONE)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intentCall);
        } else {
            Toast.makeText(MainActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //concdio su permiso
                        String phoneNumber = "2881164937";
                        Intent intenCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intenCall);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"No hay permiso", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean CheckPermission(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
