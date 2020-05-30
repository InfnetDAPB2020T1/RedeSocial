package com.example.redesocial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redesocial.R
import com.example.redesocial.models.Perfil

class PerfilAdapter(listaPerfil : List<Perfil>) : RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>(){

    var listaPerfil = listaPerfil

    class PerfilViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){

        var nomeCard = itemView.findViewById<TextView>(R.id.username_lista)
        var avatarCard = itemView.findViewById<ImageView>(R.id.foto_lista)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_pessoa, parent, false)

        return PerfilViewHolder(card)
    }

    override fun getItemCount() = listaPerfil.size

    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {

        holder.nomeCard.text = listaPerfil[position].nome
        //holder.avatarCard.setImageResource(listaPerfil[position].foto)
        holder.avatarCard.setImageResource(R.drawable.userpeqbkggray)
    }

}