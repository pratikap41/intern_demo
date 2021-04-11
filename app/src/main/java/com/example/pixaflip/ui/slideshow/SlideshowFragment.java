package com.example.pixaflip.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.pixaflip.DatabaseHelper;
import com.example.pixaflip.PdfRecyclerViewAdapter;
import com.example.pixaflip.R;
import com.example.pixaflip.pdf;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel =
//                ViewModelProviders.of(this).get(SlideshowViewModel.class);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        rv = root.findViewById(R.id.recyclerViewfav);
        final SwipeRefreshLayout sr = root.findViewById(R.id.swipeToRefresh);

        sr.setRefreshing(true);
        SwipeRefreshLayout.OnRefreshListener obj = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<pdf> pdfList = new ArrayList<>();
                DatabaseHelper databaseHelper = new DatabaseHelper(root.getContext());
                List<pdf> allList = databaseHelper.getAll();
                for (int i = 0; i < allList.size(); i++) {
                    if (allList.get(i).getIsfav()) {
                        pdfList.add(allList.get(i));
                    }
                }
                sr.setRefreshing(false);

                rv.setAdapter(new PdfRecyclerViewAdapter(pdfList));
                rv.setLayoutManager(new LinearLayoutManager(root.getContext()));

            }
        };
        obj.onRefresh();
        sr.setOnRefreshListener(obj);


        sr.setRefreshing(false);
        return root;
    }
}
