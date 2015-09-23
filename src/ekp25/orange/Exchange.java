package ekp25.orange;

import java.util.NavigableSet;
import java.util.Set;

public final class Exchange implements Request{
	
	private Exchange(Builder builder){
		
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		// TODO Auto-generated method stub
		
	}
	
	public NavigableSet<SerialNumber> getCompatibleProducts(){
		// TODO
	}
	
	public static class Builder{
		
		public Builder addCompatible(SerialNumber serialNumber){
			//TODO
		}
		
		public Set<SerialNumber> getCompatibleProducts(){
			//TODO
		}
		
		public Exchange build(){
			//TODO
		}
	}
}
