import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String type;  // e.g., Mobile, Home, Work

    // ✅ Many Phones belong to One Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Phone() {}

    public Phone(String number, String type) {
        this.number = number;
        this.type = type;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @Override
    public String toString() {
        return "Phone{id=" + id + ", number='" + number + "', type='" + type + "'}";
    }
}
