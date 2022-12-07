package unicauca.edu.drogue_plus.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unicauca.edu.drogue_plus.databinding.Medicamento0Binding
import java.util.Collections.list


class ServiceAdapter (var list: List<ServiceModel>):RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    class ViewHolder (val view: Medicamento0Binding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(Medicamento0Binding.inflate(inflater, parent, attachToParent: false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list(position)
        holder.view.itemMedicineTittle.text = item.tittle
        holder.view.itemMedicineDescription.text = item.description
        holder.view.itemMedicineIcon.setImageResource(item.icon.toInt())
    }

    override fun getItemCount(): Int {
        return list.size
    }
}