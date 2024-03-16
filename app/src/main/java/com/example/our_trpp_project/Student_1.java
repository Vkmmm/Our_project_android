package com.example.our_trpp_project;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;

public class Student_1 {
    protected void OnCreate(View rootView){ // Принимаем корневую View в качестве параметра
        Button button = rootView.findViewById(R.id.button2); // Ищем кнопку внутри корневой View
        String text = "У меня уже есть аккаунт";
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
        button.setText(content);
    }
}
