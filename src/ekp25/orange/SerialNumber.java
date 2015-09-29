package ekp25.orange;

import java.math.BigInteger;

public class SerialNumber implements Comparable {
	
	private BigInteger serialNumber;
	
	public SerialNumber(BigInteger serialNumber){
		this.serialNumber = serialNumber;
	}
	
	public BigInteger getSerialNumber(){
		return serialNumber;
	}
	
	//Return the greatest common divisor of this SerialNumber
	//and the other SerialNumber
	public BigInteger gcd(SerialNumber other){
		return serialNumber.gcd(other.serialNumber);
	}
	
	//Return this SerialNumber mod the other SerialNumber
	public BigInteger mod(SerialNumber other){
		return serialNumber.mod(other.serialNumber);
	}
	
	public boolean testBit(int bit){
		return serialNumber.testBit(bit);
	}
	
	public boolean isEven(){
		return !serialNumber.testBit(0);
	}
	
	public boolean isOdd(){
		return serialNumber.testBit(0);
	}

	public int compareTo(SerialNumber other) {
		if(this.getSerialNumber().equals(other)){
			return 0;
		}
		else if(this.getSerialNumber().compareTo(other.serialNumber) == -1){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	@Override
	public int compareTo(Object o){
		if(o instanceof SerialNumber){
			return this.compareTo((SerialNumber) o);
		}
		else return this.compareTo(o);
	}

}
