package ru.nsu.yakovleva.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for a Record book of a student.
 */
public class StudentRecordBook {
    private final Student student;

    /**
     * Record book initialization.
     *
     * @param student - student.
     */
    public StudentRecordBook(Student student) {
        this.student = student;
    }

    /**
     * Method for calculating GPA of a student.
     *
     * @return - GPA.
     */
    public double calculateGpa() {
        List<Grade> allGrades = student.getGrades();
        List<Grade> lastGrades = new ArrayList<>();

        for (Grade grade : allGrades) {
            boolean found = false;
            for (int i = lastGrades.size() - 1; i >= 0; i--) {
                if (lastGrades.get(i).getSubjectName().equals(grade.getSubjectName())
                        && lastGrades.get(i).getSemester().equals(grade.getSemester())) {
                    lastGrades.set(i, grade);
                    found = true;
                    break;
                }
            }
            if (!found) {
                lastGrades.add(grade);
            }
        }

        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Grade grade : lastGrades) {
            Integer gradeValue = grade.getGrade();
            totalGradePoints += gradeValue;
            totalCredits += 1;
        }

        return totalCredits > 0 ? totalGradePoints / totalCredits : 0;
    }


    /**
     * Method for calculating if the student can get a Red diploma with honors or not.
     *
     * @return - true or false.
     */
    public boolean hasRedDiplomaWithHonors() {
        List<Grade> allGrades = student.getGrades();

        int excellentCount = 0;
        int satisfactoryCount = 0;
        boolean hasExcellentQualificationWork = false;
        int totalSubjects = 0;

        for (Grade grade : allGrades) {
            if (grade.getGrade().equals(5)) {
                excellentCount++;
            } else if (grade.getGrade().equals(3)) {
                satisfactoryCount++;
            }

            if (grade.getSubjectName().equals("Qualification Work") && grade.getGrade().equals(5)) {
                hasExcellentQualificationWork = true;
            }

            totalSubjects++;
        }

        double percentageExcellent = ((double) excellentCount / totalSubjects) * 100;

        boolean hasExcellentGrades = percentageExcellent >= 75;
        boolean hasSatisfactoryFinalGrades = satisfactoryCount > 0;

        return hasExcellentGrades && hasExcellentQualificationWork && !hasSatisfactoryFinalGrades;
    }

    /**
     * Method for calculating if the student can get an increased scholarship for given semester.
     * The student can get it if they have all excellent marks.
     *
     * @param currentSemester - semester.
     * @return - true or false.
     */
    public boolean hasIncreasedScholarship(Integer currentSemester) {
        List<Grade> currentSemesterGrades = getSemesterGrades(currentSemester);

        boolean meetsScholarshipCriteria = true;

        for (Grade grade : currentSemesterGrades) {
            if (!grade.getGrade().equals(5)) {
                meetsScholarshipCriteria = false;
                break;
            }
        }

        return meetsScholarshipCriteria;
    }

    /**
     * Method for getting grades for a given semester.
     *
     * @param currentSemester - semester.
     * @return - list of grades.
     */
    private List<Grade> getSemesterGrades(Integer currentSemester) {
        List<Grade> allGrades = student.getGrades();
        List<Grade> currentSemesterGrades = new ArrayList<>();

        for (Grade grade : allGrades) {
            if (Objects.equals(grade.getSemester(), currentSemester)) {
                currentSemesterGrades.add(grade);
            }
        }

        return currentSemesterGrades;
    }
}
