package com.marco.buscamercadolibre.view.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marco.buscamercadolibre.databinding.CustomItemProductPictureBinding
import com.marco.buscamercadolibre.model.product.PictureModel
import com.squareup.picasso.Picasso

class ProductDetailAdapter(private var pictures:List<PictureModel>): RecyclerView.Adapter<ProductDetailAdapter.ProductDetailHolder>() {

    private val TAG = "ProductAdapter"

    class ProductDetailHolder(val binding:CustomItemProductPictureBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailHolder {
        val binding = CustomItemProductPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductDetailHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDetailHolder, position: Int) {
        with(holder){
            with(pictures[position]){
                Picasso.get().load(this.url).into(binding.imageViewPicture)
            }
        }
    }

    override fun getItemCount(): Int = pictures.size

}