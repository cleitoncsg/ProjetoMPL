import java.util.Calendar;
import org.junit.Test;


public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {
		
		new Candlestick(10, 20, 20, 10, 10000, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void fechamentoNaoPodeTerValorNegativo() {
		
		new Candlestick(10, -1, 20, 10, 10000, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void maximoNaoPodeTerValorNegativo() {
		
		new Candlestick(10, 20, 20, -1, 10000, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void minimoNaoPodeTerValorNegativo() {
		
		new Candlestick(10, 20, -1, 10, 10000, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void volumeNaoPodeTerValorNegativo() {
		
		new Candlestick(10, 20, 20, 10, -1, null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void aberturaNaoPodeTerValorNegativo() {
		
		new Candlestick(-1, 20, 20, 10, 10000, null);
		
	}
}
