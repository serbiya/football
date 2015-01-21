package entity;

public class Coach extends Member {
    private CoachingLevel coachingLevel;

    public CoachingLevel getCoachingLevel() {
        return coachingLevel;
    }

    public void setCoachingLevel(CoachingLevel coachingLevel) {
        this.coachingLevel = coachingLevel;
    }
}
