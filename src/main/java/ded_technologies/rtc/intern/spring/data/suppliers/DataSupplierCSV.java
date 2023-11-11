package ded_technologies.rtc.intern.spring.data.suppliers;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package data.suppliers;
//
//import objects.Person;
//import interfaces.DataLoader;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import objects.Grade;
//import jpa.Group;
//import jpa.Performance;
//import jpa.Student;
//import jpa.Subject;
//
///**
// *
// * @author Даниил
// */
//public class DataSupplierCSV implements DataLoader{
//    private final BufferedReader READER;                                        //буффер чтения
//    private final List<Student> STUDENTS = new ArrayList<>();                   //несортированный массив учеников
//    private String[] headers;                                                   //заголовки
//    private int iLine=-1;                                                       //позиция по строке
//    private String[] subjects;                                                  //массив предметов
//    
//    public DataSupplierCSV(String file) throws FileNotFoundException, NullPointerException{
//        //инициализируем
//        READER = new BufferedReader(new FileReader(file));
//    }
//    //получаем строки с данными в виде массива
//    private String[] getNextData() throws IOException {
//        try {
//            iLine++;
//            //делим строку на части
//            return READER.readLine().split(";");
//        } catch (NullPointerException ex) {
//            //Logger.getLogger(DataSupplierCSV.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //когда данные закончились
//        return null;
//    }
//    
//    @Override
//    public String[] getHeaders() throws IOException{
//        //если заголовки не были прочитаны
//        if (iLine==-1){
//            //сохраняем
//            headers = getNextData();
//            subjects = Arrays.copyOfRange(headers, 4, headers.length);
//        }
//        //взвращаем после чтения и при повторных обращениях
//        return headers;
//    }
//    
//    public String[] getSubjects() throws IOException{
//        //если заголовки не были прочитаны
//        if (iLine==-1){
//            //сохраняем
//            headers = getNextData();
//            subjects = Arrays.copyOfRange(headers, 4, headers.length);
//        }
//        //взвращаем после чтения и при повторных обращениях
//        return subjects;
//    }
//    
//    @Override
//    public Student[] getStudents() throws IOException{
//        String[] line, sGrades;
//        //получаем все строки с данными
//        while ((line = getNextData()) != null) {
//            //если строка с заголовкам прочитана
//            if (iLine>0){
//                sGrades = Arrays.copyOfRange(line, 4, line.length);
//                //добавляем ученика
//                Person person = new Person();
//                person.setFamily(line[0]);
//                person.setName(line[1]);
//                person.setAge(Integer.parseInt(line[2]));
//                
//                Group group = new Group();
//                group.setNumber(Integer.parseInt(line[3]));
//                
//                Performance performance;
//                Map<Subject, Grade> grades = new HashMap<>();
//                double sum=0;
//                for (int i=0; i<sGrades.length;i++){
//                    Subject subject = new Subject();
//                    subject.setTitle(subjects[i]);
//                    
//                    Grade grade = new Grade();
//                    grade.setValue(Integer.parseInt(sGrades[i]));
//                    grades.put(subject, grade);
//                    
//                    sum=sum+Integer.parseInt(sGrades[i]);
//                }
//                performance = new Performance();
//                performance.setGrades(grades);
//                performance.setAverageGrade(sum/sGrades.length);
//                
//                Student student = new Student();
//                student.setPerson(person);
//                student.setGroup(group);
//                student.setPerformance(performance);
//                STUDENTS.add(student);
//            }else{
//                //иначе сохраняем заголовки
//                headers = line;
//                subjects = Arrays.copyOfRange(line, 4, line.length);
//            }
//	}
//        //взвращаем после чтения и при повторных обращениях
//        Student[] output = new Student[STUDENTS.size()];
//        STUDENTS.toArray(output);
//        return output;
//    }
//}
