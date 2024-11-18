package com.example.btgiuaki;

public class Person {
    private String name, gender, bmi;
    private int age, height, weight;

    // Constructor mặc định (cần cho Firebase)
    public Person() {}

    // Constructor có tham số
    public Person(String name, String gender, String bmi, int age, int height, int weight) {
        this.name = name;
        this.gender = gender;
        this.bmi = bmi;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}