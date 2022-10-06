package hometask.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Results extends OneShotBehaviour {
    AID receiver;
    ACLMessage winner;

    public Results(Agent a, AID receiver,ACLMessage winner) {
        super(a);
        this.receiver = receiver;
        this.winner = winner;
    }

    @Override
    public void action() {
        if (receiver.equals(winner.getSender())){
            ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
            msg.setProtocol("Trade");
            msg.addReceiver(receiver);
            myAgent.send(msg);
        }
        else{
            ACLMessage msg = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
            msg.setProtocol("Trade");
            msg.addReceiver(receiver);
            myAgent.send(msg);
        }
    }
}
