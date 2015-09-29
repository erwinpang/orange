package ekp25.orange;

import java.math.BigInteger;

import ekp25.orange.RequestException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

public final class Refund implements Request {
	
	private final BigInteger rma;
	
	private Refund(Builder builder){
		this.rma = builder.rma;
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		try{
		     product.process(this, status);
		} catch (ProductException e){
		     status.setStatusCode(StatusCode.FAIL);
		}
	}
	
	public BigInteger getRma(){
		return new BigInteger(this.rma.toString());
	}
	
	public static class Builder{
		private BigInteger rma;
		
		public Builder setRma(BigInteger rma) throws RequestException{
			if(rma == null){
				throw new RequestException(ErrorCode.INVALID_RMA);
			}
			else{
				this.rma = rma;
			}
			return this;
		}
		
		public BigInteger getRma(){
			return rma;
		}
		
		public Refund build(){
			return new Refund(this);
		}
		
	}

}
