package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.our_trpp_project.Data.AppDatabaseStudent;
import com.example.our_trpp_project.Data.StudentDAO;
import com.example.our_trpp_project.Data.StudentEntity;
import com.example.our_trpp_project.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** The StudentRegister1 class contains input fields and a button. */
public class StudentRegister1 extends Fragment {
    /** Declaration of the repository. */
    StudentEntity informationStudentRepository;
    private AppDatabaseStudent dbStudent;
    private StudentDAO studentDAO;
    private ExecutorService executorService;
    /** Constructor of the class, creates a new InformationStudentRepository */
    public StudentRegister1() {
        super(R.layout.fragment_student1);
        informationStudentRepository = new StudentEntity();
    }
    /**
     * onCreateView function. Initializes the activity after its creation.
     * Uses navigation to move between screens.
     * Converts input data and passes it upon button click.
     * Checks the correctness of entered passwords.
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_register1, container, false);

        dbStudent = AppDatabaseStudent.getInstance(requireContext());
        studentDAO = dbStudent.studentDAO();
        executorService = Executors.newSingleThreadExecutor();

        Button button1 = view.findViewById(R.id.button6);
        EditText editTextNumber = view.findViewById(R.id.editTextText);
        EditText editTextPassword = view.findViewById(R.id.editTextText2);
        EditText editTextRepeatPassword = view.findViewById(R.id.editTextText3);

        button1.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */
            @Override
            public void onClick(View view) {
                String Number = editTextNumber.getText().toString();
                String Password = editTextPassword.getText().toString();
                String RepeatPassword = editTextRepeatPassword.getText().toString();
                if (!checkPasswords(Password, RepeatPassword)) {
                    Toast.makeText(getContext(),
                            "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                } else {
                    StudentEntity student = new StudentEntity();
                    student.setNumber(Number);
                    student.setPassword(Password);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            studentDAO.insert(student);
                        }
                    });
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Info", student);
                    Navigation.findNavController(view).navigate
                            (R.id.action_studentRegister1_to_studentRegister2, bundle);
                }
            }
        });
        return view;
    }
    /** Function for checking passwords. */

    private boolean checkPasswords(String password1, String password2) {
        return password1.equals(password2);
    }
    }