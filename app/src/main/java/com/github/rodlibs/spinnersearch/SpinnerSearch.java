package com.github.rodlibs.spinnersearch;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rodd on 02/06/18.
 */


public class SpinnerSearch {

    private AdapterSpinnerSearch adapter;
    private AlertDialog alerta;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;
    private String titleDialog;
    private String titleButtonDialog;
    private boolean cancelableDialog;
    private int gravitySearch;
    private int visibleSearch;
    private float textSizeSearch;
    private String textHint;
    private int textColor;
    private Typeface typeface;
    private int dividerHeigthListView;




    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public SpinnerSearch(Context context, List<Item> listItem) {
        this.context = context;
        titleDialog = "Select an item";
        titleButtonDialog = "Close";
        cancelableDialog = true;
        gravitySearch = Gravity.LEFT;
        visibleSearch = View.VISIBLE;
        textSizeSearch = 15f;
        textHint = "Search";
        textColor = Color.BLACK;
        typeface = Typeface.DEFAULT;
        dividerHeigthListView = 1;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter = new AdapterSpinnerSearch(insertHeads(listItem),context);
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




    public void setTitleMessage(String title){
        titleDialog = title;
    }
    public void setTitleButtonMessage(String title){
        titleButtonDialog = title;
    }


    public void setCancelable(boolean flag){
        cancelableDialog = flag;
    }
    public void setGravitCampSearch(int gravity){
        gravitySearch = gravity;
    }
    public void setVisibleCampSearch(int visible){
        visibleSearch = visible;
    }
    public void setTextSizeSearch(float size){
        textSizeSearch = size;
    }
    public void setTextHintSearch(String hint){
        textHint = hint;
    }
    public void setColortextSearch(int color){
        textColor = color;
    }
    public void setTypefaceSearch(Typeface type){
        typeface = type;
    }
    public void setDividerHeigth(int heigth){
        dividerHeigthListView = heigth;
    }


    public void setSizeFontGroupList(float size){
        adapter.setSizeFontGroup(size);
    }
    public void setSizeFontItemList(float size){
        adapter.setSizeFontItem(size);
    }
    public void setColorFontGroupList(int color){
        adapter.setColorGroup(color);
    }
    public void setColorFontItemList(int color){
        adapter.setColorItem(color);
    }
    public void setTypefaceGroupList(Typeface type){
        adapter.setTypefaceGroup(type);
    }
    public void setTypefaceItemList(Typeface type){
        adapter.setTypefaceItem(type);
    }









    public void show(){
        View view = layoutInflater.inflate(R.layout.layout_dialog_spinner_search, null);
        EditText edt = view.findViewById(R.id.edtSearch);
        edt.setGravity(gravitySearch);
        edt.setVisibility(visibleSearch);
        edt.setTextSize(textSizeSearch);
        edt.setHint(textHint);
        edt.setTextColor(textColor);
        edt.setTypeface(typeface);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                adapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        ListView listView = view.findViewById(R.id.listView);
        listView.setDividerHeight(dividerHeigthListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = adapter.getItem(position);
                if (obj instanceof Item){
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, ((Item) obj));
                    }
                    alerta.dismiss();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(cancelableDialog);
        builder.setTitle(titleDialog);
        builder.setView(view);
        builder.setPositiveButton(titleButtonDialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                alerta.dismiss();
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}