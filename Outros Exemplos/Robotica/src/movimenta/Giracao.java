package movimenta;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import sensores.UltrasonicSensor;



public class Giracao {
	
	public static void girarAcharParede(){
		
		int angulo = - 10;
		
		UltrasonicSensor distanciaParede = new UltrasonicSensor(SensorPort.S4);
		MovimentaRodas movimento = new MovimentaRodas();
		movimento.pontoVirtual(angulo, 0);
		
		int controleGirar = 0;
		
		while(true){
			
			Button.waitForAnyEvent(500);
			
			if( lerDistancia() <= 90 ){
				
				for(int i = 1; i <= 3; i++){	
					LCD.drawInt(lerDistancia(), 1,i);
					Button.waitForAnyEvent(700);
					
				}
				controleGirar = 0;
				break;
			}
			else{
				angulo = angulo - 10;
				movimento.pontoVirtual(angulo, 0);
				controleGirar++;
			}
			if(controleGirar == 4 ) break;
		}
	}

	public static int lerDistancia(){
		
		UltrasonicSensor distanciaParede = new UltrasonicSensor(SensorPort.S4);
		int i = 1;
		int QUANTIDADE_MEDIDAS = 3;
		
		LCD.drawString("Testando a distancia", 0, 0);
		
		for(i = 1; i <= QUANTIDADE_MEDIDAS; i++){	
			Button.waitForAnyEvent(500);
			
		}
		
		return distanciaParede.getDistance();
		
	}	
	
}
