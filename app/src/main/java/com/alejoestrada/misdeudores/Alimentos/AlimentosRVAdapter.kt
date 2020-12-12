package com.alejoestrada.misdeudores.Alimentos

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.Recetas.ListaRecetasFragmentDirections
import com.alejoestrada.misdeudores.Registro.RegistroActivity
import com.alejoestrada.misdeudores.Registro.RegistroActivity.Companion.TAG
import com.alejoestrada.misdeudores.data.Alimento
import com.alejoestrada.misdeudores.data.server.Receta
import com.alejoestrada.misdeudores.databinding.AlimentosItemBinding

class AlimentosRVAdapter(var alimentosList: ArrayList<Alimento>,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<AlimentosRVAdapter.AlimentosViewHolder>() {




    // paren es el papa que lo contiene, retorna un DeudoresViewHolder lo que esta despues de :
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlimentosViewHolder { // creamos el item view
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.alimentos_item, parent, false)
        return AlimentosViewHolder(itemView,onItemClickListener)
    }


    //  para mostrar la cantidad de datos que tenga
    override fun onBindViewHolder(holder: AlimentosViewHolder, position: Int) {
        val alimento = alimentosList[position]
        holder.bindAlimento(alimento)
    }

    override fun getItemCount(): Int {
        return alimentosList.size
    }




    // cuando la clase esta en rojo se deben poner las funciones de sobrecarga que el nos da por defecto
    // esta ereda de RecyclerView.ViewHolder

    class AlimentosViewHolder(itemView: View,
                              var onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) { // el itemVIew los manda la clase externa
        private val binding = AlimentosItemBinding.bind(itemView)

        fun bindAlimento(alimento: Alimento) {  // establece la información a mostrar
          // Picasso.get().load(deudor.foto).into(binding.fotoImageView)

               binding.nombreAlimentoTextView.text = alimento.id
               binding.caloriasTextView.text = alimento.calorias.toString()

                binding.favImageButton.setOnClickListener{
                    onItemClickListener.onItemClick(alimento)
               }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(alimento: Alimento)
    }


}