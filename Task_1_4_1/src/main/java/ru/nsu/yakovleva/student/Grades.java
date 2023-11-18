package ru.nsu.yakovleva.student;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for grades of a student.
 */
public class Grades {
    private final List<Grade> gradeList;

    /**
     * Grades initialization.
     */
    public Grades() {
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
    public void addGrade(String subjectName, Integer grade, String dateOfPassing, Integer semester, String teacherFullName) {
        Grade newGrade = new Grade(subjectName, grade, dateOfPassing, semester, teacherFullName);
        gradeList.add(newGrade);
    }

    /**
     * Getting a list of all grades.
     *
     * @return - list of grades.
     */
    public List<Grade> getGradeList() {
        return gradeList;
    }
}