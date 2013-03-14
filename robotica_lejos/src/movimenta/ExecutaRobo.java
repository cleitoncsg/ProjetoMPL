package movimenta;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import sensores.LightSensor;
import sensores.UltrasonicSensor;

public class ExecutaRobo {
	
	
	public static void main(String[] args) {
		
		Button.waitForAnyEvent(2000);
		
		Distancia distancia = new Distancia();
		Giracao girar = new Giracao();
		MovimentaRodas movimento = new MovimentaRodas();
		DifferentialPilot pilot = new DifferentialPilot(2.15f, 4.31f, Motor.C,Motor.B,true);
		
		Button.waitForAnyEvent(100);
		movimento.largada();
		
		Button.waitForAnyEvent(100);
		movimento.ajuste(medeDistanciaObstaculos(), 92);
		
		Button.waitForAnyEvent(100);
		movimento.pontoVirtual(25,-36);
			
		
		Button.waitForAnyEvent(100);
		movimento.pontoVirtual(115,-40);
		
	
		Button.waitForAnyEvent(100);
		girar.girarAcharParede();
		
		Button.waitForAnyEvent(100);
		//movimento.ajuste(girar.lerDistancia(), 76);
		
		Button.waitForAnyEvent(100);
		//primeiro parametro é angulo 
		movimento.pontoVirtual(-70, -35);
		
		Button.waitForAnyEvent(100);
		movimento.pontoVirtual(-115,-73);
		
		Button.waitForAnyEvent(500);
		pilot.setTravelSpeed(7);
		Button.waitForAnyEvent(100);
		movimento.pontoVirtual(119,-60);
	
	}
	
	public static int medeDistanciaObstaculos(){
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
