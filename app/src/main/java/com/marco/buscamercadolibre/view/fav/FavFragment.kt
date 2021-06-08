package com.marco.buscamercadolibre.view.fav

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.marco.buscamercadolibre.databinding.FragmentFavBinding

/**
 * Fragmento para gestinar los productos favoritos de un usuario
 */
class FavFragment : Fragment() {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
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
    private fun init() {
        setHasOptionsMenu(true)
    }
}