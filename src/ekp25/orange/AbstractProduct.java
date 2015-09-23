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
	
	@FunctionalInterface
	public interface CheckSerial{
		public boolean isValid(SerialNumber serialNumber);
	}
	
	public static Product make(ProductType productType,SerialNumber serialNumber, Optional<Set<String>>
							   description) throws ProductException{
		Map<ProductType, CreateProduct> commands = new HashMap<>();

		//Move these into a separate function
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
		Product p = (Product) commands.get(productType).create(serialNumber, description); //move this
		
		Map<ProductType, CheckSerial> checkValidity = new HashMap<>();
		
		CheckSerial checkOpod = (serialnum) -> {return Opod.isValidSerialNumber(serialnum); };
		CheckSerial checkOpad = (serialnum) -> {return Opad.isValidSerialNumber(serialnum); };
		CheckSerial checkOwatch = (serialnum) -> {return Owatch.isValidSerialNumber(serialnum); };
		CheckSerial checkOtv = (serialnum) -> {return Otv.isValidSerialNumber(serialnum); };
		CheckSerial checkOphone = (serialnum) -> {return Ophone.isValidSerialNumber(serialnum); };
		
		checkValidity.put(ProductType.OPOD, checkOpod);
		checkValidity.put(ProductType.OPAD, checkOpad);
		checkValidity.put(ProductType.OWATCH, checkOwatch);
		checkValidity.put(ProductType.OTV, checkOtv);
		checkValidity.put(ProductType.OPHONE, checkOphone);

		
		Boolean serialValid = checkValidity.get(productType).isValid(serialNumber);
		if(!serialValid){
			throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
		}
		//check for null
		
		
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
