package com.example.our_trpp_project.Student.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentViewModel extends ViewModel {
    private final MutableLiveData<StudentEntity> studentLiveData = new MutableLiveData<>();

    public void setStudent(StudentEntity student) {
        studentLiveData.setValue(student);
    }

    public LiveData<StudentEntity> getStudentLiveData() {
        return studentLiveData;
    }
}
