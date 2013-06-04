
package agenteProduto;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteCompradorProduto extends Agent {
	
	private String produtoAlvo;
	private AID[] agentesVendedores;
	
	protected void setup() {
		// Mensagem de bem vindo
		System.out.println("Meu nome"+getAID().getName()+".");

		Object[] args = getArguments();
		
		//arg não pode ser nulo e o tamanho deve ser maior que zero
		if (args != null && args.length > 0) {
			produtoAlvo = (String) args[0];
			System.out.println("O produto alvo é: "+produtoAlvo);

			//Adiciona um TickerBehaviour (comportamento Ticker) que evidencia a solicitação de agentes vendedores a cada minuto
			addBehaviour(new TickerBehaviour(this, 60000) {
				
				protected void onTick() {
					
					System.out.println("Tentando comprar "+produtoAlvo);
					// Update the list of seller agents
					DFAgentDescription modeloAgente = new DFAgentDescription();
					ServiceDescription descricaoServico = new ServiceDescription();
					descricaoServico.setType("Produto vendido!");
					modeloAgente.addServices(descricaoServico);
					
					try {
						DFAgentDescription[] resultado = DFService.search(myAgent, modeloAgente); 
						System.out.println("Foi encontrado os seguintes agentes vendedores:");
						agentesVendedores = new AID[resultado.length];
						
						for (int i = 0; i < resultado.length; ++i) {
							agentesVendedores[i] = resultado[i].getName();
							System.out.println(agentesVendedores[i].getName());
						}
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}

					// Realizando o pedido
					myAgent.addBehaviour(new SolicitarExecucao());
				}
			} );
		}
		else {
			// Fazendo o agente terminar
			System.out.println("Destino do produto não especificado");
			doDelete();
		}
	}

	protected void takeDown() {
		System.out.println("Agente "+getAID().getName()+" finalizado.");
	}

	/**
	   Fazendo uma agregação com a classe SolicitarExecucao.
		Comportamento utilizado por agentes comprador do produto solicitar agentes vendedores para o produto	
	 */
	private class SolicitarExecucao extends Behaviour {
		private AID agenteMelhorOferta;     
		private int melhorPrecoOferecido;  
		private int contadorRespostasAgentesVendedores = 0; 
		private MessageTemplate modeloParaReceberRespostas; 
		private int passo = 0;

		public void action() {
			switch (passo) {
			case 0:
				// Envia o CFP a todos os vendedores
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				for (int i = 0; i < agentesVendedores.length; ++i) {
					cfp.addReceiver(agentesVendedores[i]);
				} 
				cfp.setContent(produtoAlvo);
				cfp.setConversationId("Comercio de produtos");
				cfp.setReplyWith("cfp"+System.currentTimeMillis()); // Valor único
				myAgent.send(cfp);
				// Prepara o modelo para obter respostas
				modeloParaReceberRespostas = MessageTemplate.and(MessageTemplate.MatchConversationId("Comercio de produtos"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				passo = 1;
				break;
			case 1:
				// Recebe todas as propostas de agentes vendedores
				ACLMessage resposta = myAgent.receive(modeloParaReceberRespostas);
				if (resposta != null) {
					
					// Resposta recebida
					if (resposta.getPerformative() == ACLMessage.PROPOSE) {
						// Evidencia uma oferta! 
						int price = Integer.parseInt(resposta.getContent());
						if (agenteMelhorOferta == null || price < melhorPrecoOferecido) {
							// This is the best offer at present
							melhorPrecoOferecido = price;
							agenteMelhorOferta = resposta.getSender();
						}
					}
					contadorRespostasAgentesVendedores++;
					if (contadorRespostasAgentesVendedores >= agentesVendedores.length) {
						// Recebeu-se todas as respostas
						passo = 2; 
					}
				}
				else {
					block();
				}
				break;
			case 2:
				// Envia ordem de compra para o vendedor que ofereceu a melhor oferta
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(agenteMelhorOferta);
				order.setContent(produtoAlvo);
				order.setConversationId("book-trade");
				order.setReplyWith("order"+System.currentTimeMillis());
				myAgent.send(order);
				// Prepara o modelo para receber a resposta de ordem de compra
				modeloParaReceberRespostas = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				passo = 3;
				break;
				
			case 3:      
				// Recebe a ordem de resposta de compra
				resposta = myAgent.receive(modeloParaReceberRespostas);
				if (resposta != null) {
					//Resposta de ordem de compra recebida
					if (resposta.getPerformative() == ACLMessage.INFORM) {
						// Purchase successful. We can terminate
						System.out.println(produtoAlvo+" agente comprou com sucesso"+resposta.getSender().getName());
						System.out.println("Price = "+melhorPrecoOferecido);
						myAgent.doDelete();
					}
					else {
						System.out.println("Tentativa fracassada! Produto já foi vendido!!!");
					}

					passo = 4;
				}
				else {
					block();
				}
				break;
			}        
		}

		public boolean done() {
			if (passo == 2 && agenteMelhorOferta == null) {
				System.out.println("Tentativa fracassada: "+produtoAlvo+" produto não disponivel para venda");
			}
			return ((passo == 2 && agenteMelhorOferta == null) || passo == 4);
		}
		
	}  
}
