package com.example.pixaflip.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pixaflip.DatabaseHelper;
import com.example.pixaflip.DisplayPdfActivity;
import com.example.pixaflip.MainActivity;
import com.example.pixaflip.R;
import com.example.pixaflip.VideoActivity;
import com.example.pixaflip.pdf;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RelativeLayout playVideo = root.findViewById(R.id.playVideo);
        RelativeLayout showPdf = root.findViewById(R.id.showPdf);


        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(container.getContext(), VideoActivity.class));

            }
        });

        showPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.context, DisplayPdfActivity.class);
                startActivity(intent);
            }
        });

//        DataBase initial data filling
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        if (databaseHelper.getAll().isEmpty()) {
            databaseHelper.add(new pdf("Animals", "https://agendaweb.org/pdf/animals-1.pdf", "PDF about animals"));
            databaseHelper.add(new pdf("Notice", "http://exam.unipune.ac.in/Docs/Timetables/April14/DLL_Lw.pdf", "covid notice"));
            databaseHelper.add(new pdf("TimeTable", "http://exam.unipune.ac.in/Docs/Timetables/April14/Cert_course_Forensic_2014.pdf", "Contains college TimeTable for next week"));
            databaseHelper.add(new pdf("Countries", "https://www.hrwstf.org/mobile_library/r2etoolkit/country_project.pdf", "PDF about countries"));
            databaseHelper.add(new pdf("Food", "https://www.healthpromotion.ie/hp-files/docs/HPM00796.pdf", "PDF about food"));
        }

        return root;
    }

}
