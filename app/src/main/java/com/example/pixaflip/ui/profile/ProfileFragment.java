package com.example.pixaflip.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pixaflip.R;
import com.example.pixaflip.UserProfile;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;
    private TextView name, email, address, number, dateOfBirth, college, bloodGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        galleryViewModel =
//                ViewModelProviders.of(this).get(ProfileViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
        View root = inflater.inflate(R.layout.profile_fragment, container, false);
        UserProfile user = new UserProfile("Pratik Patil", "pratikap41@gmail.com", "h-1201, Marvel Ganga Fria, Pune", "9960141806", "22/11/2000", "SKNCOE",
                "O+");

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        address = root.findViewById(R.id.address);
        number = root.findViewById(R.id.number);
        dateOfBirth = root.findViewById(R.id.dateOfBirth);
        college = root.findViewById(R.id.college);
        bloodGroup = root.findViewById(R.id.bloodGroup);

        name.setText(user.getName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        number.setText(user.getNumber());
        dateOfBirth.setText(user.getDateOfBirth());
        college.setText(user.getCollege());
        bloodGroup.setText(user.getBloodGroup());


        return root;
    }
}
