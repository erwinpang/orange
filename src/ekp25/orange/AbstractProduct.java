package ekp25.orange;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractProduct implements Product {
	
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	
	public AbstractProduct(SerialNumber serialNumber, Optional<Set<String>> description){
		this.serialNumber = serialNumber;
		this.description = description;
	}
	
	public interface CreateProduct {
		public Product create(SerialNumber serialNumber,  Optional<Set<String>> description);
	}
	
	public static Product make(ProductType productType,SerialNumber serialNumber, Optional<Set<String>>
							   description) throws ProductException{
		Map<ProductType, CreateProduct> commands = new HashMap<>();

		CreateProduct createOpod = (serialnum, desc) -> {return new Opod(serialnum, desc); };
		CreateProduct createOpad = (serialnum, desc) -> {return new Opad(serialnum, desc); };
		CreateProduct createOwatch = (serialnum, desc) -> {return new Owatch(serialnum, desc); };
		CreateProduct createOtv = (serialnum, desc) -> {return new Otv(serialnum, desc); };
		CreateProduct createOphone = (serialnum, desc) -> {return new Ophone(serialnum, desc); };
		
		commands.put(ProductType.OPOD, createOpod);
		commands.put(ProductType.OPAD, createOpad);
		commands.put(ProductType.OWATCH, createOwatch);
		commands.put(ProductType.OTV, createOtv);
		commands.put(ProductType.OPHONE, createOphone);
		Product p = (Product) commands.get(ProductType.OPOD).create(serialNumber, description);
		
		return p;
		
//		switch (productType){
//			case OPOD:
//				if(Opod.isValidSerialNumber(serialNumber)){
//					return new Opod(serialNumber, description);
//				}
//				else{
//					throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
//				}
//			case OPAD:
//				if(Opad.isValidSerialNumber(serialNumber)){
//					return new Opad(serialNumber, description);
//				}
//				else{
//					throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
//				}
//			case OWATCH:
//				if(Owatch.isValidSerialNumber(serialNumber)){
//					return new Owatch(serialNumber, description);
//				}
//				else{
//					throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
//				}
//			case OTV:
//				if(Otv.isValidSerialNumber(serialNumber)){
//					return new Otv(serialNumber, description);
//				}
//				else{
//					throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
//				}
//			case OPHONE:
//				if(Ophone.isValidSerialNumber(serialNumber)){
//					return new Ophone(serialNumber, description);
//				}
//				else{
//					throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
//				}
//			default: 
//				throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_PRODUCT_TYPE);
//		}
		
		
	}
	
	
	public SerialNumber getSerialNumber(){
		return this.serialNumber;
	}
	
	public String getProductName(){
		return getProductType().getName();
	}
	
	public Optional<Set<String>> getDescription(){
		return description;
	}
	
	public boolean equals(Object object){
		if(object instanceof AbstractProduct) {
			return ((AbstractProduct) object).serialNumber.equals(this.serialNumber);
		}
		else{
			return equals(object);
		}
	}
	
	@Override
	public int hashCode(){
		return this.serialNumber.hashCode();
	}
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		info.append(this.getProductName() + " ");
		info.append(this.serialNumber.getSerialNumber() + " ");
		if(description.isPresent()){
			description.get().stream().map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
									  .forEach(w -> info.append(w).append(" "));
		}
		return info.toString();
	}
	


	public static void main(String[] args) throws ProductException{

		BigInteger b = new BigInteger("54736");
		SerialNumber sn = new SerialNumber(b);
		Set<String> s = new HashSet<String>();
		s.add("this is an opod");
		s.add("not a owatch");
		Optional<Set<String>> description = Optional.of(s);
		Product o = make(ProductType.OPOD, sn, description );
	}

}
