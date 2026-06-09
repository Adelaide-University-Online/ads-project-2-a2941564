import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DegreePlanner {
    private CourseGraph graphData;

    public DegreePlanner(CourseGraph graphData) {
        this.graphData = graphData;
    }

    public void calculateStudyPlan(int maxConcurrent) {
        Map<String, Course> graph = graphData.getGraph();
        
        // Track in-degrees locally so we don't mutate the original graph's state
        // This allows us to run the calculation multiple times with different limits
        Map<String, Integer> inDegrees = new HashMap<>();
        for (Course c : graph.values()) {
            inDegrees.put(c.getCourseCode(), c.getInDegree());
        }

        // PriorityQueue is used instead of a standard Queue so that courses 
        // available in the same period are sorted alphabetically. 
        PriorityQueue<String> available = new PriorityQueue<>();
        
        // Step 1: Find all courses with 0 prerequisites
        for (Map.Entry<String, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) {
                available.add(entry.getKey());
            }
        }

        List<List<String>> studyPlan = new ArrayList<>();

        // Step 2: Process the queue period by period
        while (!available.isEmpty()) {
            List<String> currentPeriodCourses = new ArrayList<>();
            int count = 0;
            
            // Extract courses up to the maximum concurrent limit
            while (!available.isEmpty() && count < maxConcurrent) {
                currentPeriodCourses.add(available.poll());
                count++;
            }
            
            studyPlan.add(currentPeriodCourses);
            
            // Step 3: "Complete" the courses for this period and unlock dependents
            for (String completedCourse : currentPeriodCourses) {
                Course courseObj = graph.get(completedCourse);
                for (String dependent : courseObj.getDependentCourses()) {
                    int newInDegree = inDegrees.get(dependent) - 1;
                    inDegrees.put(dependent, newInDegree);
                    
                    // If all prerequisites are met, add to the available queue
                    if (newInDegree == 0) {
                        available.add(dependent);
                    }
                }
            }
        }

        // Step 4: Check for unresolved courses (indicates a cycle in the prerequisites)
        for (int degree : inDegrees.values()) {
            if (degree > 0) {
                System.out.println("Error: A cycle was detected. The degree cannot be completed.");
                return;
            }
        }

        // Step 5: Print the final optimized study plan
        System.out.println("--- Degree Plan (Max " + maxConcurrent + " courses per period) ---");
        for (int i = 0; i < studyPlan.size(); i++) {
            System.out.println("Study Period " + (i + 1) + ": " + String.join(", ", studyPlan.get(i)));
        }
        System.out.println("Total time taken: " + studyPlan.size() + " Study Periods.");
    }
}
