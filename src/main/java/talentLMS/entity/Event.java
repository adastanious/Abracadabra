package talentLMS.entity;

public enum Event {
    SELECTEVENT("Select event"),
    ONUSERCREATE("On user create"),
    ONUSERSIGNUP("On user signup"),
    XHOURSAFTERUSERSIGNUP("X hours after user signup"),
    XHOURSAFTERUSERSIGNUPANDTHEUSERHASNOTMADEAPURCHASE("X hours after user signup and the user has not made a purchase"),
    XHOURSAFTERUSERCREATION("X hours after user creation"),
    XHOURSAFTERUSERCREATIONANDTHEUSERHASNOTSIGNEDIN("X hours after user creation and the user has not signed in"),
    XHOURSAFTERUSERSIGNUPANDTHEUSERHASNOTSIGNEDIN("X hours after user signup and the user has not signed in"),
    XHOURSSINCEUSERLASTSIGNEDIN("X hours since user last signed in"),
    XHOURSSINCEUSERFIRSTSIGNINANDTHEUSERHASNOTCOMPLETEDANYCOURSE("X hours since user first sign in and the user has not completed any course"),
    XHOURSBEFOREUSERDEACTIVATION("X hours before user deactivation"),
    ONCOURSEASSIGNMENT("On course assignment"),
    ONCOURSESELFASSIGNMENT("On course self assignment"),
    XHOURSAFTERCOURSEACQUISITION("X hours after course acquisition"),
    XHOURSBEFORECOURSESTART("X hours before course start"),
    ONCOURSECOMPLETION("On course completion"),
    XHOURSAFTERCOURSECOMPLETION("X hours after course completion"),
    ONCOURSEFAILURE("On course failure"),
    ONCOURSEEXPIRATION("On course expiration"),
    XHOURSBEFORECOURSEEXPIRATION("X hours before course expiration"),
    ONCERTIFICATEACQUISITION("On certificate acquisition"),
    ONCERTIFICATEEXPIRATION("On certificate expiration"),
    XHOURSBEFORECERTIFICATEEXPIRATION("X hours before certificate expiration"),
    ONGROUPASSIGNMENT("On group assignment"),
    ONBRANCHASSIGNMENT("On branch assignment"),
    ONASSIGNMENTSUBMISSION("On assignment submission"),
    ONASSIGNMENTGRADING("On assignment grading"),
    ONILTSESSIONCREATE("On ILT session create"),
    ONILTSESSIONREGISTRATION("On ILT session registration"),
    XHOURSBEFOREANILTSESSIONSTARTS("X hours before an ILT session starts"),
    ONILTGRADING("On ILT grading"),
    ONUSERPAYMENT("On user payment"),
    ONLEVELXREACHED("On level X reached");

    private final String description;

    Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
