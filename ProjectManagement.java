import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ProjectManagement {

  //a default roadmap for the project. It defines the ratio of time required for various stages in the project.
  final Map<ProjectStage, Integer> stageDurationRatio = Map.of(
    ProjectStage.REQUIREMENT_GATHERING, 2,
    ProjectStage.PROJECT_PLANNING, 2,
    ProjectStage.EXECUTION, 4,
    ProjectStage.ACCEPTANCE_TESTING, 1,
    ProjectStage.RELEASE, 1
    );


  /**
   * based on provided resources and dependencies calculate estimated completion
   * @param project object
   * @return date of completion for the project
  */
  public LocalDate getEstimatedCompletion(Project project) {
    //Compares a project's allocated resource to planned resource consumption.
    //eg planned number of days worth of effort from human resource vs available human resource effort.

    return null;
  }
  
  //Check if project can be completed within provided date.
  public boolean checkProjectCompletion(Project project, LocalDate endDate) {
    LocalDate estimatedCompletionDate = this.getEstimatedCompletion(project);

    if (estimatedCompletionDate != null)
      return estimatedCompletionDate.isAfter(endDate);
    else
      return false;
  }

  public static void main(String[] args) {
    System.out.println("Initializing Project Management Application");

    HumanResource seniorDevelopers = new HumanResource();
    seniorDevelopers.count = 6;
    seniorDevelopers.experienceLevel = ExperienceLevel.SENIOR;
    seniorDevelopers.role = Role.DEVELOPER;

    HumanResource leadDevelopers = new HumanResource();
    leadDevelopers.count = 2;
    leadDevelopers.experienceLevel = ExperienceLevel.LEAD;
    leadDevelopers.role = Role.DEVELOPER;

    HumanResource projectManagers = new HumanResource();
    projectManagers.count = 1;
    projectManagers.experienceLevel = ExperienceLevel.MANAGEMENT;
    leadDevelopers.role = Role.MANAGER;

    Task projectDevelopmentTask = new Task();
    projectDevelopmentTask.resources = List.of(seniorDevelopers, leadDevelopers, projectManagers);

    LocalDate projectStartDate = LocalDate.now();

    Project project = new Project();
    project.startDate = projectStartDate;
    project.timeline = Map.of(
      ProjectStage.REQUIREMENT_GATHERING, projectStartDate.plusWeeks(2),
      ProjectStage.PROJECT_PLANNING, projectStartDate.plusWeeks(4),
      ProjectStage.EXECUTION, projectStartDate.plusMonths(2)
    );
    project.requiredHumanResourseUnits = 100;

    ProjectManagement projectManagementApplication = new ProjectManagement();
    LocalDate plannedCompletionDate = projectStartDate.plusMonths(5);

    System.out.println(projectManagementApplication.checkProjectCompletion(project, plannedCompletionDate));
    
  }
}

// Class to represent a project
class Project {
  String name;
  LocalDate startDate;
  List<Task> tasks;
  Map<ProjectStage, LocalDate> timeline;

  //This is decided during project planning. Effort represented as number of days.
  int requiredHumanResourseUnits;
}

// Class to represent different types of actors in project
abstract class Resource {
  String description;
}

class HumanResource extends Resource {
  Role role;
  ExperienceLevel experienceLevel;
  int count;

  //This assumes that on increasing level of expertise, the work output increases.
  int getEffort() {
    switch (experienceLevel) {
      case LEAD:
        return count*4;
      case SENIOR: 
        return count*2;
      default:
        return count*1;
    }
  }
}

class MaterialResource extends Resource {
  String type;
  int quantity;
}

class FinancialResource extends Resource {
  double amount;
  String description;
}

// Represents a unit of task.
class Task {
  String name;
  String description;
  Duration requiredTimeForCompletion;
  List<Resource> resources;
  List<Task> externalDependency;

  // Task(String name, String description, Duration duration, List<Resource> resources, List<Task> dependencies) {
  //   this.name = name;

  // }
}

enum Role {MANAGER, CONSULTANT, DEVELOPER}

enum ExperienceLevel {JUNIOR, SENIOR, LEAD, MANAGEMENT}

enum ProjectStage {
  REQUIREMENT_GATHERING, PROJECT_PLANNING, EXECUTION, ACCEPTANCE_TESTING, RELEASE 
}

