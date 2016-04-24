package com.creasylai.yt_app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.creasylai.yt_app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by laicreasy on 16/4/24.
 */
public class ImageAdapterForRecyclerView extends RecyclerView.Adapter<ImageAdapterForRecyclerView.ViewHolder> {
	private String[] mDataset;
	private Context context;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public ImageView imageView;
		public ViewHolder(View v) {
			super(v);
			imageView = (ImageView)v;
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public ImageAdapterForRecyclerView(String[] myDataset, Context context) {
		this.mDataset = myDataset;
		this.context = context;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public ImageAdapterForRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
	                                               int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_layout_imageview, parent, false);
		// set the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		Picasso.with(context).load(mDataset[position]).into(holder.imageView);

	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.length;
	}
}
