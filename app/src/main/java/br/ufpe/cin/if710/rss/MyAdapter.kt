package br.ufpe.cin.if710.rss

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.itemlista.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class MyAdapter(private val dataset: List<ItemRSS>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // provide a reference to the views for each data item
    class MyViewHolder(val v: View) : RecyclerView.ViewHolder(v)


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemlista, parent, false)
        return MyViewHolder(v)
    }

    // replace the contents of a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // get element from dataset at this position and
        // replace the contents of the view with that element
        holder.v.apply {
            item_titulo.apply {
                text = dataset[position].title
                onClick {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(dataset[position].link)
                    startActivity(getContext(), i, null)
                }
            }
            item_data.text = dataset[position].pubDate
        }
    }

    // return the size of your dataset
    override fun getItemCount() = dataset.size
}
