package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.our_trpp_project.R;

/** The StartFragment class contains buttons for selection. */
public class StartFragment extends Fragment {

    /** Function to initialize the fragment */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * onCreateView function. Initializes the activity after its creation.
     * Uses navigation to move between screens.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button button = view.findViewById(R.id.button_stud);
        Button button1 = view.findViewById(R.id.button_rep);
        button.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_student1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_teacher1);
            }
        });
        return view;
    }
}