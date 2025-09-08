/**
 * This class contains the available money denomination for
 * paying for an item or product in the vending machine app. 
 */
public class MoneyDenomination {
	/**
	 * The collection of denominations available in the machine
	 */
	private int[] denominations;

	/**
	 * This is a constructor that will initialize the available
	 * denominations upon creation of the vending machine.
	 */
    public MoneyDenomination() {
        denominations = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    /**
     * This will determine the denominations that will be given
     * to the user when dispensing the change. It will return
     * true if the change has enough denominations to give as a 
     * change; otherwise, it will return false.
     * @param change of the user
     * @return if there is a change
     */
    public boolean hasDenominations(double change) {
        int[] denomCopy = denominations.clone();

        int[] denom = new int[]{1000, 500, 200, 100, 50, 20, 10, 5, 1};

        for (int i = 0; i < denom.length; i++) {
            if (change >= denom[i]) {
                int count = (int) (change / denom[i]);
                count = Math.min(count, denomCopy[i]);
                change -= count * denom[i];
                denomCopy[i] -= count;
            }
        }
        return change == 0;
    }

    /**
     * This will give the change of the user in different denominations 
     * upon payment of the item in the vending machine.
     * @param change of the user
	 * @return string of denominations
     */
    public String giveChange(double change) {
        StringBuilder result = new StringBuilder();
		int[] values = new int[]{1000, 500, 200, 100, 50, 20, 10, 5, 1};

		/* Compute for the denominations that will be given to the user */
		for (int i = 0; i < values.length; i++) {
			if (change >= values[i]) {
				int count = (int) (change / values[i]);
				count = Math.min(count, denominations[i]);
				change -= count * values[i];
				denominations[i] -= count;
				result.append("Give ").append(count).append(" of ").append(values[i]).append(" peso denomination\n");
			}
		}
		return result.toString();
    }

    /**
     * This will allow the user to replenish the money denominations in
     * the vending machine. At the start of creating the machine, the
     * user must replenish the denominations first so that transactions
     * will not be cancelled.
     */
    public void replenish() {
        denominations = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    }
}
