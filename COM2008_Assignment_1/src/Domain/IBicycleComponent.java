package Domain;

public interface IBicycleComponent {
	public String getBrandName();
	public int getSerialNumber();
	public double getCost();
	public int getStockNum();
	public void reduceStockNum();
}
