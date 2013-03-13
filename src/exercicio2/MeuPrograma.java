package exercicio2;

import exercicio1.Programa;

public class MeuPrograma {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GerarPDF gerapdf = new GerarPDF();
		
		Thread threadDoPdf = new Thread(gerapdf);
		threadDoPdf.start();
		
		BarraDeProgresso barraDoProgresso = new BarraDeProgresso();
		
		Thread threadDaBarra = new Thread(barraDoProgresso);
		threadDaBarra.start();

	}

}
