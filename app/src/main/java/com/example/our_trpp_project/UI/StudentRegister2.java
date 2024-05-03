package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.our_trpp_project.Student.Data.AppDatabaseStudent;
import com.example.our_trpp_project.Student.Data.StudentDAO;
import com.example.our_trpp_project.Student.Data.StudentEntity;
import com.example.our_trpp_project.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** The StudentRegister2 class contains input fields and a button. */
public class StudentRegister2 extends Fragment {
    /** Declaration of the repository. */
    StudentEntity studentEntity;
    private AppDatabaseStudent dbStudent;
    private StudentDAO studentDao;
    private ExecutorService executorService;
    /** Constructor of the class, creates a new InformationStudentRepository */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentEntity = new StudentEntity();
    }
    /**
     * onCreateView function. Initializes the activity after its creation.
     * Uses navigation to move between screens.
     * Converts input data and passes it upon button click.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_register2, container, false);
        dbStudent = AppDatabaseStudent.getInstance(requireContext());
        studentDao = dbStudent.studentDAO();
        executorService = Executors.newSingleThreadExecutor();

        Button button1 = view.findViewById(R.id.button6);
        EditText editTextName = view.findViewById(R.id.editTextText);
        EditText editTextGrade = view.findViewById(R.id.editTextText2);
        EditText editTextCity = view.findViewById(R.id.editTextText3);

        button1.setOnClickListener(new View.OnClickListener() {
            /** Handling button click */

            @Override
            public void onClick(View view) {
                String Name = editTextName.getText().toString();
                String Grade = editTextGrade.getText().toString();
                String City = editTextCity.getText().toString();
                studentEntity.setName(Name);
                studentEntity.setGrade(Grade);
                studentEntity.setCity(City);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        studentDao.insert(studentEntity);
                    }
                }).start();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Info2", studentEntity);
                Navigation.findNavController(view).navigate
                        (R.id.action_studentRegister2_to_studentMain1, bundle);
            }
        });
        return view;
    }
}