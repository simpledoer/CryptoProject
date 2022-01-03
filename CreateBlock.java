package blockchain;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CreateBlock extends ProofOfWork implements Runnable {

    public CreateBlock(String address,String customerName, ProofOfWork connectedNode) {
        super(address, connectedNode);
    }

    public void testOrders() throws InterruptedException {
        String[] customerName = {"ramu", "sidha", "megha","veera","haripal","vishal","aalok"};
        String[] orderDetails = {"rice", "wheat", "tomato","cabbage","carrot","rice","tomato"};
        String[] dateOfOrder  ={"12thmarch","13thmarch","12thmarch","14thmarch","12thmarch","14thmarch","13thmarch"};
        String[] placeOfOrder=  {"mirpur","chandigarh","jaipur","hyderabad","ooty","kochi","vizag"};
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            Block block = new Block(
                    new ContentBlock(customerName[random.nextInt(6)], orderDetails[random.nextInt(6)],dateOfOrder[random.nextInt(6)],placeOfOrder[random.nextInt(6)], random.nextInt(50)),
                    this.blockChain.getLastHash()
            );
            try {
                this.mine(block);
                System.out.println(String.format("%s mined: %s", address, block.hash));
            } catch (IllegalArgumentException ex) {
                System.out.println(String.format("%s mined invalid block: %s", address, block.hash));
            }
            Thread.sleep(random.nextInt(4) * 1000 + 100);
        }
    }

    public void printBlockChain() {
        System.out.println("------ Block chain of [" + address + "] ------");

        this.blockChain.getBlocks().forEach(block -> {
            System.out.println(block.hash);
        });
        

        System.out.println("------ END OF BLOCK ------");
    }

    @Override
    public void run() {
        try {
            testOrders();
        } catch (InterruptedException ex) {
            Logger.getLogger(CreateBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}