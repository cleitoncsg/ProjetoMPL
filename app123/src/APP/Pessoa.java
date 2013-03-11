package APP;

import java.util.ArrayList;

public class Pessoa {

	public static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private String nome;
	private int idade;
	private String sexo;

	public Pessoa(){
		
	}
	
	public Pessoa(String nome, int idade, String sexo) {
		cadastrar(nome, idade, sexo);
	}

	public static void listar() {
		for(int i = 0; i<pessoas.size();i++){
			System.out.println(pessoas.get(i).getNome() +" " +pessoas.get(i).getIdade() + " "+pessoas.get(i).getSexo());
		}
	}

	public void cadastrar(String nome, int idade, String sexo) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		
		pessoas.add(this);
		
	}

	public static void excluir(Pessoa pessoa) {
		pessoas.remove(pessoa);
	}
	
	


	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + ", sexo=" + sexo
				+ "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
