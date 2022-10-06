package hometask.Behaviours;

import hometask.Behaviours.Reply;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartBargagining extends Behaviour {
    private MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.CFP),
            MessageTemplate.MatchProtocol("Trade"));

    @Override
    public void action() {
        ACLMessage trade = myAgent.receive(mt);
        if (trade!= null){
            log.info("I received offer from {}", trade.getSender().getLocalName());
            double Amount = Double.parseDouble(String.valueOf(trade.getContent()));
            final int min = (int) Amount; // Минимальное число для диапазона
            final int max = (int) (Amount*6); // Максимальное число для диапазона
            final int rnd = rnd(min, max);
            ACLMessage bid = trade.createReply();
            bid.setPerformative(ACLMessage.PROPOSE);
            bid.setProtocol("ReturnRate");
            bid.setContent(String.valueOf(rnd));
            myAgent.send(bid);
            myAgent.addBehaviour(new Reply(myAgent));

        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    /**
     * Метод получения псевдослучайного целого числа от min до max (включая max);
     */
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
