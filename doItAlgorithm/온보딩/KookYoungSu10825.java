package org.example.doItAlgorithm.온보딩;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// https://www.acmicpc.net/status?user_id=joychae0714&problem_id=10825&from_mine=1
// solved silver4

public class KookYoungSu10825 {

    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            students.add(new Student(
                    st.nextToken(),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        List<Student> sortedStudents = sortStudents();

        for (Student student : sortedStudents) {
            System.out.println(student.name);
        }
    }

    private static List<Student> sortStudents() {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getKorean)
                        .reversed()
                        .thenComparing(Student::getEnglish)
                        .thenComparing(Student::getMath, Comparator.reverseOrder())
                        .thenComparing(Student::getName))
                .collect(Collectors.toList());
    }

    public static class Student {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        public int getKorean() {
            return korean;
        }

        public int getEnglish() {
            return english;
        }

        public int getMath() {
            return math;
        }
    }
}
