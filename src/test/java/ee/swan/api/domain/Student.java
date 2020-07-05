package ee.swan.api.domain;

public class Student {
    private int id;
    private String name;
    private int grade;
    private int rank;
    private String prize;

    public Student() {
    }

    public Student(int id, String name, int grade, int rank) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", grade=").append(grade);
        sb.append(", rank=").append(rank);
        sb.append(", prize=").append(prize);
        sb.append('}');
        return sb.toString();
    }
}
