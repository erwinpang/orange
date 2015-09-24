package ekp25.orange;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import ekp25.orange.RequestException.ErrorCode;

public final class Exchange implements Request{
	private final NavigableSet<SerialNumber> compatibleProducts;
	
	private Exchange(Builder builder){
		this.compatibleProducts = builder.compatibleProducts;
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		if(status.getStatusCode() == RequestStatus.StatusCode.OK){
			compatibleProducts.add(product.getSerialNumber());
		}
		else{
			throw new RequestException(ErrorCode.INVALID_REQUEST);
		}
	}
	
	public NavigableSet<SerialNumber> getCompatibleProducts(){
		NavigableSet<SerialNumber> copySet = new TreeSet<SerialNumber>();
		for(SerialNumber s : compatibleProducts){
			copySet.add(s);
		}
		return copySet;
	}
	
	public static class Builder{
		private final NavigableSet<SerialNumber> compatibleProducts = new TreeSet<SerialNumber>();
		
		public Builder addCompatible(SerialNumber serialNumber){
			compatibleProducts.add(serialNumber);
			return this;
		}
		
		public Set<SerialNumber> getCompatibleProducts(){
			return compatibleProducts;
		}
		
		public Exchange build(){
			return new Exchange(this);
		}
		
	}
	

	
}
