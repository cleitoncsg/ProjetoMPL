
package agenteProduto;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.*;

public class AgenteVendedorProduto extends Agent {
	
	private Hashtable catalogo;
	private VenderProdutoGUI meuGuia;

	protected void setup() {
		
		catalogo = new Hashtable();

		meuGuia = new VenderProdutoGUI(this);
		meuGuia.showGui();

		// Registre o serviço seller nas páginas amarelas
		DFAgentDescription modeloAgente = new DFAgentDescription();
		modeloAgente.setName(getAID());
		ServiceDescription descricaoServico = new ServiceDescription();
		descricaoServico.setType("Produto vendido");
		descricaoServico.setName("Comercio de produtos - JADE");
		modeloAgente.addServices(descricaoServico);
		
		try {
			DFService.register(this, modeloAgente);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Adicione o comportamento servindo consultas de agentes do comprador
		addBehaviour(new OfertaDePedidosServer());

		// Adicione o comportamento servindo ordens de compra de agentes do comprador
		addBehaviour(new OrdensDeCompraServer());
	}

	// Agente de operações de limpeza
	protected void takeDown() {
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		// Fecha o guia
		meuGuia.dispose();
		System.out.println("Agente "+getAID().getName()+" finalizado.");
	}


	public void atualizaCatalogo(final String titulo, final int preco) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				catalogo.put(titulo, new Integer(preco));
				System.out.println(titulo+" inserido no catálogo. Preco = "+preco);
			}
		} );
	}

	/**
	    Agregação - OfertaDePedidosServer
		Este é o comportamento utilizado por agentes vendedores para atender as solicitações recebidas
		para a oferta de agentes do comprador.
		Se o produto solicitado está no catálogo local o agente vendedor responde:
		com uma mensagem PROPOR especificando o preço. Caso contrário, uma mensagem de recusa é
		enviado de volta.
	 */
	private class OfertaDePedidosServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mensagemTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mensagemTemplate);
			if (msg != null) {
				// Mensagem recebida e pode ser processada
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();

				Integer preco = (Integer) catalogo.get(title);
				if (preco != null) {
					// O produto solicitado está disponível para venda. Responder com o preço
					reply.setPerformative(ACLMessage.PROPOSE);
					reply.setContent(String.valueOf(preco.intValue()));
				}
				else {
					// Produto solicitado não disponivel para venda
					reply.setPerformative(ACLMessage.REFUSE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}  

	/**
	   Inner class OrdensDeCompraServer.
	   Este é o comportamento utilizado por agentes Book-seller para servir de entrada
	   oferecem aceitações (ou seja, as ordens de compra) de agentes do comprador.
	   O agente vendedor remove o produto comprado a partir do seu catálogo
	   e responde com uma mensagem de INFORM para notificar o comprador de que o
	   compra foi sucesfully concluída.
	 */
	private class OrdensDeCompraServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mensagemTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
			ACLMessage msg = myAgent.receive(mensagemTemplate);
			if (msg != null) {
				// Mensagem recebida com sucesso
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();

				Integer price = (Integer) catalogo.remove(title);
				if (price != null) {
					reply.setPerformative(ACLMessage.INFORM);
					System.out.println(title+" sold to agent "+msg.getSender().getName());
				}
				else {
					// Produto solicitado foi vendido para outro comprador
					reply.setPerformative(ACLMessage.FAILURE);
					reply.setContent("not-available");
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}  
}

