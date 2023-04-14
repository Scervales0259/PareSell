package ph.edu.dlsu.mobdeve.cervales.santiago.paresell

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.databinding.FavoritesItemRowBinding
import ph.edu.dlsu.mobdeve.cervales.santiago.paresell.model.Product

class ItemAdapter(val items : MutableList<Product>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private lateinit var mListener : OnItemClickListener


    interface OnItemClickListener {
        fun onItemClick(position: Int, boolean: Boolean)
    }

    fun setOnItemClickListener(listener : OnItemClickListener)
    {

        mListener = listener

    }
//, listener : OnItemClickListener
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val productName : TextView = itemView.findViewById(R.id.productName)
        val price : TextView = itemView.findViewById(R.id.price)
        val accountIcon : ImageView = itemView.findViewById(R.id.accountIcon)
        val sellerName : TextView = itemView.findViewById(R.id.sellerName)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)

        fun bindData(data: Product){
            itemView.findViewById<TextView>(R.id.productName).text = data.name
            itemView.findViewById<TextView>(R.id.price).text = data.price.toString() + " PHP"
            itemView.findViewById<TextView>(R.id.sellerName).text = data.user.firstName + " " + data.user.lastName
            itemView.findViewById<ImageView>(R.id.accountIcon).setImageResource(data.user.imageUrl)
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(data.imageUrl)
        }

//        init {
//            itemView.findViewById<TextView>(R.id.productName).setOnClickListener {
//
//                listener.onItemClick(adapterPosition, true)
//
//            }
//            itemView.findViewById<TextView>(R.id.sellerName).setOnClickListener{
//
//                listener.onItemClick(adapterPosition, false)
//
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.favorites_item_row,parent,false)
//        , mListener
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindData(item)
        holder.itemView.setOnClickListener{
            val bundle = Bundle()

            bundle.putString("name", item.name)
            bundle.putString("description", item.description)
            bundle.putString("category", item.category)
            bundle.putInt("image", item.imageUrl)
            bundle.putInt("icon", item.user.imageUrl)
            bundle.putString("sellerName", item.user.firstName + " " + item.user.lastName)
            bundle.putString("sellerLoc", item.user.address + ", " + item.user.city)

            var intent = Intent(holder.itemView.context, ProductActivity::class.java)

            intent.putExtras(bundle)
            startActivity(holder.itemView.context, intent, null)
        }
//        holder.productName.text = item.name
//        holder.price.text = item.price.toString() + " PHP"
//        holder.sellerName.text = item.user.firstName + " " + item.user.lastName
//        holder.accountIcon.setImageResource(item.user.imageUrl)
//        holder.imageView.setImageResource(item.imageUrl)
    }

    override fun getItemCount() : Int = items.size

}