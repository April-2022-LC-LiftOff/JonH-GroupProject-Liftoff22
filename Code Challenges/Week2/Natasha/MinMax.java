import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMax {
    public static void main(String[] args) {
        List<Integer> myNumbers = new ArrayList<>();
        myNumbers.add(33);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(34);
        myNumbers.add(8);
        myNumbers.add(12);

        System.out.println(minMax(myNumbers));

    }


    public static List<Integer> minMax(List<Integer> numbers) {
        Collections.sort(numbers);

        if (numbers.isEmpty()) {
            return new ArrayList<>();
        }
        Integer first = numbers.get(0);
        Integer last = numbers.get(numbers.size() - 1);

        return List.of(first, last);


    }

}