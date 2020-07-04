package ee.swan.api.domain;

public class SearchCondition {
    private int rank;
    private int grade;
    private String name;

    public SearchCondition(int rank, int grade, String name) {
        this.rank = rank;
        this.grade = grade;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
