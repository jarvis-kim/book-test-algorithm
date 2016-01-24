package kr.co.jarvisk.algorithm.apss.recursive;

import java.util.Stack;

/**
 * Page 148.
 * n개의 원소 중 네 개를 고르는 모든 경우를 출력.
 */
public class Pick {

    public final static void main(String[] agrs) {
//        pick(7, 0, new Stack<Integer>(), 4);
        pick2(7, new Stack<Integer>(), 4);
    }

    /**
     * 내가 짠것.
     * @param n 원소의 총 개수
     * @param i 현재 원소
     * @param numbers 선택된 원소들
     * @param k 원소를 선택할 개수
     */
    public static void pick(int n, int i, Stack<Integer> numbers, int k) {
        // 기저사례 : 숫자가 k 만큼 선택 되었다면 출력
        if ( numbers.size() == k ) {
            System.out.println(numbers.toString());
            return;
        }

        for (; i < n; i++ ) {
            numbers.push(i);
            pick(n, i + 1, numbers, k);
            numbers.pop();
        }
    }

    /**
     *
     * @param n 전체 원소의 수
     * @param picked 지금까지 고른 원소들의 번호
     * @param toPick 더 고를 원소의 수
     */
    public static void pick2(int n, Stack<Integer> picked, int toPick) {
        // 기저 사례 : 더 고를 원소가 없을 때 고른 원소들을 출력한다.
        if ( toPick == 0 ) {
            System.out.println(picked);
        }

        // 고를 수 있는 가장 작은 번호를 계산한다.
        int smallest = picked.empty() ? 0 : picked.lastElement() + 1;

        // 이 단계에서 원소 하나를 고른다.
        for ( int next = smallest; next < n; ++next ) {
            picked.push(next);
            pick2(n, picked, toPick - 1);
            picked.pop();
        }


    }

}
