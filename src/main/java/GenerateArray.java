import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* Generate an array of Int from a String passed as parameter.
        Input -> Output
        ‘2’ -> [2]
        ‘2, 5, 8’ -> [2, 5, 8]
        ‘5-10’ -> [5, 6, 7, 8, 9, 10]
        ‘5-10:2’ -> [5, 7, 9]
        ‘1, 3, 5-8’ -> [1, 3, 5, 6, 7, 8]
        ‘1-10, 14, 20-25:2’ - [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 20, 22, 24]
*/

public class GenerateArray {
    public static int[] generateArray(String input){
        List<Integer> output = new ArrayList<>();
        String[] inputSeparatedByCommas = separateBy(input, ",");

        for(int i = 0; i < inputSeparatedByCommas.length; i++) {
            int rangeIndex = getIndexOf(inputSeparatedByCommas[i], "-");
            boolean notARange = rangeIndex < 0;
            if (notARange) {
                addNonRangeElement(output, Integer.parseInt(inputSeparatedByCommas[i]));
            } else {
                String[] rangeSeparated = separateBy(inputSeparatedByCommas[i], "-");
                int skipsIndex = getIndexOf(inputSeparatedByCommas[i], ":");
                boolean noSkips = skipsIndex < 0;
                if (noSkips) {
                    addRangeWithNoSkips(output, rangeSeparated);
                } else {
                    addRangeWithSkips(output, rangeSeparated);
                }
            }
        }
        return output.stream().mapToInt(i->i).toArray();
    }

    private static String[] separateBy(String input, String s) {
        return input.split(s);
    }

    private static int getIndexOf(String inputSeparatedByComma, String s) {
        return inputSeparatedByComma.indexOf(s);
    }

    private static void addRangeWithSkips(List<Integer> output, String[] rangeSeparated) {
        int start = Integer.parseInt(rangeSeparated[0]);
        String regex = "\\W";
        String[] skipsString = separateBy(rangeSeparated[1], regex);
        int skips = Integer.parseInt(skipsString[1]);
        int end = Integer.parseInt(skipsString[0]);
        for (int j = start; j < end; j += skips) {
            output.add(j);
        }
    }

    private static void addRangeWithNoSkips(List<Integer> output, String[] rangeSeparated) {
        int start = Integer.parseInt(rangeSeparated[0]);
        int end = Integer.parseInt(rangeSeparated[1]);
        List<Integer> listRange = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
        for(int element: listRange) output.add(element);
    }

    private static void addNonRangeElement(List<Integer> output, int i2) {
        output.add(i2);
    }
}
