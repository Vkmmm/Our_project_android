package com.example.our_trpp_project.Repository;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private final FirebaseUser user = mAuth.getCurrentUser();

    public void saveUserToDatabase(String name, String grade, String city, Context context) {
        if (user != null) {
            String userId = user.getUid();
            Map<String, Object> student = new HashMap<>();
            student.put("name", name);
            student.put("email", user.getEmail());
            student.put("grade", grade);
            student.put("city", city);

            db.collection("students").document(userId).set(student)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Данные пользователя успешно сохранены", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Ошибка сохранения данных пользователя", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void registerUser(String email, String password, Context context) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Регистрация не удалась", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
