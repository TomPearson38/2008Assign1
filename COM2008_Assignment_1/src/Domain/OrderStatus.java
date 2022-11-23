package Domain;

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
