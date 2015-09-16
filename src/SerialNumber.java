import java.math.BigInteger;

public class SerialNumber {
	
	BigInteger serialNumber;
	
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

}
