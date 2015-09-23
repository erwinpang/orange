package ekp25.orange;

import java.math.BigInteger;

public final class Refund implements Request {
	
	private BigInteger rma;
	
	private Refund(Builder builder){
		
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		// TODO Auto-generated method stub

	}
	
	public BigInteger getRma(){
		return rma;
	}
	
	public static class Builder{
		
		public Builder setRma(BigInteger rma) throws RequestException{
			//TODO
		}
		
		public BigInteger getRma(){
			//TODO
		}
		
		
	}

}
