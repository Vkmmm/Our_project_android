package com.example.our_trpp_project.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.our_trpp_project.R;

import java.util.ArrayList;
import java.util.List;

public class Student_choice extends Fragment {

    public Student_choice() {
        super(R.layout.student_choice);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("some_int", 0);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.student_choice, container, false);
        RecyclerView itemsList = view.findViewById(R.id.recycleView);
        MyRecycleAdapter adapter = new MyRecycleAdapter(getContext(), generateItemListRecycle());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        itemsList.setLayoutManager(layoutManager);
        itemsList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Toast.makeText(getContext(), "Выбран элемент",Toast.LENGTH_SHORT).show();
                Log.d("Fragment3", "Выбран элемент");
            }
        });


        return view;
    }

    private List<Item> generateItemListRecycle() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("математика"));
        items.add(new Item("русский язык"));
        items.add(new Item("информатика"));
        items.add(new Item("физика"));
        items.add(new Item("химия"));
        items.add(new Item("история"));
        items.add(new Item("обществознание"));
        items.add(new Item("география"));
        items.add(new Item("биология"));
        items.add(new Item("английский язык"));
        items.add(new Item("немецкий язык"));
        items.add(new Item("китайский язык"));


        return items;
    }
}