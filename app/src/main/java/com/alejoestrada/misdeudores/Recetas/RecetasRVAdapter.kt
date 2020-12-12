package com.alejoestrada.misdeudores.Recetas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.server.Receta
import com.alejoestrada.misdeudores.databinding.FragmentNavRecetasBinding
import com.alejoestrada.misdeudores.databinding.RecetasItemBinding
import com.squareup.picasso.Picasso

class RecetasRVAdapter(
    var recetasList: ArrayList<Receta>,
    val onItemClickListener: OnItemClickListener
) :

    RecyclerView.Adapter<RecetasRVAdapter.RecetasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetasViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recetas_item, parent, false)
        return RecetasViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecetasViewHolder, position: Int) {
        val receta = recetasList[position]
        holder.bindReceta(receta)
    }

    override fun getItemCount(): Int {
        return recetasList.size
    }

    class RecetasViewHolder(
        itemView: View,
        var onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecetasItemBinding.bind(itemView)

        fun bindReceta(receta: Receta) {
            Picasso.get().load(receta.foto).into(binding.fotoRecetaImageView)
            binding.nombreRecetaTextView.text = receta.nombre
            binding.itemCardView.setOnClickListener{
                onItemClickListener.onItemClick(receta)
            }


            // binding.ingredientesTextView.text = receta.ingredientes
            // binding.preparacionTextVIew.text = "hola1"// receta.preparacion


            //   binding.itemCardView.setOnClickListener {
            //      onItemClickListener.onItemClick(receta)
            //  }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(receta: Receta)

    }


}