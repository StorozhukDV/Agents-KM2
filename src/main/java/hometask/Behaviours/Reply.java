package hometask.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Reply extends Behaviour {
    private boolean finish = false;
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.or(
                    MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                    MessageTemplate.MatchPerformative(ACLMessage.REJECT_PROPOSAL)
            ),
            MessageTemplate.MatchProtocol("Trade"));

    public Reply(Agent a) {
        super(a);
    }


    @Override
    public void action() {
        ACLMessage trade = myAgent.receive(mt);
        if (trade!= null){
            if(trade.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                log.info("{}: I am the OWNER!! ",myAgent.getName());
                finish = true;
            }
            if(trade.getPerformative() == ACLMessage.REJECT_PROPOSAL){
                log.info("{}: I am not the OWNER :( ",myAgent.getName());
                finish = true;
            }
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
