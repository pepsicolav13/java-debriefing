public class Student {
	private String name;
	private int age;
	private int studentId;

	public Student(String name, int age, int studentId) {
		this.name = name;
		this.age = age;
		this.studentId = studentId;
	}
	// getter & setter
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return name.equals(other.name) && studentId == other.studentId;
    }

    public int hashCode() {
        return name.hashCode() + studentId;
    }
    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", ID: " + studentId;
    }
}
