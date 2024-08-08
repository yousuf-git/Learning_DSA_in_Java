public class Buy_Sell_Stock {

    // this class will keep track of buy and sale price for which we can get max profit
    static class Pair {
        private int buy;
        private int sale;
        Pair(int buy, int sale) {
            this.buy = buy;
            this.sale = sale;
        }
        public int getBuy() {
            return buy;
        }
        public void setBuy(int buy) {
            this.buy = buy;
        }
        public int getSale() {
            return sale;
        }
        public void setSale(int sale) {
            this.sale = sale;
        }
    }
    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        System.out.println("Max Profit: " + getMaxProfit(prices));
    }

    // Time Complexity: O(n)
    public static int getMaxProfit(int[] prices) {
        int maxProfit = 0;
        int buyPrice = Integer.MAX_VALUE; // + infinity
        Pair pair = new Pair(buyPrice, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            // Check if today's price is higher then buying price - we can have profit
            if (prices[i] > buyPrice) {
                // update profit
                int profit = prices[i] - buyPrice;      // profit = sale price - buy price
                // maxProfit = Math.max(maxProfit, profit);
                if (profit > maxProfit) {
                    maxProfit = profit;
                    // If current profit is greater, update the buy and sale price in the pair
                    pair.setSale(prices[i]);
                    pair.setBuy(buyPrice);
                }
            } else { // today's price is less, so let's buy today
                buyPrice = prices[i];
            }
        }
        System.out.println("Buy at price: " + pair.getBuy());
        System.out.println("Sale at price: " + pair.getSale());
        return maxProfit;
    }
}