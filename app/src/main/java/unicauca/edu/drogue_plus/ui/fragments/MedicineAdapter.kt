package unicauca.edu.drogue_plus.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unicauca.edu.drogue_plus.databinding.Medicamento0Binding

class MedicineAdapter (var list: List<MedicineModel>):
    RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    class ViewHolder(val view: Medicamento0Binding): RecyclerView.ViewHolder(view.root)

    private var listener: OnMedicineClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(Medicamento0Binding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.medicamento0Tittle.text = item.name
        holder.view.medicamento0State.text = item.state.toString()
        //holder.view.medicamento0Icon.setImageResource(item.image.toInt())
        holder.view.root.setOnClickListener {
            listener?.onClick(item)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


}
