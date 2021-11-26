package com.example.reclyclerviewfirestore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reclyclerviewfirestore.R
import com.example.reclyclerviewfirestore.model.ProductData
import android.content.Context


//creo el adapter y le paso el modelo de datos

class MyAdapter (private var productList: ArrayList<ProductData>, private val context:Context) :RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder (holder: MyViewHolder, position: Int) {
        val p: ProductData = productList[position]
        holder.name.text = p.name
        holder.info.text = p.info

        // utilizo glide para pasar la imagen
        //Glide.with(context)
          //  .load(p.img)
           // .into(holder.img)
        //holder.price.text = productData.price
        //holder.ofert.text = productData.ofert
    }

    override fun getItemCount(): Int {
       return  productList.size
    }

        class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.name)

            //val img: ImageView = itemView.findViewById(R.id.productImg)

            val info: TextView = itemView.findViewById(R.id.productInfo)

            //val price: TextView = itemView.findViewById(R.id.price)

            //val ofert: TextView = itemView.findViewById(R.id.ofert)
        }

    }
