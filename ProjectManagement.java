import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProjectManagement {

  /**
   * evaluate equivalent effort from all resources, and calculate expected date of completionm for a project
   * @param project object
   * @return date of completion for the project
  */
  public Date getEstimatedCompletion(Project project) {
    //
    return null;
  }

  public static void main(String[] args) {
    System.out.println("Initializing Project Management Application");
  }
}

// Class to represent a project
class Project {
  String name;
  Date startDate;
  Duration hoursRequired;
  List<Task> dependencies;
  Map<Stage, Date> timeline;

}

// Class to represent different types of actors in project
class Member {
  int id;
  String name;
  Role role;
  ExperienceLevel experienceLevel;

}

// Represents a unit of task.
class Task {
  String name;
  String description;
  Duration requiredTimeForCompletion;
  Task dependency;

}

enum Role {MANAGER, CONSULTANT, DEVELOPER}

enum ExperienceLevel {JUNIOR, SENIOR, PRINCIPLE, MANAGEMENT}

enum Stage {
  REQUIREMENT_GATHERING, PROJECT_PLANNING, EXECUTION, ACCEPTANCE_TESTING, RELEASE  
}

