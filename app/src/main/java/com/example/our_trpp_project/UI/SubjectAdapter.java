package com.example.our_trpp_project.UI;
import com.example.our_trpp_project.Data.Tutor;
import com.example.our_trpp_project.UI.StudentMain1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_trpp_project.Data.Subject;
import com.example.our_trpp_project.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<Subject> subjects;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Tutor tutor);
    }

    public SubjectAdapter(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.subjectName.setText(subject.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    // Получаем первого репетитора из списка
                    Tutor firstTutor = subject.getTutors().get(0);
                    listener.onItemClick(firstTutor); // Передаем выбранного репетитора
                }
            }
        });
        // Создайте и установите адаптер для подсписка репетиторов
        TutorAdapter tutorAdapter = new TutorAdapter(subject.getTutors());
        holder.tutorRecyclerView.setAdapter(tutorAdapter);
    }


    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        RecyclerView tutorRecyclerView;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            tutorRecyclerView = itemView.findViewById(R.id.tutorRecyclerView);
            tutorRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
