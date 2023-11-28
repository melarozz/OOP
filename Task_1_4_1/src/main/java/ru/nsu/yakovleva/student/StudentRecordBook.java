package ru.nsu.yakovleva.student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

        Map<String, Map<Integer, List<Grade>>> groupedGrades = allGrades.stream()
                .collect(Collectors.groupingBy(Grade::getSubjectName,
                        Collectors.groupingBy(Grade::getSemester, Collectors.toList())));

        List<Grade> lastGrades = groupedGrades.values().stream()
                .flatMap(m -> m.values().stream().flatMap(List::stream))
                .collect(Collectors.groupingBy(grade -> grade.getSubjectName()
                                + grade.getSemester(),
                        Collectors.collectingAndThen(Collectors.toList(),
                                grades -> grades.stream().max(Comparator.comparing(Grade::getSemester))
                                        .orElse(null))))
                .values().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        double totalGradePoints = lastGrades.stream()
                .mapToInt(Grade::getGrade)
                .sum();

        int totalCredits = lastGrades.size();

        return totalCredits > 0 ? (double) totalGradePoints / totalCredits : 0;
    }

    /**
     * Method for calculating if the student can get a Red diploma with honors or not.
     *
     * @return - true or false.
     */
    public boolean hasRedDiplomaWithHonors() {
        List<Grade> allGrades = student.getGrades();

        long excellentCount = allGrades.stream()
                .filter(grade -> grade.getGrade().equals(5)).count();
        long satisfactoryCount = allGrades.stream()
                .filter(grade -> grade.getGrade().equals(3)).count();

        boolean hasExcellentQualificationWork = allGrades.stream()
                .anyMatch(grade -> grade.getSubjectName()
                        .equals("Qualification Work") && grade.getGrade().equals(5));

        int totalSubjects = allGrades.size();

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
