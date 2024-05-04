package com.example.our_trpp_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.our_trpp_project.Student.Data.StudentEntity;

import java.util.List;

public class StudentCabinet extends Fragment {
    private TextView textViewEditNumber;
    private TextView textViewEditPassword;
    private TextView textViewEditName;
    private TextView textViewEditGrade;
    private TextView textViewEditCity;
    private EditText editTextEditNumber;
    private EditText editTextEditPassword;
    private EditText editTextEditName;
    private EditText editTextEditGrade;
    private EditText editTextEditCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_cabinet, container, false);

        // Инициализация текстовых полей
        textViewEditNumber = view.findViewById(R.id.textView_edit_number);
        textViewEditPassword = view.findViewById(R.id.textView_edit_password);
        textViewEditName = view.findViewById(R.id.textView_edit_Name);
        textViewEditGrade = view.findViewById(R.id.textView_edit_grade);
        textViewEditCity = view.findViewById(R.id.textView_edit_city);

        editTextEditNumber = view.findViewById(R.id.editText_edit_number);
        editTextEditPassword = view.findViewById(R.id.editText_edit_password);
        editTextEditName = view.findViewById(R.id.editText_edit_Name);
        editTextEditGrade = view.findViewById(R.id.editText_edit_grade);
        editTextEditCity = view.findViewById(R.id.editText_edit_city);

        Button button_change = view.findViewById(R.id.button_toggle_visibility);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("StudentInfo")) {
            StudentEntity studentEntity = (StudentEntity) bundle.getSerializable("StudentInfo");
            textViewEditNumber.setText(studentEntity.getNumber());
            textViewEditPassword.setText(studentEntity.getPassword());
            textViewEditName.setText(studentEntity.getName());
            textViewEditGrade.setText(studentEntity.getGrade());
            textViewEditCity.setText(studentEntity.getCity());
        }
        // fillTextFields();
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();}
        });
        return view;
    }
    private void fillTextFields() {
        // Получение списка всех студентов из базы данных
        StudentEntity student = new StudentEntity();
            textViewEditNumber.setText(student.getNumber());
            textViewEditPassword.setText(student.getPassword());
            textViewEditName.setText(student.getName());
            textViewEditGrade.setText(student.getGrade());
            textViewEditCity.setText(student.getCity());
    }

    private void toggleVisibility() {
        textViewEditNumber.setVisibility(View.GONE);
        editTextEditNumber.setVisibility(View.VISIBLE);
        editTextEditNumber.setText(textViewEditNumber.getText());

        textViewEditPassword.setVisibility(View.GONE);
        editTextEditPassword.setVisibility(View.VISIBLE);
        editTextEditPassword.setText(textViewEditPassword.getText());

        textViewEditName.setVisibility(View.GONE);
        editTextEditName.setVisibility(View.VISIBLE);
        editTextEditName.setText(textViewEditName.getText());

        textViewEditGrade.setVisibility(View.GONE);
        editTextEditGrade.setVisibility(View.VISIBLE);
        editTextEditGrade.setText(textViewEditGrade.getText());

        textViewEditCity.setVisibility(View.GONE);
        editTextEditCity.setVisibility(View.VISIBLE);
        editTextEditCity.setText(textViewEditCity.getText());
    }
}