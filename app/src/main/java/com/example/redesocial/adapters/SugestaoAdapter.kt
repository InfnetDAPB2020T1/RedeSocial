package com.example.redesocial.adapters

import android.app.Activity
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redesocial.R
import com.example.redesocial.models.Perfil
import com.example.redesocial.services.OperacoesConviteService
import com.example.redesocial.ui.carregamentoalerta.LoadingAlerta
import com.example.redesocial.viewmodel.PerfilViewModel

class SugestaoAdapter(listaPerfil : MutableList<Perfil>, activity: Activity, perfilViewModel: PerfilViewModel) : RecyclerView.Adapter<SugestaoAdapter.SugestaoViewHolder>(){

    var listaPerfil = listaPerfil
    var activity = activity
    var perfilViewModel = perfilViewModel

    class SugestaoViewHolder(itemView: View, listaPerfil: MutableList<Perfil>, sugestaoAdapter: SugestaoAdapter, activity: Activity, perfilViewModel: PerfilViewModel):
        RecyclerView.ViewHolder(itemView){

        var nomeCard = itemView.findViewById<TextView>(R.id.username_sugest)
        var avatarCard = itemView.findViewById<ImageView>(R.id.foto_sugest)

        var btn_convida = itemView.findViewById<ImageButton>(R.id.adiciona_pessoa_sugest).setOnClickListener {
            ConvidarPerfilAsync(activity,perfilViewModel,listaPerfil[adapterPosition],listaPerfil,adapterPosition,sugestaoAdapter).execute()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugestaoViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_sugestao, parent, false)

        return SugestaoViewHolder(card, listaPerfil,this,activity,perfilViewModel)
    }

    override fun getItemCount() = listaPerfil.size

    override fun onBindViewHolder(holder: SugestaoViewHolder, position: Int) {

        holder.nomeCard.text = listaPerfil[position].nome
        //holder.avatarCard.setImageResource(listaPerfil[position].foto)
        holder.avatarCard.setImageResource(R.drawable.userpeqbkggray)
    }

    class ConvidarPerfilAsync(activity: Activity, perfilViewModel: PerfilViewModel, pessoa: Perfil, listaPerfil: MutableList<Perfil>, position: Int, sugestaoAdapter: SugestaoAdapter) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var pessoa = pessoa
        var listaPerfil = listaPerfil
        var position = position
        var sugestaoAdapter = sugestaoAdapter
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Enviando convite...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            var sucesso = OperacoesConviteService.getInstance().enviarConvite(activity,perfilViewModel.perfilAtual!!.id!!,pessoa.id!!)

            dialogApi.dismiss()

            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)

            if(result)
            {
                listaPerfil.removeAt(position)
                sugestaoAdapter.notifyItemRemoved(position);
            }

        }

    }




}