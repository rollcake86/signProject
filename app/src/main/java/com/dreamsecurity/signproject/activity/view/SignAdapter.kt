package com.dreamsecurity.signproject.activity.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.Logs

class SignAdapter(private val myDataset: ArrayList<SignItem> ,val listener :  onClickListener ) :
        RecyclerView.Adapter<SignAdapter.MyViewHolder>() {

    interface onClickListener {
        fun click(id : String)
    }

    val TAG = SignAdapter::class.java.simpleName

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SignAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.signlist, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.findViewById<TextView>(R.id.title).text = myDataset[position].title
        holder.view.findViewById<TextView>(R.id.content).text = myDataset[position].content
        holder.view.findViewById<TextView>(R.id.signCount).text = myDataset[position].signCount

        holder.view.setOnClickListener {
            listener.click(myDataset[position].signHash)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}