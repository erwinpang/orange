import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;
import java.util.stream.*;

public abstract class AbstractProduct implements Product {
	
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	public AbstractProduct(SerialNumber serialNumber, Optional<Set<String>> description){
		this.serialNumber = serialNumber;
		this.description = description;
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
	
	public boolean equals(AbstractProduct abstractProduct){
		if(this.serialNumber.equals(abstractProduct.serialNumber)){
			return true;
		}
		else if(this.hashCode() == abstractProduct.hashCode()){
			return true;
		}
		else return false;
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
