package ru.nsu.yakovleva.student;

/**
 * Class for each student.
 */
class Student {
    private final String fullName;
    private final String group;
    private final Grades grades;

    /**
     * Initialization of a student.
     *
     * @param fullName - name of a student.
     * @param group - group of a student.
     */
    public Student(String fullName, String group) {
        this.fullName = fullName;
        this.group = group;
        this.grades = new Grades();
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
        grades.addGrade(subjectName, grade, dateOfPassing, semester, teacherFullName);
    }

    /**
     * Get all grades of a student.
     * @return - grades object.
     */
    public Grades getGrades() {
        return grades;
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



