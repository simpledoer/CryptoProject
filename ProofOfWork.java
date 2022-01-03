package blockchain;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProofOfWork {
    public String address;

    protected BlockChain blockChain;
    private ArrayList<ProofOfWork> nodes;

    public ProofOfWork(String address, ProofOfWork connectedNode) {
        this.address = address;
        blockChain = new BlockChain();

        if (connectedNode != null) {
            registerToNetwork(connectedNode);
            try {
                getBlockChain(connectedNode);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ProofOfWork.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            nodes = new ArrayList<>();
        }
    }

    public void add(Block block) {
        try {
            this.blockChain.add(block);
        } catch (IllegalArgumentException e) {
            if (this.nodes.size() > 0) {
                try {
                    getBlockChain(this.nodes.get(0));
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ProofOfWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void mine(Block block) {
        this.blockChain.add(block);
        this.nodes.forEach((node) -> {
            try {
                node.add((Block) block.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ProofOfWork.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void registerToNetwork(ProofOfWork connectedNode) {
        this.nodes = connectedNode.getNodes();
            this.nodes.add(connectedNode);
            this.nodes.forEach((node) -> {
                node.register(this);
            });
    }

    private void getBlockChain(ProofOfWork connectedNode) throws CloneNotSupportedException {
        this.blockChain = (BlockChain) connectedNode.blockChain.clone();
    }

    public ArrayList<ProofOfWork> getNodes() {
        return (ArrayList<ProofOfWork>) this.nodes.clone();
    }

    public void register(ProofOfWork node) {
        this.nodes.add(node);
    }
}