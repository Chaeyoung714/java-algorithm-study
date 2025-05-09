package org.example.greedy;

// https://www.acmicpc.net/problem/1931
// Solved Gold5
// 아주 살짝 Referenced(?) - https://st-lab.tistory.com/145

/**
 * 2순위 정렬 기준도 정하는 이유
 * (start,end)
 * (2,2),
 * (1,2)인 경우
 * 사실은 1,2와 2,2 둘다 할 수 있는데도 2,2만 들어간다.
 * 따라서 종료 시간이 같으면 시작 시간은 더 이른 순으로 정렬해야 한다.
 */

//Generic
//Comparator.reverseOrdeR()를 추가하면 themComparing을 thenCOmparingInt로 하면 틀린다.
//thenComparing(meeting -> meeting.startTime)은 불가능하다 (타입추론 안돼서인듯)
// -> 근데 앞의 comparing(meeting -> meeting.startTime)도 함께 오류난다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MeetingRoomAssignment1931 {
    public static int maxEndTime = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int meetingCount = sc.nextInt();

        int minStartTime = Integer.MAX_VALUE;

        Meeting[] meetings = new Meeting[meetingCount];
        for (int i = 0; i < meetingCount; i++) {
            int startTime = sc.nextInt();
            int endTime = sc.nextInt();
            meetings[i] = new Meeting(startTime, endTime);

            minStartTime = Math.min(startTime, minStartTime);
            maxEndTime = Math.max(startTime, maxEndTime);
        }

        List<Meeting> meetingList = new ArrayList<>(Arrays.asList(meetings));

        List<Meeting> sortedMeetingList = meetingList.stream().sorted(
                        Comparator.comparing(Meeting::getEndTime)
                                .thenComparing(Meeting::getStartTime))
                .collect(Collectors.toList());

        int maxCount = calculateMaxMeetingCount(sortedMeetingList, minStartTime);
        System.out.println(maxCount);
    }

    public static int calculateMaxMeetingCount(List<Meeting> meetings, int currStartTime) {
        int index = 0;
        int meetingsCount = 0;

        int meetingsSize = meetings.size();
        while (currStartTime <= maxEndTime && index < meetingsSize) {
            Meeting meeting = meetings.get(index);
            if (meeting.startTime < currStartTime) {
                index++;
                continue;
            }

            currStartTime = meeting.endTime;
            meetingsCount++;
            index++;
        }

        return meetingsCount;
    }


    public static class Meeting {
        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }
}
