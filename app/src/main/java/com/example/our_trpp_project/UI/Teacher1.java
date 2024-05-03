package com.example.our_trpp_project.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.our_trpp_project.R;

public class Teacher1 extends Fragment {
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
        View view = inflater.inflate(R.layout.teacher_1, container, false);
        Button button = view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate
                        (R.id.action_teacher1_to_teacherRegister1);
            }
        });
        return view;
    }
}
