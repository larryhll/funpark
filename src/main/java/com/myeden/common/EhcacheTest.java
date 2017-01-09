package com.myeden.common;

import com.myeden.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felhan on 1/7/2017.
 */
public class EhcacheTest {

    private static MyEhcacheUtil cache=MyEhcacheUtil.getInstance();
    private static final String PRODUCT_MAP_CACHE_NAME = "productMap";

    public static void main(String[] args) {

        setStudentDate();
        addStudentData(100002);
        deleteStudentData(1);
        showStudentData();
    }

    public static void setStudentDate() {
        int sizeStudent=10;
        List<Student> students = new ArrayList<Student>();
        Student student=null;
        for(int i=0;i<sizeStudent;i++) {
            student=new Student();
            student.setId(i);
            student.setName("Student Name " + i);
            students.add(student);
            cache.put(PRODUCT_MAP_CACHE_NAME + i, student);
            cache.put(PRODUCT_MAP_CACHE_NAME+i, i);
        }
        cache.put(PRODUCT_MAP_CACHE_NAME, students);
    }

    public static List<Student> getStudentData() {
        return (List<Student>) cache.get(PRODUCT_MAP_CACHE_NAME);
    }

    public static void deleteStudentData(int index) {
        List<Student> studentList=getStudentData();
        studentList.remove(studentList.get(index));
        cache.put(PRODUCT_MAP_CACHE_NAME, studentList);
    }

    public static void addStudentData(int index) {
        List<Student> studentList=getStudentData();
        Student student=new Student();
        student.setId(index);
        student.setName("Student Name" + index);
        studentList.add(student);
        cache.put(PRODUCT_MAP_CACHE_NAME, studentList);
    }

    public static void showStudentData() {
        List<Student> studentList = getStudentData();
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
    }

}
