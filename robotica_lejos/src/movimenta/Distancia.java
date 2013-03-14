package movimenta;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import sensores.UltrasonicSensor;

public class Distancia {
	
	public static void lerDistanciaParede(){
		
		UltrasonicSensor distanciaParede = new UltrasonicSensor(SensorPort.S4);
		int i = 1;
		int QUANTIDADE_MEDIDAS = 3;
		
		LCD.drawString("Testando a distancia", 0, 0);
		
		for(i = 1; i <= QUANTIDADE_MEDIDAS; i++){	
			LCD.drawInt(distanciaParede.getDistance(), 1,i);
			Button.waitForAnyEvent(500);
			
		}
		
	}

}
