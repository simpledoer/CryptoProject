package blockchain;

import java.util.logging.Level;
import java.util.logging.Logger;
public class FoodBlockChainTest {
       public static void main(String[] args) {
        CreateBlock ramu = new CreateBlock("ramu rice 13thmarch mirpur","ramu", null);
        CreateBlock somu = new CreateBlock("somu wheat 12thmarch chandigarh","somu", ramu);
        CreateBlock megha = new CreateBlock("megha tomato 13thmarch jaipur","megha", somu);
        CreateBlock veera = new CreateBlock("veera cabbage 14thmarch hyderabad","veera",megha);
        CreateBlock haripal=new CreateBlock("haripal carrot 12thmarch ooty","haripal",veera);
        CreateBlock vishal=new CreateBlock("vishal rice 14thmarch kochi","vishal",haripal);
        CreateBlock aalok=new CreateBlock("aalok Tomato 13thmarch vizag","aalok",vishal);
        CreateBlock[] stores = {ramu, somu,megha,veera,haripal,vishal,aalok};

        for (CreateBlock store : stores) {
            Thread thread = new Thread(store);
            thread.start();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoodBlockChainTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (CreateBlock store : stores) {
            System.out.println("");
            store.printBlockChain();
        }
    }

}