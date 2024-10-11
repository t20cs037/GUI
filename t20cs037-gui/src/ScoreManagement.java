import java.io.*;
import java.util.*;

public class ScoreManagement {
    private LinkedList<Long> scorelist;

    public ScoreManagement() {
        super();
        scorelist = new LinkedList<Long>();
    }

    // 新たなスコアをファイルにもとあるスコアに追加して書き込む
    public void appendScoreToFile(String filename, long score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(Long.toString(score));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ファイルからスコアを読み取ってリストに格納してリストを返す
    public List<Long> readScoreFromFile(String filename) {
        List<Long> score = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                score.add(Long.parseLong(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    // 保存してるすべてのスコアをファイルに書き込む
    public void writeScoreToFile(String filename, List<Long> score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (long s : score) {
                writer.write(Long.toString(s));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // スコアのリストを小さい順にソートして、５番目よりあとの値を消去する
    public void sortAndWriteTop5Scores(String filename) {
        scorelist = new LinkedList<>(readScoreFromFile(filename));
        Collections.sort(scorelist);
        if (scorelist.size() > 5) {
            scorelist = new LinkedList<>(scorelist.subList(0, 5));
        }
        writeScoreToFile(filename, scorelist);
    }

    public int size() {
        return scorelist.size();
    }
}
