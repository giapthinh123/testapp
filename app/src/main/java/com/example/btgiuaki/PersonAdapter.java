package com.example.btgiuaki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> personList;

    // Constructor
    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tt_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);

        holder.name.setText("Tên: " + person.getName());
        holder.age.setText("Tuổi: " + person.getAge());
        holder.gender.setText("Giới tính: " + person.getGender());
        holder.height.setText("Chiều cao: " + person.getHeight() + " cm");
        holder.weight.setText("Cân nặng: " + person.getWeight() + " kg");
        holder.bmi.setText("BMI: " + person.getBmi());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, gender, height, weight, bmi;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.tuoi);
            gender = itemView.findViewById(R.id.GT);
            height = itemView.findViewById(R.id.CC);
            weight = itemView.findViewById(R.id.CN);
            bmi = itemView.findViewById(R.id.BMI);
        }
    }
}


