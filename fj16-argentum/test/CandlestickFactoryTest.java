import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegocios() {
		Calendar hoje = Calendar.getInstance();
	
		Negocio negocio1 = new Negocio(40.5,100,hoje);
		Negocio negocio2 = new Negocio(45.0,100,hoje);
		Negocio negocio3 = new Negocio(39.8,100,hoje);
		Negocio negocio4 = new Negocio(42.3,100,hoje);
	
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3,negocio4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
	
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
		
	}
	
	@Test
	public void semNegociosGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();	
		
		List<Negocio> negocios = Arrays.asList();	
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);	
		
		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
		Assert.assertEquals(0.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(0.0, candle.getMinimo(), 0.00001);
		Assert.assertEquals(0.0, candle.getFechamento(), 0.00001);
		Assert.assertEquals(0.0, candle.getAbertura(), 0.00001);
	}
	
	@Test
	public void apenasUmNegocioGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
	
		List<Negocio> negocios = Arrays.asList(negocio1);
	
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}

	
	@Test
	public void negociosEmOrdemCrescenteDeValor() {
		
        Negocio primeiro = new Negocio(200, 1, Calendar.getInstance());
		Negocio segundo = new Negocio(100, 2, Calendar.getInstance());

		List<Negocio> negocios = Arrays.asList(segundo, primeiro);
		List<Negocio> negocios2 = Arrays.asList(primeiro, segundo);
		CandlestickFactory fabrica = new CandlestickFactory();
		negocios2 = fabrica.ordernarListaDeNegocioCrescente(negocios2);

		Assert.assertEquals(negocios2, negocios);
            
		
	}
	
	@Test
	public void negociosEmOrdemDecrescenteDeValor() {
		
		Negocio primeiro = new Negocio(200, 1, Calendar.getInstance());
		Negocio segundo = new Negocio(100, 2, Calendar.getInstance());

		List<Negocio> negocios2 = Arrays.asList(primeiro, segundo);
		List<Negocio> negocios = Arrays.asList(primeiro, segundo);

		CandlestickFactory fabrica = new CandlestickFactory();
		negocios2 = fabrica.ordernarListaDeNegocioDecrescente(negocios2);

		Assert.assertEquals(negocios2, negocios);
	}

	
}
