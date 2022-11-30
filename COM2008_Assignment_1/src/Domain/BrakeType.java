package Domain;

/**
 * Enum containing the different break types allowed
 * @author Alex Dobson
 *
 */
public enum BrakeType {
	RIM{ 
		public String toString() {
			return "Rim";
		}
    },
	DISK{ 
		public String toString() {
			return "Disk";
		}
    }
}
