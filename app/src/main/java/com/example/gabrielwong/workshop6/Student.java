package com.example.gabrielwong.workshop6;

import java.util.HashMap;

public class Student extends HashMap<String, String>  {
    /**
     * Student model object
     */
    public Student(String name, int age, String school) {
        put("name", name);
        put("age", Integer.toString(age));
        put("school", school);
    }
}
