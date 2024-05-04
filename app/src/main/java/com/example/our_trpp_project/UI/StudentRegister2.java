package com.example.our_trpp_project.UI;

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

import com.example.our_trpp_project.Student.Data.StudentEntity;
import com.example.our_trpp_project.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** The StudentRegister2 class contains input fields and a button. */
public class StudentRegister2 extends Fragment {
    private StudentEntity studentEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentEntity = new StudentEntity();
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

                // Проверяем, что поля не пустые
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Grade) || TextUtils.isEmpty(City)) {
                    // Если какое-то из полей пустое, выводим сообщение об ошибке
                    Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();

                    // Проверяем наличие информации о студенте в аргументах
                    if (getArguments() != null && getArguments().containsKey("Info")) {
                        // Получаем информацию о студенте из аргументов
                        StudentEntity studentEntity = (StudentEntity) getArguments().getSerializable("Info");
                        // Устанавливаем новые данные
                        studentEntity.setName(Name);
                        studentEntity.setGrade(Grade);
                        studentEntity.setCity(City);
                        // Помещаем обновленный объект студента в Bundle
                        bundle.putSerializable("StudentInfo", studentEntity);
                    }

                    // Передаем обновленные данные в следующий фрагмент
                    Navigation.findNavController(view).navigate(R.id.action_studentRegister2_to_studentMain1, bundle);
                }
            }
        });


        return view;
    }
}
