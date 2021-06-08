package com.marco.buscamercadolibre.view.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.marco.buscamercadolibre.databinding.FragmentMainBinding
import com.marco.buscamercadolibre.model.product.ProductModel
import com.marco.buscamercadolibre.viewmodel.ProductViewModel
import com.marco.buscamercadolibre.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment principal de la aplicación, se encarga de mostrar la lista de productos para una nueva búsqueda
 */
@AndroidEntryPoint
class MainFragment: Fragment(), OnProductClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: ProductAdapter
    @Inject lateinit var products: ArrayList<ProductModel>

    //private val products = mutableListOf<ProductModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unitView(view)
        configObservers()
    }

    override fun onProductClicked(product:ProductModel) {
        sharedViewModel.sendProduct(product)
        val action = MainFragmentDirections.actionMainFragmentToProductDetailFragment()
        binding.root.findNavController().navigate(action)
    }

    /**
     * Configuración inicial del fragment
     */
    private fun unitView(view: View){
        adapter = ProductAdapter(products, this)
        val orientation = binding.root.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_PORTRAIT)
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        else
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = adapter
    }

    /**
     * Configura los Observe de la aplicación
     */
    private fun configObservers() {
        productViewModel.productLiveData.observe(viewLifecycleOwner, {
            drawList(it.products)
        })

        productViewModel.pbLiveData.observe(viewLifecycleOwner, {
            showPB(it)
        })
    }

    /**
     * Pinta la lista de productos en pantalla
     * @param productList Lista de productos que serán mostrados en la lista
     */
    private fun drawList(productList: List<ProductModel>){
        if (productList.isNullOrEmpty()){
            products.clear()
            adapter.notifyDataSetChanged()
            binding.textViewNoProducts.isVisible = true
        }else{
            binding.textViewNoProducts.isVisible = false
            products.addAll(productList)
            adapter.notifyDataSetChanged()
        }
    }

    /**
     * Muestra u oculta la barra de progreso en pantalla
     * @param visible Determina si será visible o no el ProgressBar
     */
    private fun showPB(visible: Boolean){
        if(visible){
            products.clear()
            adapter.notifyDataSetChanged()
            binding.textViewNoProducts.isVisible = false
        }
        binding.progressBar.isVisible = visible
    }
}
