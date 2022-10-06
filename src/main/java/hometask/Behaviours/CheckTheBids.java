package hometask.Behaviours;

import hometask.CheckTheWinner;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CheckTheBids extends Behaviour {
    private List<AID> receivers;
    private List<ACLMessage> responses = new ArrayList<>();

    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
            MessageTemplate.MatchProtocol("ReturnRate"));

    public CheckTheBids(Agent a,List<AID> receivers) {
        super(a);
        this.receivers = receivers;
    }

    @Override
    public void action() {
        ACLMessage replied = myAgent.receive(mt);
        if(replied != null){
            log.info("i received reply bid from {} with content {}", replied.getSender().getLocalName(), replied.getContent());
            responses.add(replied);
        }
        if(responses.size()==3){
            ACLMessage winner = new CheckTheWinner(responses).theWinner();
            for (AID receiver:receivers){
                myAgent.addBehaviour(new Results(myAgent,receiver,winner));
            }
            responses.clear();
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
