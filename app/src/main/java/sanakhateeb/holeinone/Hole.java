package sanakhateeb.holeinone;

public class Hole {
    private int holeNum;
    private int score;

    public Hole(int num, int strokes)
    {
        holeNum = num;
        score = strokes;
    }

    public int getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(int holeNum) {
        this.holeNum = holeNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
