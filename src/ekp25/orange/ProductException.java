package ekp25.orange;

public class ProductException extends Exception {
	
	ProductType productType;
	SerialNumber serialNumber;
	ErrorCode errorCode;
	
	public enum ErrorCode{
		INVALID_SERIAL_NUMBER,
		INVALID_PRODUCT_TYPE,
		UNSUPPORTED_OPERATION;
	}
	
	public ProductException(ProductType productType, SerialNumber serialNumber, ErrorCode errorCode){
		this.productType = productType;
		this.serialNumber = serialNumber;
		this.errorCode = errorCode;
	}
	
	ProductType getProductType(){
		return this.productType;
	}
	
	public String getProductName(){
		return this.getProductType().getName();
	}
	
	public SerialNumber getSerialNumber(){
		return this.serialNumber;
	}
	
	public ErrorCode getErrorCode(){
		return this.errorCode;
	}

}
