package unicauca.edu.drogue_plus.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import unicauca.edu.drogue_plus.data.models.MedicineModel
import unicauca.edu.drogue_plus.databinding.ItemMedicineBinding


class MedicineAdapter (var list: List<MedicineModel>): RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    class ViewHolder(val view: ItemMedicineBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMedicineBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemMedicineTitle.text =item.title
        holder.view.itemMedicineState.text =item.state
        holder.view.itemMedicineIcon.setImageResource(item.icon.toInt())
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun changeDataSet(newList: List<MedicineModel>){
        this.list = newList
        notifyDataSetChanged()
    }


}
