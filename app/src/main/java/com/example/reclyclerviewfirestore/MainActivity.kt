package com.example.reclyclerviewfirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reclyclerviewfirestore.adapter.MyAdapter
import com.example.reclyclerviewfirestore.model.ProductData
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productListArrayList: ArrayList<ProductData>
    private lateinit var adapter: MyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewLista) //instancio Recycler View
        recyclerView.layoutManager = LinearLayoutManager(this)  //asigno el layout manager
        recyclerView.setHasFixedSize(true)

        //array de lista instanciado

        productListArrayList = arrayListOf()  //instancio el array de productos

        adapter = MyAdapter(productListArrayList,this)

        recyclerView.adapter = adapter

        EventChangeListener()

    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()  //instancio base de datos
        db.collection("Products")  // coleccion de firebase. Con listener obtengo los movimientos de la base de datos
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())  //si hay un error muestro mensaje
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {  //si no hay error muestro por pantalla la lista de productos
                        if (dc.type == DocumentChange . Type . ADDED) {
                            productListArrayList.add(dc.document.toObject(ProductData::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }

}
