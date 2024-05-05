package com.example.our_trpp_project.UI;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_trpp_project.Data.Subject;
import com.example.our_trpp_project.Data.Tutor;
import com.example.our_trpp_project.R;
import com.example.our_trpp_project.Student.Data.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentMain1 extends Fragment implements SubjectAdapter.OnItemClickListener{

    private SubjectAdapter adapter;
    private ImageView ava_image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student_main1, container, false);

        // Получаем переданную информацию о студенте из Bundle


        // Инициализация RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recycleView);

        // Создание списка предметов с репетиторами
        List<Subject> subjects = createSubjectList();

        // Создание и установка адаптера для RecyclerView
        adapter = new SubjectAdapter(subjects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ImageView ava_image = rootView.findViewById(R.id.imageView5);
        //   ava_image.setImageResource(R.drawable.ava2);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("ImageUri")) {
            // Если в Bundle содержится URI изображения, устанавливаем его в ImageView
            String imageUriString = bundle.getString("ImageUri");
            if (imageUriString != null) {
                Uri imageUri = Uri.parse(imageUriString);
                ava_image.setImageURI(imageUri);
            }
        }
        ava_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                if (bundle != null && bundle.containsKey("StudentInfo")) {
                    StudentEntity studentEntity = (StudentEntity) bundle.getSerializable("StudentInfo");
                bundle.putSerializable("StudentInfo", studentEntity); // studentEntity - объект класса StudentEntity с заполненными данными
                Navigation.findNavController(rootView).navigate(R.id.action_studentMain1_to_studentCabinet, bundle);}
            }
        });


        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ваш существующий код

        adapter.setOnItemClickListener(this); // Передайте фрагмент в качестве слушателя
    }

    // Метод обработки нажатия на репетитора
    @Override
    public void onItemClick(Tutor tutor) {
        // Здесь можно передать информацию о репетиторе на следующий экран и открыть его
        Bundle bundle = new Bundle();
        bundle.putSerializable("SelectedTutor", tutor);
        Navigation.findNavController(requireView()).navigate(R.id.action_studentMain1_to_tutorPage, bundle);
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
