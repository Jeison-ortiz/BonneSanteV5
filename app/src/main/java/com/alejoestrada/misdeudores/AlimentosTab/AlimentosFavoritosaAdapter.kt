package com.alejoestrada.misdeudores.AlimentosTab

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alejoestrada.misdeudores.R
import com.alejoestrada.misdeudores.data.Alimento
import com.alejoestrada.misdeudores.databinding.AlimentosFavsItemBinding
import com.squareup.picasso.Picasso

class AlimentosFavoritosaAdapter( var alimentosList : ArrayList<Alimento>, val onItemClickListener : OnItemClickListener): RecyclerView.Adapter<AlimentosFavoritosaAdapter.AlimentosFavoritosaViewHolder>(){
    var pos : Int = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlimentosFavoritosaViewHolder {
        val itemView
                = LayoutInflater.from(parent.context).inflate(R.layout.alimentos_favs_item, parent, false)
        return AlimentosFavoritosaViewHolder(itemView,onItemClickListener )
    }


    override fun onBindViewHolder(holder: AlimentosFavoritosaViewHolder, position: Int) {
       val alimento = alimentosList[position]
        pos = position
        holder.bindAlimento(alimento)
    }

    override fun getItemCount(): Int {
        return alimentosList.size
    }


    class AlimentosFavoritosaViewHolder(itemView: View,
                                        var onItemClickListener: OnItemClickListener
    ): RecyclerView.ViewHolder(itemView){
        private val binding = AlimentosFavsItemBinding.bind(itemView)

        fun bindAlimento(alimento: Alimento){
            Picasso.get().load(alimento.foto).into(binding.imageView3)
            binding.nombreAlimentoTextView4.text= alimento.id
            binding.caloriasTextView4.text = alimento.calorias.toString() + " Cal"

            binding.checkImageView3.setOnClickListener {
                onItemClickListener.onItemClick(alimento,this.adapterPosition)
            }
        }

    }


    interface OnItemClickListener {
        fun onItemClick(alimento: Alimento, pos : Int)
    }


}