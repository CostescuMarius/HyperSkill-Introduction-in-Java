package challenging.bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String inputLength = scanner.next();
        int length = 0;
        try {
            length = Integer.parseInt(inputLength);
            if(length == 0) {
                System.out.print("Error: the length cannot be 0.");
                System.exit(0);
            }

        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", inputLength);
            System.exit(0);
        }


        System.out.println("Input the number of possible symbols in the code:");
        String inputSymbolsRange = scanner.next();
        int symbolsRange = 0;
        try {
            symbolsRange = Integer.parseInt(inputSymbolsRange);
            if(symbolsRange == 0) {
                System.out.print("Error: the symbol range cannot be 0.");
                System.exit(0);
            } else if(symbolsRange < length) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", length, symbolsRange);
                System.exit(0);
            } else if (symbolsRange > 36) {
                System.out.print("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", symbolsRange);
            System.exit(0);
        }

        BullsCows bullsCows = new BullsCows();
        bullsCows.generateRandomCodeStage6(length, symbolsRange);

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        boolean codeGuessed = false;
        while(!codeGuessed) {
            System.out.printf("Turn %d:\n", turn);
            String userCode = scanner.next();
            if(userCode.length() < length) {
                System.out.println("Error: Please provide an answer with a correct length.");
                System.exit(0);
            }
            codeGuessed = bullsCows.grader(userCode);
            turn++;
        }

    }
}

class BullsCows {
    private String secretCode;

    public String getSecretCode() {
        return this.secretCode;
    }

    public void generateRandomCodeStage6(int length, int symbolRange) {
        char[] characters = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q' , 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] copyCharacters = Arrays.copyOf(characters, characters.length);


        Random random = new Random();
        int currentLength = 0;
        StringBuilder secretCodeSb = new StringBuilder();

        while(currentLength < length) {
            int randomPosition = random.nextInt(symbolRange);
            if(characters[randomPosition] == '-') {
                continue;
            }

            secretCodeSb.append(characters[randomPosition]);
            characters[randomPosition] = '-';
            currentLength++;
        }

        secretCode = secretCodeSb.toString();
        System.out.print("The secret is prepared: " );
        for(int i = 0; i < length; i++) {
            System.out.print("*");
        }

        if(length <= 9) {
            System.out.printf("(%c-%c)\n", copyCharacters[0], copyCharacters[symbolRange - 1]);
        } else {
            System.out.printf("(%c-%c, %c-%c)\n", copyCharacters[0], copyCharacters[9], copyCharacters[10], copyCharacters[symbolRange - 1]);
        }

    }

    public boolean grader(String userCode) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < userCode.length(); i++) {
            if (userCode.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else {
                for (int j = 0; j < secretCode.length(); j++) {
                    if (userCode.charAt(i) == secretCode.charAt(j)) {
                        cows++;
                    }
                }
            }
        }

        if (bulls > 0 && cows > 0) {
            if(bulls == 1 && cows != 1) {
                System.out.printf("Grade: %d bull and %d cows.\n", bulls, cows);
            } else if(bulls != 1 && cows == 1) {
                System.out.printf("Grade: %d bulls and %d cow.\n", bulls, cows);
            } else if(bulls == 1 && cows == 1) {
                System.out.printf("Grade: %d bull and %d cow.\n", bulls, cows);
            } else {
                System.out.printf("Grade: %d bulls and %d cows.\n", bulls, cows);
            }
        } else if (bulls == 0 && cows > 0) {
            if(cows == 1) {
                System.out.printf("Grade: %d cow.\n", cows);
            }
            System.out.printf("Grade: %d cows.\n", cows);
        } else if (bulls > 0 && cows == 0) {
            System.out.printf("Grade: %d bulls.\n", bulls);
            if (bulls == secretCode.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                return true;
            } else if(bulls == 1) {
                System.out.printf("Grade: %d bull.\n", bulls);
            }

        } else {
            System.out.println("Grade: None.");
        }

        return false;
    }

    public long generateRandomCodeStage1(int length) {
        int secretKey = 0;
        int currentLength = 0;
        int[] unicDigits = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        while (String.valueOf(secretKey).length() != length || secretKey == 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(System.nanoTime()));
            for (int i = sb.length() - 1; i >= 0 && currentLength < length; i--) {
                int everyNumberInReverse = Character.getNumericValue(sb.charAt(i));

                if (secretKey == 0 && everyNumberInReverse == 0) {
                    continue;
                } else {
                    if (unicDigits[everyNumberInReverse] == 0) {
                        secretKey = secretKey * 10 + everyNumberInReverse;
                        currentLength++;
                        unicDigits[everyNumberInReverse]++;
                    }
                }
            }
        }

        return secretKey;
    }

    public long generateRandomCodeStage5(int length) {
        int secretKey = 0;
        int[] unicDigits = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Random random = new Random();

        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        } else {
            int firstDigitInSecretKey = random.nextInt(9) + 1;
            secretKey += firstDigitInSecretKey;
            unicDigits[firstDigitInSecretKey]++;
            int currentLength = 1;
            while (currentLength < length) {
                int digitsInSecretKey = random.nextInt(10);

                if (unicDigits[digitsInSecretKey] == 0) {
                    secretKey = secretKey * 10 + digitsInSecretKey;
                    currentLength++;
                }
                unicDigits[digitsInSecretKey]++;
            }
        }

        return secretKey;
    }
}