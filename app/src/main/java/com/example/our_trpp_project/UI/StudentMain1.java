package com.example.our_trpp_project.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_trpp_project.Data.Subject;
import com.example.our_trpp_project.Data.Tutor;
import com.example.our_trpp_project.R;

import java.util.ArrayList;
import java.util.List;

public class StudentMain1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student_main1, container, false);

        // Инициализация RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycleView);

        // Создание списка предметов с репетиторами
        List<Subject> subjects = createSubjectList();

        // Создание и установка адаптера для RecyclerView
        SubjectAdapter adapter = new SubjectAdapter(subjects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    private List<Subject> createSubjectList() {
        List<Subject> subjects = new ArrayList<>();

        // Создание предметов
        Subject math = new Subject("Математика", createTutorList("Смирнова Светлана Георгиевна", "Васильев Аркадий Сергеевич"));
        Subject physics = new Subject("Физика", createTutorList("Лушков Алексей Иванович"));
        Subject rus = new Subject("Русский язык", createTutorList("Мосина Виктория Дмитриевна:)", "Иванова Ольга Викторовна", "Петрова Екатерина Павловна"));
        Subject english = new Subject("Английский язык", createTutorList("Абрамов Юрий Петрович"));
        Subject informatics = new Subject("Информатика", createTutorList("Мамаева Людмила Дмитриевна", "Пятлина Екатерина Валерьевна"));
        Subject chemistry = new Subject("Химия", createTutorList("Строгина Надежда Сергеевна"));
        // Добавление предметов в список
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
