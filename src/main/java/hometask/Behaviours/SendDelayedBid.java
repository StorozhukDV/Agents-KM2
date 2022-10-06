package hometask.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendDelayedBid extends TickerBehaviour {
    private List<AID> receivers;
    private boolean finish;
    public SendDelayedBid(Agent a, List<AID> receivers,long period) {
        super(a,period);
        this.receivers = receivers;
    }


    @Override
    public void onTick() {
        for(AID receiver:receivers){
            ACLMessage msg = new ACLMessage(ACLMessage.CFP);
            msg.setContent("200");
            msg.setProtocol("Trade");
            msg.addReceiver(receiver);
            myAgent.send(msg);
        }
    }
}
