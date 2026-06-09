import java.util.*;
import java.util.stream.IntStream;

public class ProblemSolving {

    public static void main(String[] args) {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};

       char[] chars = {'a','a','b','b','c','c','c'};

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

       // System.out.println(rotateString("abcde","cdeab"));

        String input = "ABC";

        String output = "";

        permutation(input, output);

    }

    //https://leetcode.com/problems/two-sum/description/

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }


  /*https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    Best Time to Buy and Sell Stock */

    /* Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell. */

    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {

            minPrice = Math.min(minPrice, price);

            int profit = price - minPrice;

            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }



 /* Rotate Array
    https://leetcode.com/problems/rotate-array/description/ */
   /* Example 1:

    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4] */

    static void rotate(int[] nums, int k) {


        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);


        reverse(nums, 0, k - 1);

        reverse(nums, k, n - 1);


    }

    static void reverse(int[] nums, int i, int j) {

        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /* 283. Move Zeroes
    https://leetcode.com/problems/move-zeroes/description/
    Example 1:

    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0] */


    static void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }

        }


    }


/* Remove Duplicates from Sorted Array
    https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
    Input: nums = [1,1,2]
    Output: 2, nums = [1,2,_]
    Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores). */

    static int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int index = 0;

        for (int num : set) {
            nums[index++] = num;
        }

        return set.size();
    }


    /*Longest Substring Without Repeating Characters
     https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
     Example 1:

     Input: s = "abcabcbb"
     Output: 3
     Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers. */
    static int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int n = s.length();
        int maxLength = 0;
        Set<Character> set = new HashSet<>();

        while (j < n) {
            char ch = s.charAt(j);
            if (!set.contains(ch)) {
                set.add(ch);
                int windowSize = j - i + 1;
                maxLength = Math.max(windowSize, maxLength);
                j++;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }

        /* Valid Anagram
    https://leetcode.com/problems/valid-anagram/description/
    Input: s = "anagram", t = "nagaram"

    Output: true  */

    static boolean isAnagram1(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        char[] chArr1 = s.toCharArray();
        char[] chArr2 = t.toCharArray();

        Arrays.sort(chArr1);
        Arrays.sort(chArr2);

        return Arrays.equals(chArr1, chArr2);

    }

    static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] frequency = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
            frequency[t.charAt(i) - 'a']--;
        }

        for (int count : frequency) {
            if (count != 0) {
                return false;
            }
        }

        return true;

    }

    /*Group Anagrams
    https://leetcode.com/problems/group-anagrams/description/?utm_source=chatgpt.com
    Example 1:

    Input: strs = ["eat","tea","tan","ate","nat","bat"]

    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

    Explanation:

    There is no string in strs that can be rearranged to form "bat".
    The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
    The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other. */

    static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);

        }
        return new ArrayList<>(map.values());

    }

    /*Product of Array Except Self
    https://leetcode.com/problems/product-of-array-except-self/description/?utm_source=chatgpt.com
    Example 1:

    Input: nums = [1,2,3,4]
    Output: [24,12,8,6] */

    static int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] res = new int[n];

        // prefix product
        res[0] = 1;

        for (int i = 1; i < n; i++) {

            res[i] = res[i - 1] * nums[i - 1];
        }

        // suffix product
        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {

            res[i] = res[i] * suffix;

            suffix = suffix * nums[i];
        }

        return res;
    }

    /*Container With Most Water
        https://leetcode.com/problems/container-with-most-water/description/?utm_source=chatgpt.com
        Input: height = [1,8,6,2,5,4,8,3,7]
        Output: 49
        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.*/
    static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int maxWater = 0;

        while (left < right) {

            // width between lines
            int width = right - left;

            // smaller height
            int minHeight = Math.min(height[left], height[right]);

            // calculate area
            int area = width * minHeight;

            // update maximum
            maxWater = Math.max(maxWater, area);

            // move smaller height pointer
            if (height[left] < height[right]) {

                left++;
            } else {

                right--;
            }
        }

        return maxWater;
    }
        /*Reverse Words in a String
    https://leetcode.com/problems/reverse-words-in-a-string/description/?utm_source=chatgpt.com
    Example 1:

    Input: s = "the sky is blue"
    Output: "blue is sky the" */

    static String reverseWords(String s) {

        if (s.length() == 1) {
            return s;
        }
        String res = "";

        String[] arr = s.split(" ");
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        for (int k = 0; k < arr.length; k++) {
            res = res + arr[k] + " ";
        }


        return res.trim().replaceAll("\\s+", " ");
    }

/*Valid Palindrome
    https://leetcode.com/problems/valid-palindrome/description/?utm_source=chatgpt.com
    Example 1:

    Input: s = "A man, a plan, a canal: Panama"
    Output: true
    Explanation: "amanaplanacanalpanama" is a palindrome. */


    static boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {

            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    /*Longest Palindromic Substring
    https://leetcode.com/problems/longest-palindromic-substring/description/?utm_source=chatgpt.com
    Example 1:

    Input: s = "babad"
    Output: "bab"
    Explanation: "aba" is also a valid answer. */



    static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        String rev = new StringBuilder(s).reverse().toString();


return longestCommonSubstring(s,rev);

    }


    static String longestCommonSubstring(String s1,String s2) {


            int n1 = s1.length();
            int n2 = s2.length();
            int[][] dp = new int[n1+1][n2+1];
            int endIndex = 0;
            int max = 0;

            for(int i = 1;i<n1;i++) {
                for(int j = 1;j<n2;j++) {
                    if(s1.charAt(i-1) == s2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1]+1;
                        if(dp[i][j]>max) {
                            max = dp[i][j];
                            endIndex = i;
                        }
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }

            String res = s1.substring(endIndex-max,endIndex);
          //  System.out.println(res);


            return res;
        }

        /*String Compression
    https://leetcode.com/problems/string-compression/description/?utm_source=chatgpt.com
    Example 1:

    Input: chars = ["a","a","b","b","c","c","c"]
    Output: 6
    Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3". */


        static int compress(char[] chars) {
            int index = 0, i = 0, n = chars.length;

            while (i < n) {
                char current_char = chars[i];
                int count = 0;

                while (i < n && chars[i] == current_char) {
                    i++;
                    count++;
                }

                chars[index] = current_char;
                index++;

                if (count > 1) {
                    String count_str = Integer.toString(count);
                    for (char c : count_str.toCharArray()) {
                        chars[index] = c;
                        index++;
                    }
                }
            }

            return index;
        }

//Count vowels/consonants

        static void countVowelsConsonants(String s) {

            int vowels = 0;
            int consonants = 0;

            s = s.toLowerCase();

            for (int i = 0; i < s.length(); i++) {

                char ch = s.charAt(i);

                // check alphabet
                if (Character.isLetter(ch)) {

                    // vowel check
                    if (ch == 'a' || ch == 'e' ||
                            ch == 'i' || ch == 'o' ||
                            ch == 'u') {

                        vowels++;
                    }
                    else {

                        consonants++;
                    }
                }
            }

            System.out.println("Vowels = " + vowels);

            System.out.println("Consonants = " + consonants);
        }

        /*First Unique Character in a String
    https://leetcode.com/problems/first-unique-character-in-a-string/description/?utm_source=chatgpt.com
    Example 1:

    Input: s = "leetcode"

    Output: 0

    Explanation:

    The character 'l' at index 0 is the first character that does not occur at any other index. */

    public int firstUniqChar(String s) {
       int[] freq = new int[26];

       for(int i = 0;i<s.length();i++) {
           freq[s.charAt(i) - 'a']++;
       }

        for(int i = 0;i<s.length();i++) {
          char ch = s.charAt(i);
          if(freq[ch - 'a'] == 1) {
              return i;
          }
        }
           return -1;

    }

    /*Rotate String
    https://leetcode.com/problems/rotate-string/description/?utm_source=chatgpt.com
    Example 1:

    Input: s = "abcde", goal = "cdeab"
    Output: true */

    //

    static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        String temp = s + s;

        return temp.contains(goal);

    }

    static String reverse(String s,int i,int j) {
        char[] arr = s.toCharArray();
        while(i<j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

         //input output method

        static void permutation(String input,
                                String output) {

            // Base case
            if (input.length() == 0) {

                System.out.println(output);

                return;
            }

            // Choices
            for (int i = 0; i < input.length(); i++) {

                // chosen character
                char ch = input.charAt(i);

                // remaining left part
                String left = input.substring(0, i);

                // remaining right part
                String right = input.substring(i + 1);

                // new input after removing chosen character
                String newInput = left + right;

                // recursive call
                permutation(newInput,
                        output + ch);
            }
        }


        /*Valid Parentheses
    https://leetcode.com/problems/valid-parentheses/description/?utm_source=chatgpt.com
    Input: s = "()"

    Output: true */


        public boolean isValid(String s) {



                if(s.isEmpty())
                {
                    return true;
                }

                Stack<Character> stack=new Stack<>();

                for(int i=0;i<s.length();i++) {
                    char ch = s.charAt(i);

                    if (ch == '{' || ch == '(' || ch == '[') {
                        stack.push(ch);
                    }

                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (ch == ')') {
                        char ch1 = stack.pop();
                        if (ch1 == '{' || ch1 == '[') {
                            return false;
                        }
                    }

                    if (ch == '}') {
                        char ch1 = stack.pop();
                        if (ch1 == '(' || ch1 == '[') {
                            return false;
                        }
                    }
                    else if (ch == ']') {
                        char ch1 = stack.pop();
                        if (ch1 == '(' || ch1 == '{') {
                            return false;
                        }
                    }
                }
                return stack.isEmpty();

            }

      /*     Next Greater Element I
    https://leetcode.com/problems/next-greater-element-i/description/?utm_source=chatgpt.com
    Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    Output: [-1,3,-1]
    Explanation: The next greater element for each value of nums1 is as follows:
            - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
            - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
            - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1. */


        static int[] nextGreaterElement(int[] nums1, int[] nums2) {

            Stack<Integer> stack = new Stack<>();
            Map<Integer, Integer> map = new HashMap<>();

            // Build next greater element map
            for (int num : nums2) {

                while (!stack.isEmpty() && stack.peek() < num) {

                    map.put(stack.pop(), num);
                }

                stack.push(num);
            }

            // Remaining elements have no greater element
            while (!stack.isEmpty()) {

                map.put(stack.pop(), -1);
            }

            int[] result = new int[nums1.length];

            for (int i = 0; i < nums1.length; i++) {

                result[i] = map.get(nums1[i]);
            }

            return result;
        }
}
