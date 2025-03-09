public class FindPermutation {
    void printPermutation(String str, String perm) {
        if (str.length() == 0) {
            System.out.println(perm);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);
            String remString = str.substring(0, i) + str.substring(i + 1);
            printPermutation(remString, perm + currChar);
        }
    }

    public static void main(String[] args) {
        String str = "ABC";
        FindPermutation p = new FindPermutation();
        p.printPermutation(str, str);
    }
}