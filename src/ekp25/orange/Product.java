package ekp25.orange;

import java.util.Optional;
import java.util.Set;

public interface Product {
	
	public SerialNumber getSerialNumber();
	public ProductType getProductType();
	public String getProductName();
	public Optional<Set<String>> getDescription();
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException;
	public void process(Refund request, RequestStatus requestStats) throws ProductException;
	
}
