package APP;

public class ExecutaPessoa {

	public static void main(String[] args){
		
		Pessoa pessoa1 = new Pessoa("Nome1", 19, "Masculino");
		Pessoa pessoa2 = new Pessoa("Nome2", 20, "Feminino");
		
		Pessoa.listar();
		
		System.out.println(pessoa1.toString());
		
		
		Pessoa.excluir(pessoa2);
		
		Pessoa.listar();
	
	}
	
}
