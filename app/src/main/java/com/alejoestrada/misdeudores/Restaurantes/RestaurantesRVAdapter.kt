package com.alejoestrada.misdeudores.Restaurantes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.Alimento
import com.alejoestrada.misdeudores.data.server.Restaurante
import com.alejoestrada.misdeudores.databinding.FragmentRestauranteBinding
import com.alejoestrada.misdeudores.databinding.RestauranteItemBinding

class RestaurantesRVAdapter(var restaurantesList: ArrayList<Restaurante>) : RecyclerView.Adapter<RestaurantesRVAdapter.RestaurantesViewHolder>() {

    // paren es el papa que lo contiene, retorna un DeudoresViewHolder lo que esta despues de :
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantesViewHolder { // creamos el item view
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurante_item, parent, false)
        return RestaurantesViewHolder(itemView)
    }


    //  para mostrar la cantidad de datos que tenga
    override fun onBindViewHolder(holder: RestaurantesViewHolder, position: Int) {
        val restaurante = restaurantesList[position]
        holder.bindRestaurante(restaurante)
    }

    override fun getItemCount(): Int {
        return restaurantesList.size
    }


    // cuando la clase esta en rojo se deben poner las funciones de sobrecarga que el nos da por defecto
    // esta ereda de RecyclerView.ViewHolder

    class RestaurantesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { // el itemVIew los manda la clase externa
        private val binding = RestauranteItemBinding.bind(itemView)

        fun bindRestaurante(restaurante: Restaurante) {  // establece la información a mostrar
            // Picasso.get().load(deudor.foto).into(binding.fotoImageView)
            binding.nombreRestauranteTextView.text = restaurante.id
           binding.ciudadRestauranteTextView.text = restaurante.ciudad

        }
    }


}