package pl.edu.utp.wtie.homeworkweek3.model;

public enum CarColour {
    BLACK("czarny"),
    BLUE("niebieski"),
    GREEN("zielony"),
    PINK("różowy"),
    RED("czerwony"),
    SILVER("srebrny"),
    WHITE("biały"),
    YELLOW("zółty");

    private String polishName;

    CarColour(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
