import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class HomeWork
{
    private static String extracted(String string) {
        int index = string.lastIndexOf(".");
        return (index != -1) ? string.substring(index + 1) : string;
    }

    public static List<String> camelMatch(String[] inputStr, String pattern) {
        List<String> result = new ArrayList<>();

        boolean isLowerPattern = checkCaseInPattern(pattern);

        String p = pattern;
        if(isLowerPattern)
            p = pattern.toUpperCase();

        char[] patternArr = p.toCharArray();

        for (String className : inputStr) {

            boolean isMatch = match(className.toCharArray(), patternArr);

            if(isMatch)
                result.add(className);
        }

        if(result.size() <= 0)
            result.add("no matches found");

        return result;
    }

    private static boolean checkCaseInPattern(String str) {
        char ch;
        for(int i=0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                return false;
            }
        }
        return true;
    }

    private static boolean match(char[] classNameArr, char [] patternArr) {

        int j = 0;
        int k = patternArr.length - 1;

        if(patternArr[k] == ' ')
        {
            char [] ch = Arrays.copyOf(patternArr, patternArr.length - 1);

            String s1 = new String(ch);
            String s2 = new String(classNameArr);

            String lastPatternWord =  getLastWord(s1);
            String lastClassNameWord = getLastWord(s2);

            if(!lastPatternWord.equals(lastClassNameWord))
                return false;
        }

        for (char c : classNameArr) {
            if (j < patternArr.length && (c == patternArr[j] || patternArr[j] == '*')) {
                j++;
            }

        }
            return (patternArr[k] == ' ') ? (j == patternArr.length - 1) : j == patternArr.length;
    }

    private static String getLastWord(String s) {
        String lastWord = "";
        int i = s.length() - 1;

        while (i >= 0)
        {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                lastWord += Character.toString(s.charAt(i));
            else {
                lastWord += Character.toString(s.charAt(i));
                break;
            }
            i--;
        }

        return lastWord;
    }

    public static void main(String[] args) throws Exception
    {
        if(args.length < 2)
        {
            System.out.println("Program usage is: filename and pattern");
            System.exit(0);
        }

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String str;

        ArrayList<String> list = new ArrayList<>();
        while((str = reader.readLine()) != null ){
            if(!str.isEmpty()){
                list.add(str);
            }}
        String[] inputStr = list.toArray(new String[0]);

        String pattern = args[1];

        List<String> results = camelMatch(inputStr, pattern);

        List<String> listRes = results
                    .stream().sorted(Comparator.comparing(HomeWork::extracted))
                    .collect(Collectors.toList());

        for(String res : listRes) {
            System.out.println(res);
        }
    }
}

