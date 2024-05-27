package com.example.our_trpp_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.our_trpp_project.Student.Data.StudentEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StudentCabinet extends Fragment {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private StorageReference storageRef;
    private TextView textViewEditEmail;
    private TextView textViewEditName;
    private TextView textViewEditGrade;
    private TextView textViewEditCity;
    private EditText editTextEditEmail;
    private EditText editTextEditName;
    private EditText editTextEditGrade;
    private EditText editTextEditCity;
    private StudentEntity studentEntity;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_cabinet, container, false);
        // Инициализация текстовых полей
        textViewEditEmail = view.findViewById(R.id.textView_edit_email);
        textViewEditName = view.findViewById(R.id.textView_edit_Name);
        textViewEditGrade = view.findViewById(R.id.textView_edit_grade);
        textViewEditCity = view.findViewById(R.id.textView_edit_city);

        editTextEditEmail = view.findViewById(R.id.editText_edit_email);
        editTextEditName = view.findViewById(R.id.editText_edit_Name);
        editTextEditGrade = view.findViewById(R.id.editText_edit_grade);
        editTextEditCity = view.findViewById(R.id.editText_edit_city);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("students").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Данные пользователя успешно получены
                            String email = document.getString("email");
                            String name = document.getString("name");
                            String city = document.getString("city");
                            String grade = document.getString("grade");
                            String imageUrl = document.getString("imageUri");
                            // Инициализация объекта StudentEntity
                            studentEntity = new StudentEntity(email, name, city, grade, imageUrl);
                            textViewEditEmail.setText(email);
                            textViewEditName.setText(name);
                            textViewEditGrade.setText(grade);
                            textViewEditCity.setText(city);
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                                ImageView imageView = view.findViewById(R.id.imageView5);
                                // Использование Glide для загрузки изображения
                                Glide.with(StudentCabinet.this).load(imageUrl).into(imageView);
                            }
                        }
                    }
                });

        // Инициализация кнопок
        Button button_change = view.findViewById(R.id.button_toggle_visibility);
        Button button_save = view.findViewById(R.id.button_save);
        Button button_back = view.findViewById(R.id.button_Back);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Проверяем, что studentEntity инициализирован
                if (studentEntity == null) {
                    studentEntity = new StudentEntity();
                }
                // Получаем текст из EditText
                String email = editTextEditEmail.getText().toString();
                String name = editTextEditName.getText().toString();
                String grade = editTextEditGrade.getText().toString();
                String city = editTextEditCity.getText().toString();

                // Обновляем данные объекта StudentEntity
                studentEntity.setEmail(email);
                studentEntity.setName(name);
                studentEntity.setGrade(grade);
                studentEntity.setCity(city);
                if (selectedImageUri != null) {
                    uploadImageToFirebase(selectedImageUri);
                } else {
                    saveStudentData();
                }

                // Проверяем, видимы ли сейчас EditText
                if (editTextEditEmail.getVisibility() == View.VISIBLE) {
                    // Если EditText видим, скрываем его и показываем TextView
                    hideEditTextAndShowTextView();
                }
            }
        });

        ImageView imageView = view.findViewById(R.id.imageView5);
        imageView.setImageResource(R.drawable.ava2);
        // Установка обработчика нажатия на ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызов метода для выбора изображения из галереи
                pickImageFromGallery();
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание нового Bundle
                Bundle bundle = new Bundle();
                // Добавляем все поля студента в bundle
                bundle.putSerializable("StudentInfo", studentEntity);

                // Переход на предыдущий фрагмент (StudentMain1) с передачей Bundle
                Navigation.findNavController(view).navigate(R.id.action_studentCabinet_to_studentMain1, bundle);
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

    private void uploadImageToFirebase(Uri imageUri) {
        String userId = mAuth.getCurrentUser().getUid();
        StorageReference imageRef = storageRef.child("images/" + userId + "/profile.jpg");

        imageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d("ImageUpload", "Image URL: " + uri.toString());
                                studentEntity.setImageUri(uri.toString());
                                saveStudentData();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Ошибка получения URL изображения: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Ошибка загрузки изображения: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void saveStudentData() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("students").document(userId).set(studentEntity)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Метод для переключения видимости полей
    private void toggleVisibility() {
        textViewEditEmail.setVisibility(View.GONE);
        editTextEditEmail.setVisibility(View.VISIBLE);
        editTextEditEmail.setText(textViewEditEmail.getText());

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
        editTextEditEmail.setVisibility(View.GONE);
        textViewEditEmail.setVisibility(View.VISIBLE);
        textViewEditEmail.setText(editTextEditEmail.getText());

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
            selectedImageUri = data.getData();
            // Установка выбранного изображения в ImageView
            ImageView imageView = getView().findViewById(R.id.imageView5);
            imageView.setImageURI(selectedImageUri);
        }
    }

    // Метод для получения URI выбранного изображения из результата выбора из галереи
    private Uri getImageUri() {
        if (getContext() != null && getActivity() != null) {
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.getData() != null) {
                return intent.getData();
            }
        }
        return null;
    }
}


