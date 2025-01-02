/*
 * LeetCode Easy 819 Most Common Word
 * https://leetcode.com/problems/most-common-word/description/
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class L819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        // regex : [] 안의 글자 하나 이상
        String[] cleaned = paragraph.split("[ !?',;.]+");
        List<String> bannedList = Arrays.asList(banned);

        HashMap<String, Integer> countMap = new HashMap<>();
        int maxVal = -1;
        String ans = "";

        for (int i = 0; i < cleaned.length; i++) {
            String word = cleaned[i].toLowerCase();
            if (bannedList.contains(word)) continue;

            // Map 메소드 getOrDefault
            // Tip : Docs에서 메소드 검색 시 안나오면 상위 클래스로 찾아보기 (HashMap -> Map)
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);

            if (countMap.get(word) > maxVal) {
                maxVal = countMap.get(word);
                ans = word;
            }
        }

        return ans;
    }
}
