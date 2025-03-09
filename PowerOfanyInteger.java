//Find the power of any integer using divide and conquer

public class PowerOfanyInteger {

    public static int power(int x, int n) {
        if (n == 0)
            return 1;

        if (n % 2 == 0)
            return power(x * x, n / 2);

        // If n is odd, return x * power(x * x, (n - 1) / 2)
        // Optimized version of power(x, n) = x * power(x, n/2) * power(x, n/2)
        // This reduces the number of recursive calls by half
        return x * power(x * x, (n - 1) / 2);

        // This algorithm is more efficient than the naive method for finding power of a
        // number

        // It works by repeatedly squaring the base and halving the exponent until the
        // exponent becomes 0

        // Time complexity: O(log n)

        // Space complexity: O(log n)

    }
    // main function

    public static void main(String[] args) {

        int x = 2;
        int n = 4;
        System.out.println("Power of " + x + " raised to " + n + " is: " + power(x, n));
    }
}
