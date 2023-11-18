package ru.nsu.yakovleva.student;

/**
 * Class for a grade.
 */
public class Grade {
    private String subjectName;
    private Integer grade;
    private String dateOfPassing;
    private Integer semester;
    private String teacherFullName;

    /**
     * Grade initialization.
     *
     * @param subjectName - name of subject.
     * @param grade - given grade.
     * @param dateOfPassing - date of exam.
     * @param semester - number of semester (1-8).
     * @param teacherFullName - name of teacher who took an exam.
     */
    public Grade(String subjectName, Integer grade, String dateOfPassing, Integer semester, String teacherFullName) {
        this.subjectName = subjectName;
        this.grade = grade;
        this.dateOfPassing = dateOfPassing;
        this.semester = semester;
        this.teacherFullName = teacherFullName;
    }

    /**
     * Getter of the subject.
     *
     * @return - subject name.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Getter of the grade.
     *
     * @return - grade.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Getter of the date of passing an exam.
     *
     * @return - date.
     */
    public String getDateOfPassing() {
        return dateOfPassing;
    }

    /**
     * Getter of the semester.
     *
     * @return - number of semester.
     */
    public Integer getSemester() {
        return semester;
    }

    /**
     * Getter of the teacher's name.
     *
     * @return - teacher's name.
     */
    public String getTeacherFullName() {
        return teacherFullName;
    }
}