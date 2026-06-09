import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String courseCode;
    // Courses that are unlocked by completing this course (Outgoing Edges)
    private List<String> dependentCourses; 
    // Number of prerequisites required before this course can be taken
    private int inDegree; 

    public Course(String courseCode) {
        this.courseCode = courseCode;
        this.dependentCourses = new ArrayList<>();
        this.inDegree = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<String> getDependentCourses() {
        return dependentCourses;
    }

    public void addDependentCourse(String course) {
        this.dependentCourses.add(course);
    }

    public int getInDegree() {
        return inDegree;
    }

    public void incrementInDegree() {
        this.inDegree++;
    }

    public void decrementInDegree() {
        this.inDegree--;
    }

    // Standard methods required by the assessment brief
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "Code='" + courseCode + '\'' +
                ", inDegree=" + inDegree +
                ", unlocks=" + dependentCourses +
                '}';
    }
}
