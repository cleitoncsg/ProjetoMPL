import java.util.Calendar;


public final class Candlestick {
	
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candlestick(double abertura,double fechamento,double minimo,	double maximo, double volume, Calendar data) {
		
		minimoMaiorMaximo();
		dataNula();
		demaisValidacoes();
		
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
		
	}
	
	public void minimoMaiorMaximo(){
		if(minimo > maximo)
			throw new IllegalArgumentException("Minimo nao deve ser maior que maximo");
	}
	
	public void dataNula(){
		if(data == null)
			throw new IllegalArgumentException("Data nao pode ser nula");
	}
	
	public demaisValidacoes(){
		if(abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0 || volume < 0)
			throw new IllegalArgumentException("Atributo nao pode ser negativo");
		
	}
	public double getAbertura() {
		return abertura;
	}
	
	public double getFechamento() {
		return fechamento;
	}
	
	public double getMinimo() {
		return minimo;
	}
	
	public double getMaximo() {
		return maximo;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public Calendar getData() {
		return data;
	}
}
