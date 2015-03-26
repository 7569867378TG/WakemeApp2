package com.example.grupo1.wakemeapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity /*implements FragmentManager.OnBackStackChangedListener*/{

    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private String[] titles;
    private ArrayList<item_objct> NavItems;
    private TypedArray NavIcons;
    NavDrawerListAdapter NavAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;
    private int posFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawer Layout
        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Lista
        NavList = (ListView) findViewById(R.id.lista);

        //Declaramos el header el cual sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header,null);
        //Establecemos el header
        NavList.addHeaderView(header);

        //tomamos listado de imagenes desde drawable
        NavIcons=getResources().obtainTypedArray(R.array.navigation_iconos);
        //tomamos listado de titulos desde el string-array de los recursos @string/nav_options
        titles =getResources().getStringArray(R.array.nav_options);
        //Listado de titulos de barra de navegacion
        NavItems=new ArrayList<item_objct>();

        //Agregamos objetos item_objct al array
        //Home
        NavItems.add(new item_objct(titles[0],NavIcons.getResourceId(0,-1)));
        //lineas
        NavItems.add(new item_objct(titles[1],NavIcons.getResourceId(1,-1)));
        //alarmas
        NavItems.add(new item_objct(titles[2],NavIcons.getResourceId(2,-1)));
        //Favoritos
        NavItems.add(new item_objct(titles[3],NavIcons.getResourceId(3,-1)));
        //Informacion
        NavItems.add(new item_objct(titles[4],NavIcons.getResourceId(4,-1)));


        //Declaramos y seteamos nuestro adaptador al cual le pasamos el array con los titulos
       NavAdapter=new NavDrawerListAdapter (this, NavItems);
       NavList.setAdapter(NavAdapter);

        //Declaramos el mDrawerToggle y las img a utilizar
        mDrawerToggle=new ActionBarDrawerToggle(
                this,
                NavDrawerLayout,
                R.drawable.ic_action_storage,
                R.string.app_name,
                R.string.hello_world
        ){
            //llamado cuando el drawer alcanza el estado cerrado completamente
            public void onDrawerClosed(View view){
                Log.e("cerrado completo", "!!");
            }
            //llamado cuando el drawer esta completamente abierto
            public void onDrawerOpened(View view){
                Log.e("Apertura completa","!!");
            }
         };
        //Establecemos que mdrawerToggle sea el Drawerlistener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);
        //Establecemos que el ActionBar muestre el boton Home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        //Establecemos la accion al clickear sobre cualquier item del menu.
        //De la misma forma que haciamos en una app comun con un listview
        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    MostrarFragment(position);
                } catch (Exception e) {
                }
            }
        });
        //cuando la aplicacion cargue por defecto, mostrar la opcion home.
        MostrarFragment(1);
    }


    //pasando la posicion de la opcion en el menu nos mostrara el Fragment correspondiente
    private void MostrarFragment(int position){
        Fragment fragment = null;
        String name=titles[position-1];

        posFragment=position;
        switch(position){
            case 1:
                fragment= new HomeFragment();
                break;
            case 2:
                fragment = new LineasFragment();
                break;
            case 3:
                fragment = new AlarmaFragment();
                break;
            case 4:
                fragment = new FavoritosFragment();
                break;
            case 5:
                fragment = new InfoFragment();
                break;
            default:
                //si no esta la opcion mostrara un toast y nos mandara a home
                Toast.makeText(getApplicationContext(),"opcion "+titles[position-1]+" no disponible", Toast.LENGTH_SHORT).show();
                fragment=new HomeFragment();
                position=1;
                break;
        }

        //Validamos si el fragment no es nulo
        if(fragment!=null) {

            fragmentManager = getFragmentManager();

           /* fragmentManager.addOnBackStackChangedListener(this);*/
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack(titles[position-1]).commit();

            //Actualizamos el contenido segun la opcion elegida
            NavList.setItemChecked(position, true);
            NavList.setSelection(position);
            //Cambiamos el titulo en donde decia
            setTitle(titles[position - 1]);
            //cerramos el menu deslizable
            NavDrawerLayout.closeDrawer(NavList);
        }else{
                //si el fragment es nulo mostramos un mensaje de error
                Log.e("Error ", "MostrarFragment" + position);
            }
    }




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       //Handle action bar item clicks here. The action bar will
       //automatically handle clicks on the Home/Up button, so long
       //as you specify a parent activity in AndroidManifest.xml.
       int id = item.getItemId();

       //noinspection SimplifiableIfStatement
       if (id == R.id.action_settings) {
           return true;
       }
       // Pass the event to ActionBarDrawerToggle, if it returns
       // true, then it has handled the app icon touch event
       if (mDrawerToggle.onOptionsItemSelected(item)) {
           return true;
       }


       return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackStackChanged() {


        Toast.makeText(getApplicationContext(),"Status Back Stack", Toast.LENGTH_SHORT).show();

        int count = fragmentManager.getBackStackEntryCount();
        Toast.makeText(getApplicationContext(),"BackStack Size: " + String.valueOf(count), Toast.LENGTH_SHORT).show();
        for (int i=count-1;i>=0;i--){
            Toast.makeText(getApplicationContext(),fragmentManager.getBackStackEntryAt(i).getName().toString(), Toast.LENGTH_SHORT).show();

        }
        fragmentManager.removeOnBackStackChangedListener(this);

    }*/

    @Override
    public void onBackPressed(){
            if(NavDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                NavDrawerLayout.closeDrawer(Gravity.LEFT);
            }
            else{

                if(posFragment > 1){
                    //Volver a Home cuando pulso retroceso y no estoy en Home
                    MostrarFragment(1);
                }
                else cuadroDialogo(); //Cuando estoy en Home
            }
    }
    public void cuadroDialogo(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(R.string.dialogTitle);
        alertDialog.setMessage(R.string.dialogMessage);
        alertDialog.setPositiveButton(R.string.yesDialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });
        alertDialog.setNegativeButton(R.string.noDialog,null);
        alertDialog.show();

    }

}
