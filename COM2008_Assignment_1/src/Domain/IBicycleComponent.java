package Domain;

public interface IBicycleComponent {
	public String BrandName();
	public int SerialNumber();
	public double Cost();
	public int StockNum();
	public void reduceStockNum();
}
