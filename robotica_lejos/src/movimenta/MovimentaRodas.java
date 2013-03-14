package movimenta;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.*;
import lejos.util.Delay;

import java.util.*;

public class MovimentaRodas {
	
	private int distanciaAjuste;
	private int angulo, distancia;
	private int distanciaCorrige;
	
	public void largada(){
		DifferentialPilot pilot = new DifferentialPilot(2.15f, 4.31f, Motor.C,Motor.B,true);
		pilot.setTravelSpeed(3);
		pilot.rotate(20.0);
		pilot.travel(-36);
		Delay.msDelay(10);
	}
	
	public void ajuste(int distanciaAjuste, int distanciaCorrige){
		
		DifferentialPilot pilot = new DifferentialPilot(2.15f, 4.31f, Motor.C,Motor.B,true);
		if(distanciaAjuste > distanciaCorrige){
			pilot.setTravelSpeed(3);
			pilot.travel((-1)*(distanciaAjuste - distanciaCorrige));
			Delay.msDelay(10);
			
		}
		else{
			pilot.setTravelSpeed(3);
			pilot.travel((-1)* (distanciaCorrige - distanciaAjuste));
			Delay.msDelay(10);
		}
			
	}
	
	public void pontoVirtual(int angulo, int distancia){
		DifferentialPilot pilot = new DifferentialPilot(2.15f, 4.31f, Motor.C,Motor.B,true);
		pilot.setTravelSpeed(7);
		pilot.rotate(angulo);
		pilot.travel(distancia);
		Delay.msDelay(10);
	}
	
	

}
