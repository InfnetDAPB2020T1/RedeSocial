package com.example.redesocial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redesocial.R
import com.example.redesocial.models.Mensagem
import com.example.redesocial.models.MensagemTimeline
import com.google.android.material.imageview.ShapeableImageView

class MensagemTimelineAdapter(listaMensagens : MutableList<Mensagem>) : RecyclerView.Adapter<MensagemTimelineAdapter.MensagemTimelineViewHolder>() {

    var listaMensagens = listaMensagens

    class MensagemTimelineViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var nomeCard = itemView.findViewById<TextView>(R.id.username_timeline)
        var avatarCard = itemView.findViewById<ShapeableImageView>(R.id.foto_lista_timeline)
        var textoCard = itemView.findViewById<TextView>(R.id.mensagem_timeline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemTimelineViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.timeline_item, parent, false)

        return MensagemTimelineViewHolder(card)
    }

    override fun getItemCount() = listaMensagens.size

    override fun onBindViewHolder(holder: MensagemTimelineViewHolder, position: Int) {

        holder.nomeCard.text = listaMensagens[position].autor.nome
        //holder.avatarCard.setImageResource(listaMensagens[position].foto)
        holder.avatarCard.setImageResource(R.drawable.userquadrado)
        holder.textoCard.text = listaMensagens[position].conteudo
    }
}