package ru.nsu.yakovleva.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class RecordBookTest {

    private Student student;
    private StudentRecordBook studentRecordBook;

    @BeforeEach
    public void testInitialize() {
        student = new Student("Valeria Yakovleva Viktorovna", "22213");
        student.addGrade("Discrete Mathematics", 5,
               "11.01.2023", 1, "Stukachev A.I.");
        student.addGrade("Mathematical Analysis", 4,
                "09.06.2023", 2, "Vaskevich V.L.");
        student.addGrade("English", 5,
                "13.01.2024", 3, "Savilova T.K.");
        student.addGrade("Differential Equations", 5,
                "15.06.2024", 4, "Vaskevich V.L.");
        student.addGrade("Computational Mathematics", 5,
                "16.01.2025", 5, "Vaskevich V.L.");
        student.addGrade("Bioinformatics", 4,
                "15.06.2025", 6, "Ivanova A.A.");
        student.addGrade("Computer Linguistics", 5,
                "20.01.2026", 7, "Smirnova V.V.");
        student.addGrade("Qualification Work", 5,
                "15.06.2026", 8, "Comission");

        studentRecordBook = new StudentRecordBook(student);

    }

    @Test
    public void testGetName() {
        String name = student.getFullName();
        assertEquals("Valeria Yakovleva Viktorovna", name);
    }

    @Test
    public void testGetGroup() {
        String group = student.getGroup();
        assertEquals("22213", group);
    }

    @Test
    public void testGetGrades() {
        List<Grade> grades = student.getGrades().getGradeList();

        assertEquals(8, grades.size());

        assertEquals("Discrete Mathematics", grades.get(0).getSubjectName());
        assertEquals(5, grades.get(0).getGrade());
        assertEquals("11.01.2023", grades.get(0).getDateOfPassing());
        assertEquals(1, grades.get(0).getSemester());
        assertEquals("Stukachev A.I.", grades.get(0).getTeacherFullName());
    }

    @Test
    public void testGpa() {
        double gpa = studentRecordBook.calculateGPA();
        double expectedGpa = ((5.0 + 4.0 + 5.0 + 5.0 + 5.0 + 4.0 + 5.0 + 5.0) / 8);
        assertEquals(expectedGpa, gpa);
    }

    @Test
    public void testDiplomaWithHonors() {
        boolean diploma = studentRecordBook.hasRedDiplomaWithHonors();
        assertTrue(diploma);
    }

    @Test
    public void testDiplomaWithHonorsFalse() {
        student.addGrade("Computer Science", 3,
                "19.01.2026", 7, "Smirnova A.V.");
        boolean diploma = studentRecordBook.hasRedDiplomaWithHonors();
        assertFalse(diploma);
    }

    @Test
    public void testScholarship() {
        boolean scholarship = studentRecordBook.hasIncreasedScholarship(7);
        assertTrue(scholarship);
    }

    @Test
    public void testScholarshipFalse() {
        student.addGrade("Computer Science", 4,
                "19.01.2026", 7, "Smirnova A.V.");
        boolean scholarship = studentRecordBook.hasIncreasedScholarship(7);
        assertFalse(scholarship);
    }


}
