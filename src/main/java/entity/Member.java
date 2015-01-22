package entity;

public abstract class Member {
    private String surname;
    private String firstName;
    private String secondName;

    private DayTime goodTime;

    private State currentState;

    private TeamFunction teamFunction;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public DayTime getGoodTime() {
        return goodTime;
    }

    public void setGoodTime(DayTime goodTime) {
        this.goodTime = goodTime;
    }

    public TeamFunction getTeamFunction() {
        return teamFunction;
    }

    public void setTeamFunction(TeamFunction teamFunction) {
        this.teamFunction = teamFunction;
    }
}
