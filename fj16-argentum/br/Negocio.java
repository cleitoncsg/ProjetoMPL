import java.util.Calendar;

public final class Negocio {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		
		validaData();
		
		this.quantidade = quantidade;
		this.data = data;
		this.preco = preco;
	}
	
	public void validaData(){
		
		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}
	}
	
	public double getPreco() {
		return preco;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
		
	public double getVolume() {
		return preco * quantidade;
	}

}
