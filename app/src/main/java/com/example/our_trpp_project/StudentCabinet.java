package com.example.our_trpp_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private StudentEntity studentEntity;
    private static final int PICK_IMAGE_REQUEST = 1;

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

        // Инициализация кнопок
        Button button_change = view.findViewById(R.id.button_toggle_visibility);
        Button button_save = view.findViewById(R.id.button_save);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("StudentInfo")) {
            studentEntity = (StudentEntity) bundle.getSerializable("StudentInfo");
            textViewEditNumber.setText(studentEntity.getNumber());
            textViewEditPassword.setText(studentEntity.getPassword());
            textViewEditName.setText(studentEntity.getName());
            textViewEditGrade.setText(studentEntity.getGrade());
            textViewEditCity.setText(studentEntity.getCity());
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Получаем текст из EditText
                String number = editTextEditNumber.getText().toString();
                String password = editTextEditPassword.getText().toString();
                String name = editTextEditName.getText().toString();
                String grade = editTextEditGrade.getText().toString();
                String city = editTextEditCity.getText().toString();

                // Обновляем данные объекта StudentEntity
                studentEntity.setNumber(number);
                studentEntity.setPassword(password);
                studentEntity.setName(name);
                studentEntity.setGrade(grade);
                studentEntity.setCity(city);
                hideEditTextAndShowTextView();
            }
        });
        ImageView imageView = view.findViewById(R.id.imageView5);

        // Установка обработчика нажатия на ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов метода для выбора изображения из галереи
                pickImageFromGallery();
            }

        });

        // Настройка обработчика для кнопки "Изменить видимость"
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleVisibility();
            }
        });

        return view;
    }

    // Метод для переключения видимости полей
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
    private void hideEditTextAndShowTextView() {
        editTextEditNumber.setVisibility(View.GONE);
        textViewEditNumber.setVisibility(View.VISIBLE);
        textViewEditNumber.setText(editTextEditNumber.getText());

        editTextEditPassword.setVisibility(View.GONE);
        textViewEditPassword.setVisibility(View.VISIBLE);
        textViewEditPassword.setText(editTextEditPassword.getText());

        editTextEditName.setVisibility(View.GONE);
        textViewEditName.setVisibility(View.VISIBLE);
        textViewEditName.setText(editTextEditName.getText());

        editTextEditGrade.setVisibility(View.GONE);
        textViewEditGrade.setVisibility(View.VISIBLE);
        textViewEditGrade.setText(editTextEditGrade.getText());

        editTextEditCity.setVisibility(View.GONE);
        textViewEditCity.setVisibility(View.VISIBLE);
        textViewEditCity.setText(editTextEditCity.getText());
    }

    // Метод для выбора изображения из галереи
    private void pickImageFromGallery() {
        // Создание намерения для выбора изображения из галереи
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Метод обработки результата выбора изображения из галереи
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Получение выбранного изображения из данных результата
            Uri imageUri = data.getData();
            // Установка выбранного изображения в ImageView
            ImageView imageView = getView().findViewById(R.id.imageView5);
            imageView.setImageURI(imageUri);
        }
    }
}
