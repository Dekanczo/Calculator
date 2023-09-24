import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

final class Calculator {
    static String[] OPS = {"+", "-", "*", "/"};

    private Calculator() {}

    static int eval(String _input) throws Exception {
        String input = _input.replace(" ", "");

        String[] matchedOps = Arrays.stream(OPS).
                filter(input::contains).
                toArray(String[]::new);

        if (matchedOps.length != 1)
            throw new Exception();

        String op = matchedOps[0];
        String[] args = input.split(Pattern.quote(op));
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        if (!(a >= 1 && a <= 10 && b >= 1 && b <= 10))
            throw new Exception();

        Optional<Integer> result =
            switch (op) {
                case "+" -> Optional.of(a + b);
                case "-" -> Optional.of(a - b);
                case "*" -> Optional.of(a * b);
                case "/" -> Optional.of(a / b);
                default -> Optional.empty();
            };

        return result.orElseThrow();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int result = Calculator.eval(input);
        System.out.println(result);
    }
}