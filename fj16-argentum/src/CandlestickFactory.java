import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		
		double maximo;
		double minimo;
		double volume;
		double abertura;
		double fechamento;
		
		if(negocios.isEmpty()){
			
			maximo = 0.0;
			minimo = 0.0;
			volume = 0.0;
			abertura = 0.0;
			fechamento = 0.0;
			
			return new Candlestick(abertura, fechamento, minimo, maximo,volume, data);
		}
		
		maximo = negocios.get(0).getPreco();
		minimo = negocios.get(0).getPreco();
		volume = 0.0;
		
		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();		
			if (negocio.getPreco() > maximo) {
				maximo = negocio.getPreco();
			} else if (negocio.getPreco() < minimo) {
				minimo = negocio.getPreco();
			}			
		}
	
		abertura = negocios.get(0).getPreco();
		fechamento = negocios.get(negocios.size()-1).getPreco();
		
		return new Candlestick(abertura, fechamento, minimo, maximo,volume, data);
	
	}
        
        public List <Negocio> ordernarListaDeNegocioCrescente(List<Negocio> listaNegocio){
		alternarPorValorCrescente(listaNegocio);
		return listaNegocio;
	}

	private void alternarPorValorCrescente(List<Negocio> listaNegocio) {
		Collections.sort(listaNegocio, new Comparator(){
			public int compare(Object o1, Object o2){
				Negocio negocio1 = (Negocio) o1;
				Negocio negocio2 = (Negocio) o2;
				return negocio1.getPreco()<negocio2.getPreco()? -1 : (negocio1.getPreco()>negocio2.getPreco()?+1 : 0);
			}
		});
	}

	public List <Negocio> ordernarListaDeNegocioDecrescente(List<Negocio> listaNegocio){
		alternarPorValorDecrescente(listaNegocio);
		return listaNegocio;
	}

	private void alternarPorValorDecrescente(List<Negocio> listaNegocio) {
		Collections.sort(listaNegocio, new Comparator(){
			public int compare(Object o1, Object o2){
				Negocio negocio1 = (Negocio) o1;
				Negocio negocio2 = (Negocio) o2;
				return negocio1.getPreco()>negocio2.getPreco()? -1 : (negocio1.getPreco()<negocio2.getPreco()?+1 : 0);
			}
		});
	}

}
