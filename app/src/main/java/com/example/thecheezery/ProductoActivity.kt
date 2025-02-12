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
    // Inicialización de la lista de bebidas calientes
    private var hotDrinks = ArrayList<Product>().apply {
        add(Product("Latte", R.drawable.latte, "Coffee drink made with espresso and steamed milk", 6.0))
        add(Product("Hot chocolate", R.drawable.hotchocolate, "Heated drink consisting of shaved chocolate, topped with marshmallows.", 5.0))
        add(Product("Espresso", R.drawable.espresso, "Full-flavored, concentrated form of coffee.", 4.0))
        add(Product("Chai Latte", R.drawable.chailatte, "Spiced tea concentrate with milk", 6.0))
        add(Product("Cappuccino", R.drawable.capuccino, "A cappuccino is an espresso-based coffee drink, prepared with steamed foam.", 7.0))
        add(Product("American coffee", R.drawable.americano, "Espresso with hot water", 2.0))
    }

    // Inicialización de la lista de postres
    private var sweets = ArrayList<Product>().apply {
        add(Product("Blueberry cake", R.drawable.blueberrycake, "Vanilla cake flavor, topped with cheese topping and blueberries.", 6.0))
        add(Product("Chocolate cupcake", R.drawable.chocolatecupcake, "Chocolate cupcakes topped with butter cream and cherries", 3.0))
        add(Product("Lemon tartalette", R.drawable.lemontartalette, "Pastry shell with a lemon flavored filling", 4.0))
        add(Product("Red Velvet cake", R.drawable.redvelvetcake, "Soft, moist, buttery cake topped with an easy cream cheese frosting.", 6.0))
        add(Product("Cherry cheesecake", R.drawable.strawberrycheesecake, "This cherry topped cheesecake is positively creamy and delicious and will be your new favorite dessert.", 7.0))
        add(Product("Tiramisu", R.drawable.tiramisu, "Coffee-flavored Italian dessert", 6.0))
    }

    // Inicialización de la lista de comidas saladas
    private var salties = ArrayList<Product>().apply {
        add(Product("Chicken crepes", R.drawable.chickencrepes, "Fine crepes stuffed with Alfredo chicken, spinach and mushrooms.", 6.0))
        add(Product("Club Sandwich", R.drawable.clubsandwich, "A delicious sandwich served with french fries.", 5.0))
        add(Product("Panini", R.drawable.hampanini, "Sandwich made with Italian bread served warmed by grilling.", 4.0))
        add(Product("Philly cheese steak", R.drawable.phillycheesesteak, "Smothered in grilled onions, green peppers, mushrooms, and Provolone.", 6.0))
        add(Product("Nachos", R.drawable.nachos, "Tortilla chips layered with beef and melted cheddar cheese. Served with fried beans, guacamole, pico de gallo, and sour topping.", 7.0))
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
        when (intent.getStringExtra("opcion")){
            "HOT DRINKS" -> {findViewById<ImageView>(R.id.titulo).setImageResource(R.drawable.hotdrinks);findViewById<ListView>(R.id.listviewProducto).adapter = AdapadorProductos(this, hotDrinks)}
            "SWEETS"     -> {findViewById<ImageView>(R.id.titulo).setImageResource(R.drawable.sweets);findViewById<ListView>(R.id.listviewProducto).adapter = AdapadorProductos(this, sweets)}
            "SALTIES"    -> {findViewById<ImageView>(R.id.titulo).setImageResource(R.drawable.salties);findViewById<ListView>(R.id.listviewProducto).adapter = AdapadorProductos(this, salties)}
            "COMBOS" -> {}
            "CUSTOM" -> {}
            else -> findViewById<ListView>(R.id.listviewProducto).adapter = AdapadorProductos(this, coldDrinks)
        }
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