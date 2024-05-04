package com.example.our_trpp_project.UI;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Student.Data.StudentEntity;


public class StudentEnter1 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_enter_1, container, false);
        Button button = view.findViewById(R.id.button4);
        EditText editTextNumber = view.findViewById(R.id.editTextText);
        EditText editTextPassword = view.findViewById(R.id.editTextText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_studentEnter1_to_studentMain1);
                }
            }
        });
        return view;
    }
}