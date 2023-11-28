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
                         String dateOfPassing, Integer semester, String teacherFullName)
            throws Exception {
        if (grade < 2 || grade > 5) {
            throw new Exception("Grade must be between 2 and 5.");
        }

        boolean hasPreviousFail = gradeList.stream()
                .anyMatch(existingGrade -> existingGrade.getSemester().equals(semester - 1)
                        && existingGrade.getGrade() == 2);

        if (hasPreviousFail) {
            throw new Exception("Cannot add a new grade. Previous semester has a '2' mark.");
        }

        boolean found = gradeList.stream()
                .anyMatch(existingGrade -> existingGrade.getSubjectName().equals(subjectName)
                        && existingGrade.getSemester().equals(semester));

        if (found) {
            gradeList.stream()
                    .filter(existingGrade -> existingGrade.getSubjectName().equals(subjectName)
                            && existingGrade.getSemester().equals(semester))
                    .forEach(existingGrade -> {
                        existingGrade.setGrade(grade);
                        existingGrade.setDateOfPassing(dateOfPassing);
                        existingGrade.setTeacherFullName(teacherFullName);
                    });
        } else {
            Grade newGrade = new Grade(subjectName, grade, dateOfPassing, semester, teacherFullName);
            gradeList.add(newGrade);
        }
    }


    /**
     * Method for getting list of grades for a specific subject.
     */
    public List<Grade> getGradesForSubject(String subjectName) {
        List<Grade> subjectGrades = new ArrayList<>();
        for (Grade grade : gradeList) {
            if (grade.getSubjectName().equals(subjectName)) {
                subjectGrades.add(grade);
            }
        }
        return subjectGrades;
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



