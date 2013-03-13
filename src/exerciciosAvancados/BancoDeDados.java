package exerciciosAvancados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;


public class BancoDeDados {

	
	public static void main(String[] args) throws InterruptedException {
		Vector<String> sqls = new Vector<String>();
		
		ProduzSQL p1 = new ProduzSQL(0, 10000, sqls);
		Thread t1 = new Thread(p1);
		
		ProduzSQL p2 = new ProduzSQL(10000, 20000, sqls);
		Thread t2 = new Thread(p2);
		
		ProduzSQL p3 = new ProduzSQL(20000, 30000, sqls);
		Thread t3 = new Thread(p3);
		
		t1.start();
		t2.start();
		t3.start();
		
	
		t1.join();
		t2.join();
		t3.join();
		
		
		System.out.println("Thread produtoras de SQLs finalizada");
		
		for(int i = 0; i < 15000;i++){
			if(!sqls.contains("SQL"+i)){
				throw new IllegalStateException("Nao encotrei a SQL"+i);
				
			}
			
		}
		
		if(sqls.contains(null))	{
			throw new IllegalStateException("Nao devia ser nulo aqui dentro");
		}
		
		System.out.println("Fim da execução com sucesso");
		
		
	}

}
