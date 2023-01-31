package pl.edu.pjwstk.snl.match.dto;


public enum Situation {
    EQ,//równowaga
    PP1,//przewaga jednego gracza
    PP2,//przewaga 2 graczy
    SH1,//osłabienie 1 gracza
    SH2,//osłabienie 2 graczy
    EN,//pusta bramka
    PS,//z karnego
    PSS;//karny decydujący

    public boolean isPowerplay() {
        return this == PP1 || this == PP2;
    }
    public boolean isShortHanded() {
        return this == SH1 || this == SH2;
    }
}


