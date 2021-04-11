package com.example.pixaflip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PdfRecyclerViewAdapter extends RecyclerView.Adapter<PdfRecyclerViewAdapter.PdfViewHolder> {

    private List<pdf> pdfList = new ArrayList<>();
    private Context context;

    public PdfRecyclerViewAdapter(List<pdf> pdfList) {
        this.pdfList = pdfList;
    }

    //setter
    public void setPdfList(List<pdf> pdfList) {
        this.pdfList = pdfList;
    }


    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.pdf_item, parent, false);
        PdfViewHolder holder = new PdfViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PdfViewHolder holder, int position) {
        if (pdfList.isEmpty()) {
            Toast.makeText(context, "No PDF To Show", Toast.LENGTH_SHORT).show();
        }
        final pdf currentObject = pdfList.get(position);
        holder.pdfName.setText(currentObject.getName());
        holder.pdfDescrioption.setText(currentObject.getDescription());

        if (currentObject.getIsfav()) {
            holder.addToFavBTN.setChecked(true);
        }

        holder.pdfItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PdfActivity.class);
                intent.putExtra("pdfURL", currentObject.getUrl());
                context.startActivity(intent);
            }
        });

        holder.addToFavBTN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int isfav = b ? 1 : 0;
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                boolean succ = databaseHelper.update(currentObject.getName(), isfav);
                if(succ && b){
                    Toast.makeText(context, "Added To Favourite ", Toast.LENGTH_SHORT).show();
                }
                else if(succ && !b) {
                    Toast.makeText(context, "Removed From Favourite ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Something Went Wrong ", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }


    //-------------------------------------------------------------------

    public static class PdfViewHolder extends RecyclerView.ViewHolder {

        private final TextView pdfName;
        private final TextView pdfDescrioption;
        private final RelativeLayout pdfItem;
        private final CheckBox addToFavBTN;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfName = itemView.findViewById(R.id.pdfNameTV);
            pdfDescrioption = itemView.findViewById(R.id.pdfDescriptionTV);
            pdfItem = itemView.findViewById(R.id.pdfItem);
            addToFavBTN = itemView.findViewById(R.id.addToFAvBTN);
        }
    }

}
