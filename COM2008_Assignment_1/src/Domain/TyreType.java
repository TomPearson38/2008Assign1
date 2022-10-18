package Domain;

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
