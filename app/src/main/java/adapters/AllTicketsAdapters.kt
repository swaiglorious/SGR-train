package adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sgr_train.R
import models.Ticket


class AllTicketsAdapters(val ticket: ArrayList<Ticket>) : RecyclerView.Adapter<AllTicketsAdapters.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.cname)
        val txtFrom: TextView = view.findViewById(R.id.source)
        val txtTo: TextView = view.findViewById(R.id.destination)
        val txtTicketNumber: TextView = view.findViewById(R.id.tnumber)
        val delete: ClipData.Item = view.findViewById(R.id.action_delete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ticketrow, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = ticket[position]
        holder.txtTicketNumber.text = currentItem.tnumber
        holder.txtName.text = currentItem.name
        holder.txtFrom.text = currentItem.source
        holder.txtTo.text = currentItem.destination
    }

    override fun getItemCount(): Int {
       return ticket.size
    }
    fun deleteItem(index: Int){
        ticket.removeAt(index)
        notifyDataSetChanged()
    }




}


