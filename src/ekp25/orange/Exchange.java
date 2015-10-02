package ekp25.orange;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import ekp25.orange.RequestStatus.StatusCode;

public final class Exchange implements Request{
	private final NavigableSet<SerialNumber> compatibleProducts;
	
	private Exchange(Builder builder){
		this.compatibleProducts = (NavigableSet<SerialNumber>) builder.getCompatibleProducts();
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		try{
		     product.process(this, status);
		} catch (ProductException e){
		     status.setStatusCode(StatusCode.FAIL);
		}
	}
	
	public NavigableSet<SerialNumber> getCompatibleProducts(){
		return new TreeSet<SerialNumber>(compatibleProducts);
	}
	
	public static class Builder{
		private final NavigableSet<SerialNumber> compatibleProducts = new TreeSet<SerialNumber>();
		
		public Builder addCompatible(SerialNumber serialNumber){
			compatibleProducts.add(serialNumber);
			return this;
		}
		
		public Set<SerialNumber> getCompatibleProducts(){
			return new TreeSet<SerialNumber>(compatibleProducts);
		}
		
		public Exchange build(){
			return new Exchange(this);
		}
		
	}
	

	
}
