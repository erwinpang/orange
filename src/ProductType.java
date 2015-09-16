
public enum ProductType {
	OPOD	("oPod"),
	OPAD	("oPad"),
	OPHONE	("oPhone"),	
	OWATCH	("oWatch"),
	OTV		("oTv");
	
	private final String name;
	ProductType(String name){
		this.name = name;
	}
	
	public String getName() { 
		return name; 
		}

}
