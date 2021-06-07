package com.marco.buscamercadolibre.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.marco.buscamercadolibre.databinding.CustomItemProductBinding
import com.marco.buscamercadolibre.model.product.ProductModel
import com.squareup.picasso.Picasso

class ProductAdapter(private var productsList:List<ProductModel>, private val onProductClickListener: OnProductClickListener): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val TAG = "ProductAdapter"

    class ProductHolder(val binding:CustomItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = CustomItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        with(holder){
            with(productsList[position]){
                binding.tvProductName.text = this.name
                if (!this.pictures.isNullOrEmpty()) Picasso.get().load(this.pictures[0].url).into(binding.ivProduct)

                binding.layoutItem.setOnClickListener{
                    onProductClickListener.onProductClicked(productsList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = productsList.size

}