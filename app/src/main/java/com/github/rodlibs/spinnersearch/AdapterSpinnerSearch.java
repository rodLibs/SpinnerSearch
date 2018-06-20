package com.github.rodlibs.spinnersearch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodd on 02/06/18.
 */


public class AdapterSpinnerSearch extends BaseAdapter implements Filterable {

    private List<Object> listOrigi;
    private List<Object> listFiltered;
    private Context context;
    private LayoutInflater inflater;
    private float sizeFontGroup;
    private float sizeFontItem;
    private int colorGroup;
    private int colorItem;
    private Typeface typefaceGroup;
    private Typeface typefaceItem;
    private ItemFilter mFilter = new ItemFilter();


    public AdapterSpinnerSearch(List<Object> listOrigi,Context context) {
        this.listOrigi = listOrigi;
        this.listFiltered = listOrigi;
        this.context = context;
        this.sizeFontGroup = 15f;
        this.sizeFontItem = 13f;
        colorGroup = Color.BLACK;
        colorItem = Color.GRAY;
        typefaceGroup = Typeface.DEFAULT_BOLD;
        typefaceItem = Typeface.DEFAULT;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (listFiltered != null) {
            return listFiltered.size();
        } else {
            return 0;
        }
    }


    public void setSizeFontGroup(float size){
        sizeFontGroup = size;
    }
    public void setSizeFontItem(float size){
        sizeFontItem = size;
    }
    public void setColorGroup(int color){
        colorGroup = color;
    }
    public void setColorItem(int color){
        colorItem = color;
    }
    public void setTypefaceGroup(Typeface type){
        typefaceGroup = type;
    }
    public void setTypefaceItem(Typeface type){
        typefaceItem = type;
    }




    @Override
    public Object getItem(int position) {
        return listFiltered.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Object obj = listFiltered.get(position);
        view = inflater.inflate(R.layout.adapter_spinner_search, null);
        TextView txtItem = view.findViewById(R.id.txtItem);

        if (obj instanceof String){
            txtItem.setTypeface(typefaceGroup);
            txtItem.setTextSize(sizeFontGroup);
            txtItem.setTextColor(colorGroup);
            txtItem.setText((String)obj);
        } else if (obj instanceof Item){
            txtItem.setText("    "+((Item) obj).getSubitem());
            txtItem.setTypeface(typefaceItem);
            txtItem.setTextSize(sizeFontItem);
            txtItem.setTextColor(colorItem);
        }
        return view;
    }





    @Override
    public ItemFilter getFilter() {
        return mFilter;
    }

    public class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<Object> list = listOrigi;

            int count = list.size();
            List<Object> nlistt = new ArrayList<Object>(count);
            List<Item> eventos = new ArrayList<Item>();

            Object n;

            for (int i = 0; i < count; i++) {
                Object obj = list.get(i);
                n = obj;

                if (obj instanceof Item){
                    if ( ((Item) n).getSubitem().toLowerCase().contains(filterString) || ((Item) n).getGroup().toLowerCase().contains(filterString)) {
                        eventos.add( ((Item) n) );
                    }
                }
            }
            nlistt = insertHeads(eventos);
            results.values = nlistt;
            results.count = nlistt.size();
            return results;
        }




        private List<Object> insertHeads(List<Item> listItem){
            ArrayList<Object> result = new ArrayList();

            Item it1 = listItem.get(0);
            result.add(0,it1.getGroup());
            result.add(it1);
            String value = it1.getGroup();

            for (int i = 1; i < listItem.size(); i++){
                Item it = listItem.get(i);

                if (it.getGroup().equals(value)){
                    result.add(it);
                }
                else {
                    result.add(it.getGroup());
                    result.add(it);
                    value = it.getGroup();
                }
            }
            return result;
        }




        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listFiltered = (ArrayList<Object>) results.values;
            notifyDataSetChanged();
        }
    }
}