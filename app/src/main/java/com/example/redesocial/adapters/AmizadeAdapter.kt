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

class AmizadeAdapter(listaPerfil : MutableList<Perfil>, activity: Activity,perfilViewModel: PerfilViewModel) : RecyclerView.Adapter<AmizadeAdapter.AmizadeViewHolder>(){

    var listaPerfil = listaPerfil
    var activity = activity
    var perfilViewModel = perfilViewModel

    class AmizadeViewHolder(itemView: View, listaPerfil : MutableList<Perfil>, amizadeAdapter: AmizadeAdapter,activity: Activity,perfilViewModel: PerfilViewModel):
        RecyclerView.ViewHolder(itemView){

        var nomeCard = itemView.findViewById<TextView>(R.id.username_amigo)
        var avatarCard = itemView.findViewById<ImageView>(R.id.foto_amigo)
        var btn_exclui_amigo = itemView.findViewById<ImageButton>(R.id.exclui_amigo).setOnClickListener{
            BloquearAmizadeAsync(activity,perfilViewModel,listaPerfil[adapterPosition],listaPerfil,adapterPosition,amizadeAdapter).execute()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmizadeViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_amigo, parent, false)

        return AmizadeViewHolder(card, listaPerfil, this, activity,perfilViewModel)
    }

    override fun getItemCount() = listaPerfil.size

    override fun onBindViewHolder(holder: AmizadeViewHolder, position: Int) {

        holder.nomeCard.text = listaPerfil[position].nome
        //holder.avatarCard.setImageResource(listaPerfil[position].foto)
        holder.avatarCard.setImageResource(R.drawable.userpeqbkggray)
    }


    class BloquearAmizadeAsync(activity: Activity, perfilViewModel: PerfilViewModel, pessoa: Perfil, listaPerfil: MutableList<Perfil>,position: Int,amizadeAdapter: AmizadeAdapter) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var pessoa = pessoa
        var listaPerfil = listaPerfil
        var position = position
        var amizadeAdapter = amizadeAdapter
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Bloqueando usuário...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            var sucesso = OperacoesConviteService.getInstance().bloquearUsuario(activity,perfilViewModel.perfilAtual!!.id!!, pessoa.id!!)

            dialogApi.dismiss()

            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)

            if(result)
            {
                listaPerfil.removeAt(position)
                amizadeAdapter.notifyItemRemoved(position);
            }

        }

    }

}