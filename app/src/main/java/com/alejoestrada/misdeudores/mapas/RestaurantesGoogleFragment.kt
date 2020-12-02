package com.alejoestrada.misdeudores.mapas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.databinding.FragmentRestaurantesGoogleBinding
import kotlinx.android.synthetic.main.fragment_restaurantes_google_.*


class RestaurantesGoogleFragment : Fragment() {
        private lateinit var binding : FragmentRestaurantesGoogleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurantes_google_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding= FragmentRestaurantesGoogleBinding.bind(view)

       vegan_imageView2.setOnClickListener {
            val action = RestaurantesGoogleFragmentDirections.actionRestaurantesGoogleFragmentToMapaRestauranteFragment("veganos")
            findNavController().navigate(action)

        }
        vegetariano_imageView.setOnClickListener {
            val action = RestaurantesGoogleFragmentDirections.actionRestaurantesGoogleFragmentToMapaRestauranteFragment("vegetarianos")
            findNavController().navigate(action)


        }
        variado_imageView.setOnClickListener {
            val action = RestaurantesGoogleFragmentDirections.actionRestaurantesGoogleFragmentToMapaRestauranteFragment("flexibles")
            findNavController().navigate(action)
        }

    }


}