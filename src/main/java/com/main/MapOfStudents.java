package com.main;
import com.main.model.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MapOfStudents {
    public static void main(String[] args) {
        List<String> stringList = readFileData("./src/main/java/com/main/StudentData.txt");
        List<Student> studentsList = prepareData(stringList);
        Map<Integer, Integer> studentMap = processData(studentsList);
        printStudentMap(studentMap);
    }

    public static List<String> readFileData(String path) {
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(path));
            String lineText = null;
            while ((lineText = lineReader.readLine()) != null) {
                stringList.add(lineText);
            }
            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);

        }
        return stringList;
    }

    private static List<Student> prepareData(List<String> stringList) {
        List<Student> students = new ArrayList<>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                String[] arrayList = str.split(",");
                Student student = new Student(Integer.parseInt(arrayList[0]), arrayList[1], Integer.parseInt(arrayList[2].trim()));
                students.add(student);
            }
        }
        return students;
    }

    private static Map<Integer, Integer> processData(Iterable<Student> students) {
        Map<Integer, Integer> studentMap = new HashMap<>();
        int lowestId = Integer.MAX_VALUE;
        int averageMark = 0;
        int sum = 0;
        int count = 0;
        for (Student student : students) {
            int id = student.getId();
            if (id <= lowestId) {
                lowestId = id;
                if (id == lowestId) {
                    sum += student.getMark();
                    ++count;
                }
            }
        }
        averageMark = sum / count;
        studentMap.put(lowestId, averageMark);
        return studentMap;
    }

    public static void printStudentMap(Map<Integer, Integer> studentMap) {
        for (Map.Entry<Integer, Integer> entry : studentMap.entrySet()) {
            Integer key = entry.getKey();
            Integer students = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + students);
        }
    }
}