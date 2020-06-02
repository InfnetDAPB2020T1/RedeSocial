package com.bemteviinfnet.redesocial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bemteviinfnet.redesocial.R
import com.bemteviinfnet.redesocial.models.Pessoa

class PessoaAdapter(listaPessoas : List<Pessoa>) : RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>(){

    var listaPessoas = listaPessoas

    class PessoaViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){

        var nomeCard = itemView.findViewById<TextView>(R.id.username_lista)
        var avatarCard = itemView.findViewById<ImageView>(R.id.foto_lista)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_pessoa, parent, false)

        return PessoaViewHolder(card)
    }

    override fun getItemCount() = listaPessoas.size

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {

        holder.nomeCard.text = listaPessoas[position].username
        holder.avatarCard.setImageResource(listaPessoas[position].foto)
    }

}