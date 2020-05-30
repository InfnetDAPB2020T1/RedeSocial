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

class PerfilAdapter(listaPerfil : MutableList<Perfil>, activity: Activity,perfilViewModel: PerfilViewModel, tipoAcao : String) : RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>(){

    var listaPerfil = listaPerfil
    var activity = activity
    var perfilViewModel = perfilViewModel
    var tipoAcao = tipoAcao

    class PerfilViewHolder(itemView: View, listaPerfil: MutableList<Perfil>, perfilAdapter: PerfilAdapter , activity: Activity, perfilViewModel: PerfilViewModel, tipoAcao: String):
        RecyclerView.ViewHolder(itemView){

        var nomeCard = itemView.findViewById<TextView>(R.id.username_lista)
        var avatarCard = itemView.findViewById<ImageView>(R.id.foto_lista)
        var btn_exclui = itemView.findViewById<ImageButton>(R.id.exclui_pessoa).setOnClickListener {

            BloquearPerfilAsync(activity, perfilViewModel, listaPerfil[adapterPosition]).execute()

            var pos = adapterPosition
            listaPerfil.removeAt(adapterPosition)
            perfilAdapter.notifyDataSetChanged()
            perfilAdapter.notifyItemRemoved(pos)
        }

        var btn_adiciona = itemView.findViewById<ImageButton>(R.id.adiciona_pessoa).setOnClickListener {
            if(tipoAcao == "convidar")
                ConvidarPerfilAsync(activity,perfilViewModel,listaPerfil[adapterPosition]).execute()
            else if(tipoAcao == "aceitar")
                AceitarConvitePerfilAsync(activity,perfilViewModel,listaPerfil[adapterPosition]).execute()

            var pos = adapterPosition
            listaPerfil.removeAt(adapterPosition)
            perfilAdapter.notifyDataSetChanged()
            perfilAdapter.notifyItemRemoved(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_pessoa, parent, false)

        return PerfilViewHolder(card, listaPerfil, this, activity,perfilViewModel,tipoAcao)
    }

    override fun getItemCount() = listaPerfil.size

    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {

        holder.nomeCard.text = listaPerfil[position].nome
        //holder.avatarCard.setImageResource(listaPerfil[position].foto)
        holder.avatarCard.setImageResource(R.drawable.userpeqbkggray)
    }

    class BloquearPerfilAsync(activity: Activity, perfilViewModel: PerfilViewModel, pessoa: Perfil) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var pessoa = pessoa
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


            }

        }

    }


    class ConvidarPerfilAsync(activity: Activity, perfilViewModel: PerfilViewModel, pessoa: Perfil) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var pessoa = pessoa
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


            }

        }

    }


    class AceitarConvitePerfilAsync(activity: Activity, perfilViewModel: PerfilViewModel, pessoa: Perfil) : AsyncTask<Void, Void, Boolean>()
    {
        var activity = activity
        var perfilViewModel = perfilViewModel
        var pessoa = pessoa
        var dialogApi = LoadingAlerta(activity)

        override fun onPreExecute() {
            super.onPreExecute()
            dialogApi.startLoadingDialog("Aceitando convite...")
        }

        override fun doInBackground(vararg params: Void?): Boolean {
            var sucesso = OperacoesConviteService.getInstance().aceitarConvite(activity,perfilViewModel.perfilAtual!!.id!!,pessoa.id!!)

            dialogApi.dismiss()

            return sucesso
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)

            if(result)
            {


            }

        }

    }

}