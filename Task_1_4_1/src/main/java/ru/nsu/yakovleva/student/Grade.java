package ru.nsu.yakovleva.student;

/**
 * Record for grades.
 *
 * @param subjectName - name of subject.
 * @param grade - given grade.
 * @param dateOfPassing - date of exam.
 * @param semester - number of semester (1-8).
 * @param teacherFullName - name of teacher who took an exam.
 */
record Grade(String subjectName, Integer grade, String dateOfPassing, Integer semester, String teacherFullName) {
}