/*
Given: prices[i] is price of stock at day i
Return span of prices
span[i] = continuous days before day i for which price <= current price

See: Questions/stockSpan.png for visual explanation

Example: stock prices = { 100, 80, 60, 70, 60, 85, 100 }

Day     Price      Span

0        100        1           First day
1         80        1           Only current day, there is no day before day 1 with price <= 80
2         60        1           Only current day, there is no day before day 2 with price <= 60
3         70        2           Current day + day 2 (60) = 2
4         60        1           No continuous day before day 4 such that price <= 60  
5         85        5           current + day 4 + day 3 + day 2 + day 1 = 5
6        100        7           current + 6 continuous days before today price <= 100 

*/

package Questions;

import java.util.Stack;

public class StockSpan {

    public static void main(String[] args) {
        int[] prices = { 100, 80, 60, 70, 60, 85, 100 };
        int[] span = new int[prices.length];
        // Expected Output: 1 1 1 2 1 5 6

        Stack<Integer> stk = new Stack<>();
        stk.push(0); // stack will contain indices
        span[0] = 1; // Since, we are starting here so there is no price before day 0
        for (int i = 1; i < prices.length; i++) {
            // while current price is greater than price at day in top of stack
            while (!stk.empty() &&  prices[i] > prices[stk.peek()]) {
                stk.pop();
            }
            if (stk.empty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - stk.peek(); // span = current day - prev High Price Day
            }
            stk.push(i);
        }
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i] + " ");
        }
        System.out.println();
    }

}
