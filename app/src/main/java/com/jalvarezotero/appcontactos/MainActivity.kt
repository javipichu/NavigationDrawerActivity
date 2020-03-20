package com.finfanterodal.appcontactos

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Creamos la ventada para lanzar el activity_main.xml, carga otros ficheros xml y el navigation view que es el menu lateral.
        setContentView(R.layout.activity_main)
        //Crear toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Crear SnackBar y FAB, botn de accion flotante.
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        //Cargamos distintos layouts que aparecerán al inicio, gestiona todo el menu lateral,
        // conectar el conetenido con el navigation view y hacer que aparezaca o desaparezca.
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        //gestiona la navegación de la  aplicacion dentro del navhost
        val navController = findNavController(R.id.nav_host_fragment)
        //Pasar cada ID de menú como un conjunto de ID
        // el menú debe considerarse como destino de nivel superior.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        //Enlazamos la appBarConfig con el navController
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    //los fragmentos proporcionan su propia devolución de llamada onCreateOptionsMenu()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Cargamos al menu, toolbar un xml con las opciones
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //Se llama a este método cada vez que el usuario elige navegar
    // dentro de la jerarquía de actividad de su aplicación desde la barra de acción.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
