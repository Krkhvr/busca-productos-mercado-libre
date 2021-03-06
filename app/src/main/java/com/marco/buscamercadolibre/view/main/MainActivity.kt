package com.marco.buscamercadolibre.view.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.marco.buscamercadolibre.R
import com.marco.buscamercadolibre.common.Utility
import com.marco.buscamercadolibre.databinding.ActivityMainBinding
import com.marco.buscamercadolibre.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity principal de la aplicación, funciona como contenedor de todos los fragments.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:ActivityMainBinding

    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_BuscaMercado)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            setSupportActionBar(binding.activityMenuMain.toolbar)
        }

        init()
        intentHandler(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intentHandler(intent)
    }

    private fun intentHandler(intent: Intent?) {
        intent?.let {
            if (Intent.ACTION_SEARCH == intent.action) {
                if(!Utility.isNetworkConnected(this)){
                    Toast.makeText(this, R.string.no_internet_message, Toast.LENGTH_SHORT).show()
                }else{
                    val query = intent.getStringExtra(SearchManager.QUERY)
                    query?.let{
                        binding.activityMenuMain.toolbar.collapseActionView()
                        productViewModel.searchProducts(query)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        //Configuración de ViewSearch para la búsqueda de productos desde el toobar
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.main_menu_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment_container_view_menu)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container_view_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Configuración inicial de la vista
     */
    private fun init(){
        val navController = findNavController(R.id.fragment_container_view_menu)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationViewMain.setupWithNavController(navController)
    }
}



//TODO Pruebas unitarias

