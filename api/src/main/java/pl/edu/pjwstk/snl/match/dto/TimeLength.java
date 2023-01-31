package pl.edu.pjwstk.snl.match.dto;


public enum TimeLength {
    TWO(2),
    FOUR(4),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    TWENTY_FIVE(25),
    PENALTY_SHOT(0);

    private final int minute;

    TimeLength(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }
}