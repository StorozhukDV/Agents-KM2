package hometask.agents;

import hometask.Behaviours.CheckTheBids;
import hometask.Behaviours.SendDelayedBid;
import jade.core.AID;
import jade.core.Agent;

import java.util.ArrayList;
import java.util.List;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class Iniciator extends Agent {
    List<AID> receivers = new ArrayList<>();
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Iniciator.class);

    @Override
    protected void setup() {
        log.info("Starting auction");
        if (this.getLocalName().equals("Iniciator")){
            receivers.add(new AID("Receiver1",false));
            receivers.add(new AID("Receiver2",false));
            receivers.add(new AID("Receiver3",false));
            this.addBehaviour(new CheckTheBids(this,receivers));
            this.addBehaviour(new SendDelayedBid(this,receivers,10000));

        }
    }
}
