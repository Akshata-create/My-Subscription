package subscription;

import java.util.*;

public class SubscriptionCalculator {
	
	private static final Map<String, double[]> subscriptionPrices = new HashMap<String, double[]>() {{
        put("TOI", new double[] {3, 3, 3, 3, 3, 5, 6});
        put("Hindu", new double[] {2.5, 2.5, 2.5, 2.5, 2.5, 4, 4});
        put("ET", new double[] {4, 4, 4, 4, 4, 4, 10});
        put("BM", new double[] {1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5});
        put("HT", new double[] {2, 2, 2, 2, 2, 4, 4});
    }};

    public static List<List<String>> calculateSubscriptions(double budget) {
        List<List<String>> subscriptions = new ArrayList<>();

        // Loop through all possible combinations of newspapers
        for (int i = 2; i <= subscriptionPrices.size(); i++) {
            for (List<String> combination : combinations(subscriptionPrices.keySet(), i)) {
                double total_price = 0;
                // Calculate the total price of the combination
                for (int j = 0; j < 7; j++) {
                    double price = 0;
                    for (String newspaper : combination) {
                        price += subscriptionPrices.get(newspaper)[j];
                    }
                    total_price += price;
                }
                // If the total price is within the budget, add the combination to the list
                if (total_price <= budget) {
                    subscriptions.add(combination);
                }
            }
        }
        return subscriptions;
    }

    private static <T> List<List<T>> combinations(Set<T> set, int k) {
        List<List<T>> result = new ArrayList<>();
        List<T> list = new ArrayList<>(set);
        int[] indices = new int[k];
        for (int i = 0; i < k; i++) {
            indices[i] = i;
        }
        while (indices[0] <= list.size() - k) {
            List<T> combination = new ArrayList<>();
            for (int index : indices) {
                combination.add(list.get(index));
            }
            result.add(combination);
            int i = k - 1;
            while (i >= 0 && indices[i] == i + list.size() - k) {
                i--;
            }
            if (i < 0) {
                break;
            }
            indices[i]++;
            for (int j = i + 1; j < k; j++) {
                indices[j] = indices[j-1] + 1;
            }
        }
        return result;
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double budget = 40;
        List<List<String>> subscriptions = calculateSubscriptions(budget);
        for (List<String> subscription : subscriptions) {
            System.out.println(subscription);
        }
		

	}

}
