package ekp25.orange;

import java.util.HashMap;
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
	
	@FunctionalInterface
	public interface CreateProduct {
		public Product create(SerialNumber serialNumber,  Optional<Set<String>> description);
	}
	
	//Instantiate Functional Interface CreateProduct
	static CreateProduct createOpod = (serialnum, desc) -> {return new Opod(serialnum, desc); };
	static CreateProduct createOpad = (serialnum, desc) -> {return new Opad(serialnum, desc); };
	static CreateProduct createOwatch = (serialnum, desc) -> {return new Owatch(serialnum, desc); };
	static CreateProduct createOtv = (serialnum, desc) -> {return new Otv(serialnum, desc); };
	static CreateProduct createOphone = (serialnum, desc) -> {return new Ophone(serialnum, desc); };
	
	//Map Product Types to constructors
	static Map<ProductType, CreateProduct> commands = new HashMap<ProductType, CreateProduct>(){{
		put(ProductType.OPOD, createOpod);
		put(ProductType.OPAD, createOpad);
		put(ProductType.OWATCH, createOwatch);
		put(ProductType.OTV, createOtv);
		put(ProductType.OPHONE, createOphone);
	}};
	
	//Functional Interface to check validity of Serial numbers
	@FunctionalInterface
	public interface CheckSerial{
		public boolean isValid(SerialNumber serialNumber);
	}
	
	//Instantiate Functional interface CheckSerial
	static CheckSerial checkOpod = (serialnum) -> {return Opod.isValidSerialNumber(serialnum); };
	static CheckSerial checkOpad = (serialnum) -> {return Opad.isValidSerialNumber(serialnum); };
	static CheckSerial checkOwatch = (serialnum) -> {return Owatch.isValidSerialNumber(serialnum); };
	static CheckSerial checkOtv = (serialnum) -> {return Otv.isValidSerialNumber(serialnum); };
	static CheckSerial checkOphone = (serialnum) -> {return Ophone.isValidSerialNumber(serialnum); };
	
	//Map Product Type to proper serial number checker
	static Map<ProductType, CheckSerial> checkValidity = new HashMap<ProductType, CheckSerial>(){{
		put(ProductType.OPOD, checkOpod);
		put(ProductType.OPAD, checkOpad);
		put(ProductType.OWATCH, checkOwatch);
		put(ProductType.OTV, checkOtv);
		put(ProductType.OPHONE, checkOphone);
	}};
	
	public static Product make(ProductType productType,SerialNumber serialNumber, Optional<Set<String>>
							   description) throws ProductException{
		
		Boolean serialValid = checkValidity.get(productType).isValid(serialNumber);
		
		//Check if Serial Number is Valid
		//Check that product type is not null
		if(!serialValid){
			throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
		}
		else if(commands.get(productType) == null){
			throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_PRODUCT_TYPE);
		}
		
		Product p = (Product) commands.get(productType).create(serialNumber, description); 
		
		return p;
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
	
}
