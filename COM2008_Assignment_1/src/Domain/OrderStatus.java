package Domain;

/**
 * ENUM containing the possible different order status
 * @author tomap
 *
 */
public enum OrderStatus {
	PENDING{
        public String toString() {
            return "Pending";
        }
	},
	CONFIRMED{
        public String toString() {
            return "Confirmed";
        }
	},
	FULFILLED{
        public String toString() {
            return "Fulfilled";
        }
	}
}
