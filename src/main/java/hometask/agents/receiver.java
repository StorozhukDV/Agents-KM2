package hometask.agents;

import hometask.Behaviours.StartBargagining;
import jade.core.Agent;

public class receiver extends Agent {

    @Override
    public void setup(){
        this.addBehaviour(new StartBargagining());
    }
}
