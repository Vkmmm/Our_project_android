package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.our_trpp_project.R;

/** The Student1 class contains two buttons for selection. */
public class Student1 extends Fragment {
    /** Function to initialize the fragment */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * onCreateView function. Initializes the activity after its creation.
     * Uses navigation to move between screens.
     * Contains choice between "register" and "I already have an account" buttons.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student1, container, false);
        Button button = view.findViewById(R.id.button3);
        Button button2 = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate
                        (R.id.action_student1_to_studentRegister1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate
                        (R.id.action_student1_to_studentEnter1);
            }
        });

        return view;
    }
}