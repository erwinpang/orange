package ekp25.orange;

public interface Request {
	
	public void process(Product product, RequestStatus status) throws RequestException;

}
