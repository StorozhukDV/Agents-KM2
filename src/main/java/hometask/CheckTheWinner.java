package hometask;

import jade.lang.acl.ACLMessage;
import lombok.Data;

import java.util.List;
import java.util.Vector;

public class CheckTheWinner {
    private final List<ACLMessage> responses;
    double minPrice = -1;
    ACLMessage bestProposal = null;
    public CheckTheWinner(List<ACLMessage> responses){
        this.responses = responses;
    }
    public ACLMessage theWinner(){
        for (ACLMessage response:responses){
            double proposedPrice = Double.parseDouble(response.getContent());
            if (minPrice < 0){
                minPrice = proposedPrice;
                bestProposal = response;
            }
            else{
                if (minPrice > proposedPrice){
                    minPrice = proposedPrice;
                    bestProposal = response;
                }
            }
        }
        return bestProposal;
    }
}
