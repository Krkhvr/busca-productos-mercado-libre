package com.marco.buscamercadolibre.view.product

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.marco.buscamercadolibre.R
import com.marco.buscamercadolibre.common.Utility
import com.marco.buscamercadolibre.databinding.FragmentProductDetailBinding
import com.marco.buscamercadolibre.model.product.AttributeModel
import com.marco.buscamercadolibre.model.product.PictureModel
import com.marco.buscamercadolibre.model.product.ProductModel
import com.marco.buscamercadolibre.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment que muestra los detalles de un producto seleccionado
 */
@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private val TAG = "ProductDetailFragment"

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: ProductDetailAdapter
    @Inject lateinit var pictureList: ArrayList<PictureModel>

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }

    /**
     * Configuraciones iniciales del fragment
     */
    private fun init(){
        setHasOptionsMenu(true)

        //Adapter
        adapter = ProductDetailAdapter(pictureList)
        binding.viewPagerPictures.adapter = adapter
        binding.viewPagerPictures.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerPictures.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateCounter(position)
            }
        })

        //Observe
        sharedViewModel.selected.observe(viewLifecycleOwner, Observer {
            drawProduct(it)
        })

        //ClickListeners
        binding.buttonBuyNow.setOnClickListener {
            Toast.makeText(context, getString(R.string.product_purchased), Toast.LENGTH_SHORT).show()
        }

        binding.buttonAddToCar.setOnClickListener {
            Toast.makeText(context, getString(R.string.product_added_to_car), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Actualiza el indice de la imagen mostrado en el carrete
     * @param position Indice de la imagen
     */
    fun updateCounter(position: Int) {
        val counter = "${position+1}/${pictureList.size}"
        binding.textViewCounter.text = counter
    }

    /**
     * Muestra los detalles del producto en pantalla
     */
    private fun drawProduct(product:ProductModel){
        binding.textViewName.text = product.name
        binding.textViewId.text = product.id
        binding.textViewStatus.text = product.status
        binding.textViewDomain.text = product.domainId
        binding.textViewFeatures.text = Utility.convertTextToHtml(Utility.getFeatures(product.attributeModel))

        pictureList.clear()
        pictureList.addAll(product.pictures)
        adapter.notifyDataSetChanged()
    }


}