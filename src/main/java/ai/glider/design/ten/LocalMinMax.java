package ai.glider.design.ten;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LocalMinMax {

    private static Random random = new Random();

    public static List<Long> finMaxs(List<Long> numbers) {
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (
                    i - 1 > 0
                            && numbers.get(i - 1) < numbers.get(i)
                            && i + 1 < numbers.size()
                            && numbers.get(i + 1) < numbers.get(i)
            ) {
                res.add(numbers.get(i));
            }
        }

        return res;
    }

    public static List<Long> finMins(List<Long> numbers) {
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (
                    i - 1 > 0
                            && numbers.get(i - 1) > numbers.get(i)
                            && i + 1 < numbers.size()
                            && numbers.get(i + 1) > numbers.get(i)
            ) {
                res.add(numbers.get(i));
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 20; i < 25; i++) {
            FileWriter temp = new FileWriter("input" + i + ".txt");
            FileWriter temp2 = new FileWriter("output" + i + ".txt");
            List<Long> collect = LongStream.generate(() -> random.nextLong())
                    .limit(i * 100)
                    .boxed()
                    .collect(Collectors.toList());

            temp.write(collect.size() + "\n");
            collect.forEach(it -> {
                try {
                    temp.write(it + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            temp2.write("\n");
            finMaxs(collect).forEach(it -> {
                try {
                    temp2.write(it + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            temp2.write("\n");
            finMins(collect).forEach(it -> {
                try {
                    temp2.write(it + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            temp2.write("\n");
            temp.close();
            temp2.close();
        }
    }
}
