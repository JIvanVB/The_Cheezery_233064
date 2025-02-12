package com.example.thecheezery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductoActivity : AppCompatActivity() {

    private var coldDrinks = ArrayList<Product>().apply {
        add(Product("Caramel Frap", R.drawable.caramelfrap, "Caramel syrup meets coffee, milk and ice and whipped cream and buttery caramel sauce layer the love on top.", 5.00))
        add(Product("Chocolate Frap", R.drawable.chocolatefrap, "Rich mocha-flavored sauce meets up with chocolaty chips, milk and ice for a blender bash.", 6.00))
        add(Product("Cold Brew", R.drawable.coldbrew, "Created by steeping medium-to-coarse ground coffee in room temperature water for 12 hours or longer.", 3.00))
        add(Product("Matcha Latte", R.drawable.matcha, "Leafy taste of matcha green tea powder with creamy milk and a little sugar for a flavor balance that will leave you feeling ready and raring to go.", 4.00))
        add(Product("Oreo Milkshake", R.drawable.oreomilkshake, "Chocolate ice cream, and oreo cookies. Topped with whipped cream with cocoa and chocolate syrup.", 7.00))
        add(Product("Peanut Milkshake", R.drawable.peanutmilkshake, "Vanilla ice cream, mixed with peanut butter and chocolate.", 7.00))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ListView>(R.id.listviewProducto).adapter = AdapadorProductos(this, coldDrinks)

    }

    private class AdapadorProductos(context: Context,productos: ArrayList<Product>):BaseAdapter(){
        var productos=ArrayList<Product>()
        var contexto : Context? = null

        init{
            this.productos=productos
            this.contexto=context
        }

        override fun getCount(): Int = productos.size
        override fun getItem(position: Int): Any = productos[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val holder: ViewHolder
            val vista: View

            if (convertView == null) {
                val inflador = LayoutInflater.from(contexto)
                vista = inflador.inflate(R.layout.producto_view, parent, false)
                holder = ViewHolder(vista)
                vista.tag = holder
            } else {
                vista = convertView
                holder = vista.tag as ViewHolder
            }

            val prod = productos[position]
            holder.imagen.setImageResource(prod.img)
            holder.nombre.text = prod.nomb
            holder.desc.text = prod.desc
            holder.precio.text = "$${prod.precio}"

            return vista
        }

        private class ViewHolder(vista: View) {
            val imagen: ImageView = vista.findViewById(R.id.imagen_Producto)
            val nombre: TextView = vista.findViewById(R.id.nombre_Producto)
            val desc: TextView = vista.findViewById(R.id.desc_Producto)
            val precio: TextView = vista.findViewById(R.id.precio_Producto)
        }
    }
}