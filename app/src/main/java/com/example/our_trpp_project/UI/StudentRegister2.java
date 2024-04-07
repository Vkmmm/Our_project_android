package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.our_trpp_project.Data.InformationStudentRepository;
import com.example.our_trpp_project.R;

public class StudentRegister2 extends Fragment {
    InformationStudentRepository informationStudentRepository;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationStudentRepository = new InformationStudentRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_register2, container, false);
        Button button1 = view.findViewById(R.id.button6);
        EditText editTextName = view.findViewById(R.id.editTextText);
        EditText editTextGrade = view.findViewById(R.id.editTextText2);
        EditText editTextCity = view.findViewById(R.id.editTextText3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = editTextName.getText().toString();
                String Grade = editTextGrade.getText().toString();
                String City = editTextCity.getText().toString();
                informationStudentRepository.setName(Name);
                informationStudentRepository.setGrade(Grade);
                informationStudentRepository.setCity(City);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Info2", informationStudentRepository);
                Navigation.findNavController(view).navigate(R.id.action_studentRegister1_to_studentRegister2, bundle);
            }
        });
        return view;
    }
}