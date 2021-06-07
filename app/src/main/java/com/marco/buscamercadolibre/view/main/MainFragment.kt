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


class MainFragment : Fragment(), OnProductClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: ProductAdapter
    private val products = mutableListOf<ProductModel>()

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
        init()
    }

    override fun onProductClicked(product:ProductModel) {
        sharedViewModel.sendProduct(product)
        val action = MainFragmentDirections.actionMainFragmentToProductDetailFragment()
        binding.root.findNavController().navigate(action)
    }

    /**
     * Fragment configuration
     */
    private fun init(){
        //Adapter
        adapter = ProductAdapter(products, this)
        val orientation = binding.root.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_PORTRAIT)
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        else
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = adapter

        //Observe
        productViewModel.productLiveData.observe(viewLifecycleOwner, {
            drawList(it.products)
        })

        productViewModel.pbLiveData.observe(viewLifecycleOwner, {
            if(it){
                products.clear()
                adapter.notifyDataSetChanged()
                binding.textViewNoProducts.isVisible = false
            }
            binding.progressBar.isVisible = it
        })
    }

    /**
     * Draw the product list in fragment
     */
    private fun drawList(it: List<ProductModel>){
        if (it.isEmpty()){
            binding.textViewNoProducts.isVisible = true
        }else{
            binding.textViewNoProducts.isVisible = false
            products.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}
