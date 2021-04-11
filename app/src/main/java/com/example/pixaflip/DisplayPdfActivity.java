package com.example.pixaflip;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class DisplayPdfActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PdfRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pdf);


        final SwipeRefreshLayout sr = findViewById(R.id.swipeToRefresh);

        SwipeRefreshLayout.OnRefreshListener obj = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                DatabaseHelper databaseHelper = new DatabaseHelper(DisplayPdfActivity.this);
                List<pdf> pdfList = databaseHelper.getAll();
                adapter = new PdfRecyclerViewAdapter(pdfList);
                layoutManager = new LinearLayoutManager(DisplayPdfActivity.this);

                //create recycler view in this activity and use it to display pdf files.
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                sr.setRefreshing(false);

            }
        };

        obj.onRefresh();
        sr.setOnRefreshListener(obj);


    }
}
