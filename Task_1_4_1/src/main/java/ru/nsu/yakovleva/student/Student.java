package ru.nsu.yakovleva.student;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for each student.
 */
public class Student {
    private final String fullName;
    private final String group;
    private final List<Grade> gradeList;


    /**
     * Initialization of a student.
     *
     * @param fullName  - name of a student.
     * @param group     - group of a student.
     */
    public Student(String fullName, String group) {
        this.fullName = fullName;
        this.group = group;
        this.gradeList = new ArrayList<>();
    }

    /**
     * Adding a new grade method.
     *
     * @param subjectName - name of subject.
     * @param grade - given grade.
     * @param dateOfPassing - date of exam.
     * @param semester - number of semester (1-8).
     * @param teacherFullName - name of teacher who took an exam.
     */
    public void addGrade(String subjectName, Integer grade,
                         String dateOfPassing, Integer semester, String teacherFullName) {
        Grade newGrade = new Grade(subjectName, grade, dateOfPassing, semester, teacherFullName);
        gradeList.add(newGrade);
    }

    /**
     * Get all grades of a student.
     *
     * @return - list of grades.
     */
    public List<Grade> getGrades() {
        return gradeList;
    }

    /**
     * Get  full name of a student.
     *
     * @return - name.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Get group of a student.
     *
     * @return - group.
     */
    public String getGroup() {
        return group;
    }
}



