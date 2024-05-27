package com.example.our_trpp_project.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Repository.Repository;
import com.example.our_trpp_project.Student.Data.StudentEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRegister1 extends Fragment {
    private SharedPreferences sharedPreferences;
    private ExecutorService executorService;
    private final Repository repository = new Repository();
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public StudentRegister1() {
        super(R.layout.fragment_student1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_register1, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("student_info", Context.MODE_PRIVATE);
        executorService = Executors.newSingleThreadExecutor();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Button button1 = view.findViewById(R.id.button6);
        EditText editTextNumber = view.findViewById(R.id.editTextText);
        EditText editTextPassword = view.findViewById(R.id.editTextText2);
        EditText editTextRepeatPassword = view.findViewById(R.id.editTextText3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                String repeatPassword = editTextRepeatPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repeatPassword)) {
                    Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else if (!checkPasswords(password, repeatPassword)) {
                    Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password);
                }
            }
        });

        return view;
    }

    private boolean checkPasswords(String password1, String password2) {
        return password1.equals(password2);
    }

    private void registerUser(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = mAuth.getCurrentUser().getUid();
                    StudentEntity student = new StudentEntity(email, userId, "city_placeholder", "grade_placeholder");
                    db.collection("students").document(userId).set(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("Info", student);
                                Navigation.findNavController(getView()).navigate(R.id.action_studentRegister1_to_studentRegister2, bundle);
                            } else {
                                Toast.makeText(getContext(), "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Ошибка регистрации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        executorService.shutdown();
    }
}
