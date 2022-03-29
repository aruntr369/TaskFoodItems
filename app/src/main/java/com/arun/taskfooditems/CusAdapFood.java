package com.arun.taskfooditems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/*
public class CusAdapFood extends BaseAdapter implements Filterable {
    Context context;
    String food[];
    int foodImg[];
    LayoutInflater in;

    public CusAdapFood(Context application, String[] food, int[] foodImg) {
        context = application.getApplicationContext();
        this.food = food;
        this.foodImg = foodImg;
    }

    @Override
    public int getCount() {
        return food.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        in = LayoutInflater.from(context);
        view = in.inflate(R.layout.grid_cus_food, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        imageView.setImageResource(foodImg[i]);
        textView.setText(food[i]);

        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
 */

public class CusAdapFood extends BaseAdapter implements Filterable {

    private List<String> originalData = null;
    private List<String>filteredData = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();
    private  int lastpostion=-1;
    private Context mContext;
    
   // private  List<Integer>imgData=null;
   int foodImg[];

    public CusAdapFood(Context context, List<String> data, int[] foodImg) {
        this.filteredData = data ;
        this.originalData = data ;
        this.foodImg=foodImg;
        this.mContext=context;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_cus_food, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.tv);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv);


            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        Animation animation= AnimationUtils.loadAnimation(mContext,
                (position>lastpostion)? R.anim.slide_down: R.anim.slide_up);
        convertView.startAnimation(animation);
        lastpostion=position;

        // If weren't re-ordering this you could rely on what you set last time
        holder.text.setText(filteredData.get(position));
        holder.imageView.setImageResource(foodImg[position]);

        return convertView;
    }

    static class ViewHolder {
        TextView text;
        ImageView imageView;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = originalData;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }
}