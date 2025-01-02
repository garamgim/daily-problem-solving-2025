/*
 * LeetCode Medium 937 Reorder Data in Log Files
 * https://leetcode.com/problems/reorder-data-in-log-files/description/
 * */

import java.util.ArrayList;
import java.util.List;

public class L937 {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letterList = new ArrayList<>(logs.length);
        List<String> digitList = new ArrayList<>(logs.length);

        // Tip: split시 limit 지정 가능 - split(String, int)
        for (int i = 0; i < logs.length; i++) {
            if (Character.isDigit(logs[i].split(" ", 2)[1].charAt(0))) {
                digitList.add(logs[i]);
            } else letterList.add(logs[i]);
        }

        // Tip: Sort하려는 Collections의 자료형이 지정되지 않으면 o1, o2가 Object로 처리됨
        letterList.sort((s1, s2) -> {
            String[] s1Split = s1.split(" ", 2);
            String[] s2Split = s2.split(" ", 2);

            int compared = s1Split[1].compareTo(s2Split[1]);

            if (compared == 0) {
                return s1Split[0].compareTo(s2Split[0]);
            }

            return compared;
        });

        letterList.addAll(digitList);

        // Tip: new String[0]을 사용해도 자동으로 길이에 맞는 배열을 생성해서 반환
        return letterList.toArray(new String[logs.length]);
    }
}
