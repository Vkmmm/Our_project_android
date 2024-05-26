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

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Repository.Repository;
import com.example.our_trpp_project.Student.Data.StudentEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentRegister1 extends Fragment {
    private SharedPreferences sharedPreferences;
    private ExecutorService executorService;
    private final Repository repository = new Repository();


    public StudentRegister1() {
        super(R.layout.fragment_student1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_register1, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("student_info", Context.MODE_PRIVATE);
        executorService = Executors.newSingleThreadExecutor();


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
                    repository.registerUser(email,password, getContext());
                    StudentEntity student = new StudentEntity();
                    student.setNumber(email);
                    student.setPassword(password);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Info", student);
                    Navigation.findNavController(getView()).navigate(R.id.action_studentRegister1_to_studentRegister2, bundle);
                }
            }
        });

        return view;
    }



    private boolean checkPasswords(String password1, String password2) {
        return password1.equals(password2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        executorService.shutdown();
    }
}