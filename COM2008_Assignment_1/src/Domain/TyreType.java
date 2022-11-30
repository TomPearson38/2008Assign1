package Domain;

/**
 * ENUM containing all possible tyre types
 * @author Alex Dobson
 *
 */
public enum TyreType {
	ROAD {
        public String toString() {
            return "Road";
        }
	},
	MOUNTAIN {
        public String toString() {
            return "Mountain";
        }
	},
	HYBRID {
        public String toString() {
            return "Hybrid";
        }
	}
}
