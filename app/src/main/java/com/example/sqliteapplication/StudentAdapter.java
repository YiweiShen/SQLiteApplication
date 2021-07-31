package com.example.sqliteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

public class StudentAdapter extends ArrayAdapter {
    private List<Student> students;
    private final LayoutInflater layoutInflater;
    private final int layoutResource;

    public StudentAdapter(@NonNull Context context, int resource, List<Student> students) {
        super(context, resource);
        this.students = students;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutResource = resource;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        v = layoutInflater.inflate(layoutResource, parent, false);

        TextView nameText = v.findViewById(R.id.stdName);
        TextView contactText = v.findViewById(R.id.stdContact);
        TextView dobText = v.findViewById(R.id.stdDob);

        nameText.setText("Name: " + students.get(position).getName());
        contactText.setText("Contact: " + students.get(position).getContact());
        dobText.setText("DOB: " + students.get(position).getDob());

        return v;
    }
}
