/**
* File: filename.java
* Description: A brief description of this Java module.
* Author: Steve Jobs
* Student ID: 12345678
* Email ID: jobst007
* AI Tool Used: Y/N (This includes all AI Tools e.g. ChatGPT, Microsoft or Github Copiliot etc... Please leave blank if you do not wish to share this information)
* This is my own work as defined by
*    the University's Academic Integrity Policy.
**/

public class Runner {
    public static void main(String[] args) {
        // Ensure you have the test files (like XBIT.txt) in the root of your project directory 
        String testFile = "XBIT.txt"; 
        
        CourseGraph myGraph = new CourseGraph();
        
        System.out.println("Initializing OptiTime Graph from " + testFile + "...\n");
        myGraph.buildGraphFromFile(testFile);
        
        DegreePlanner planner = new DegreePlanner(myGraph);
        
        // Scenario A: A student taking 4 courses per study period
        System.out.println("Executing Scenario A...");
        planner.calculateStudyPlan(4);

        System.out.println("\n==================================================\n");

        // Scenario B: A student taking 2 courses per study period
        System.out.println("Executing Scenario B...");
        planner.calculateStudyPlan(2);
    }
}
