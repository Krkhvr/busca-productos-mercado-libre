package com.marco.buscamercadolibre.view.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.marco.buscamercadolibre.R
import com.marco.buscamercadolibre.databinding.FragmentProductDetailBinding
import com.marco.buscamercadolibre.model.product.AttributeModel
import com.marco.buscamercadolibre.model.product.PictureModel
import com.marco.buscamercadolibre.model.product.ProductModel
import com.marco.buscamercadolibre.viewmodel.SharedViewModel

/**
 * Fragment que muestra los detalles de un producto seleccionado
 */
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: ProductDetailAdapter
    private val pictures = mutableListOf<PictureModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * Configuraciones iniciales del fragment
     */
    private fun init(){
        //Adapter
        adapter = ProductDetailAdapter(pictures)
        binding.viewPagerPictures.adapter = adapter
        binding.viewPagerPictures.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //Observe
        sharedViewModel.selected.observe(viewLifecycleOwner, Observer {
            drawProduct(it)
        })
    }

    /**
     * Muestra los detalles del producto en pantalla
     */
    private fun drawProduct(product:ProductModel){
        binding.textViewName.text = product.name
        binding.textViewId.text = product.id
        binding.textViewStatus.text = product.status
        binding.textViewDomain.text = product.domainId
        binding.textViewFeatures.text = getFeatures(product.attributeModel)

        pictures.clear()
        pictures.addAll(product.pictures)
        adapter.notifyDataSetChanged()
    }

    /**
     * Construye una lista de atributos de un producto para poder mostrarlos en pantalla en un solo [TextView]
     * @param attributeModel Lista de atributos del producto
     * @return Devueve una cadena concatenada con todos los atributos del producto
     */
    private fun getFeatures(attributeModel: List<AttributeModel>): String{
        var features = ""
        attributeModel.forEach {
            features+="${it.name}: ${it.value}\n"
        }
        return features
    }
}