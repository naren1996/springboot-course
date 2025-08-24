import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Student
    @ManyToOne
    @JoinColumn(name = "student_id")   // foreign key column
    private Students student;

    // Many-to-One relationship with Course
    @ManyToOne
    @JoinColumn(name = "course_id")    // foreign key column
    private Course course;

    private LocalDate enrollmentDate;
    private String grade;

    // ✅ Default Constructor (Hibernate needs it)
    public Enrollment() {}

    // ✅ Parameterized Constructor
    public Enrollment(Students student, Course course, LocalDate enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
//        this.grade = grade;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

//    public String getGrade() {
//        return grade;
//    }

//    public void setGrade(String grade) {
//        this.grade = grade;
//    }

    // ✅ toString() for debugging
    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student=" + (student != null ? student.getName() : "null") +
                ", course=" + (course != null ? course.getTitle() : "null") +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
