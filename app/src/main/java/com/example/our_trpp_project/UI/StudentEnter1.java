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
import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Student.Data.StudentEntity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentEnter1 extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Инициализация Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
                String email = editTextNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });

        return view;
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Вход успешен, извлекаем данные пользователя из Firestore
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            db.collection("students").document(user.getUid()).get()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            DocumentSnapshot document = task1.getResult();
                                            if (document != null && document.exists()) {
                                                String name = document.getString("name");
                                                String city = document.getString("city");
                                                String grade = document.getString("grade");
                                                String imageUrl = document.getString("imageUri");

                                                StudentEntity studentEntity = new StudentEntity(email, name, city, grade, imageUrl);

                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("StudentInfo", studentEntity);

                                                // Переход к следующему фрагменту с передачей данных
                                                Navigation.findNavController(getView()).navigate(R.id.action_studentEnter1_to_studentMain1, bundle);
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "Ошибка при загрузке данных пользователя", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        // Если вход не удался, выводим сообщение об ошибке
                        Toast.makeText(getContext(), "Ошибка входа: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
