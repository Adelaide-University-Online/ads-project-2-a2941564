import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CourseGraph {
    // The Adjacency List
    private Map<String, Course> graph;

    public CourseGraph() {
        this.graph = new HashMap<>();
    }

    public void buildGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null) return;

            // Line 1: Initialize all vertices (courses)
            String[] allCourses = line.split(",");
            for (String courseCode : allCourses) {
                courseCode = courseCode.trim();
                graph.put(courseCode, new Course(courseCode));
            }

            // Subsequent lines: Map the edges (Course, Prereq1, Prereq2)
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                String targetCourseCode = parts[0].trim();
                Course targetCourse = graph.get(targetCourseCode);

                if (targetCourse != null) {
                    // Loop through the listed prerequisites starting at index 1
                    for (int i = 1; i < parts.length; i++) {
                        String prereqCode = parts[i].trim();
                        Course prereqCourse = graph.get(prereqCode);

                        if (prereqCourse != null) {
                            // Directed Edge: Prerequisite -> Target
                            prereqCourse.addDependentCourse(targetCourseCode);
                            // Increment the in-degree of the target course
                            targetCourse.incrementInDegree();
                        }
                    }
                }
            }
            System.out.println("Graph successfully built from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public Map<String, Course> getGraph() {
        return graph;
    }

    public void printGraphState() {
        for (Course course : graph.values()) {
            System.out.println(course.toString());
        }
    }
}
