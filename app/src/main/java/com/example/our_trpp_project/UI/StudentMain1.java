package com.example.our_trpp_project.UI;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.our_trpp_project.Data.Subject;
import com.example.our_trpp_project.Data.Tutor;
import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Student.Data.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentMain1 extends Fragment implements SubjectAdapter.OnItemClickListener {

    private SubjectAdapter adapter;
    private StudentEntity studentEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student_main1, container, false);

        // Получаем переданную информацию о студенте из Bundle
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("StudentInfo")) {
            studentEntity = (StudentEntity) bundle.getSerializable("StudentInfo");
            if (studentEntity != null && studentEntity.getImageUri() != null) {
                ImageView ava_image = rootView.findViewById(R.id.imageView5);
                Glide.with(this).load(studentEntity.getImageUri()).into(ava_image);
                ava_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("StudentInfo", studentEntity);
                        Navigation.findNavController(v).navigate(R.id.action_studentMain1_to_studentCabinet, bundle);
                    }
                });
            }
        }

        // Инициализация RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycleView);
        List<Subject> subjects = createSubjectList();
        adapter = new SubjectAdapter(subjects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setOnItemClickListener(this); // Передайте фрагмент в качестве слушателя
    }

    @Override
    public void onItemClick(Tutor tutor) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("SelectedTutor", tutor);
        Navigation.findNavController(requireView()).navigate(R.id.action_studentMain1_to_tutorPage, bundle);
    }

    private List<Subject> createSubjectList() {
        List<Subject> subjects = new ArrayList<>();
        Subject math = new Subject("Математика", createTutorList("Смирнова Светлана Георгиевна", "Васильев Аркадий Сергеевич"));
        Subject physics = new Subject("Физика", createTutorList("Лушков Алексей Иванович"));
        Subject rus = new Subject("Русский язык", createTutorList("Мосина Виктория Дмитриевна", "Иванова Ольга Викторовна", "Петрова Екатерина Павловна"));
        Subject english = new Subject("Английский язык", createTutorList("Абрамов Юрий Петрович"));
        Subject informatics = new Subject("Информатика", createTutorList("Мамаева Людмила Дмитриевна", "Пятлина Екатерина Валерьевна"));
        Subject chemistry = new Subject("Химия", createTutorList("Строгина Надежда Сергеевна"));
        subjects.add(math);
        subjects.add(physics);
        subjects.add(rus);
        subjects.add(english);
        subjects.add(informatics);
        subjects.add(chemistry);
        return subjects;
    }

    private List<Tutor> createTutorList(String... tutorNames) {
        List<Tutor> tutors = new ArrayList<>();
        for (String name : tutorNames) {
            tutors.add(new Tutor(name));
        }
        return tutors;
    }
}
