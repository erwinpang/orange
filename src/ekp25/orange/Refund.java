package ekp25.orange;

import java.math.BigInteger;

import ekp25.orange.RequestException.ErrorCode;

public final class Refund implements Request {
	
	private final BigInteger rma;
	
	private Refund(Builder builder){
		this.rma = builder.rma;
	}

	@Override
	public void process(Product product, RequestStatus status) throws RequestException {
		if(status.getStatusCode() == RequestStatus.StatusCode.OK){
			new Refund.Builder().setRma(null).build();
		}
		else{
			throw new RequestException(ErrorCode.INVALID_REQUEST);
		}
	}
	
	public BigInteger getRma(){
		return rma;
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
