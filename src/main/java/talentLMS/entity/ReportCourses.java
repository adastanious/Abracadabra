package talentLMS.entity;

import lombok.Data;

@Data
public class ReportCourses {
    private String course;
    private String category;
    private String assignedLearners;
    private String completedLearners;


    public ReportCourses(String course, String category, String assignedLearners, String completedLearners) {
        this.course = course;
        this.category= category;
        this.assignedLearners = assignedLearners;
        this.completedLearners = completedLearners;
    }

    @Override
    public String toString() {
        return "ReportCourses [" +
                "course='" + course  +
                "', category='" + category +
                "', assignedLearners='" + assignedLearners +
                "', completedLearners='" + completedLearners +"']\n";

    }
}
