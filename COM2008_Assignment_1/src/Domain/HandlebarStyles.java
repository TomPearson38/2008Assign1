package Domain;

/**
 * ENUM containing possible different handlebar styles
 * @author Alex Dobson
 *
 */
public enum HandlebarStyles {
	STRAIGHT{
        public String toString() {
            return "Straight";
        }
	},
	HIGH{
        public String toString() {
            return "High";
        }
	},
	DROPPED{
        public String toString() {
            return "Dropped";
        }
	}
}
