package com.cryptoride.noobs.two;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * 
 * @author Asad Ali
 * 
 *         ******************************************************************
 *         Lets add the difficulty as a static variable to the BlockChain class
 *         :
 *
 */
public class BlockChain {

	public static List<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		blockchain.add(new Block("First block", "0"));
		System.out.println("Mining block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Mining block 2... ");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Mining block 3... ");
		blockchain.get(2).mineBlock(difficulty);

		System.out.println("Blockchain is Valid: " + isValidChain());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}

	/**
	 * 
	 * @return
	 * 
	 *         Below method will check integrity of the block chain. That will
	 *         loop through all blocks in the chain and compare the hashes. This
	 *         method will need to check the hash variable is actually equal to
	 *         the calculated hash, and the previous block’s hash is equal to
	 *         the previousHash variable. Any change to the blockchain’s blocks
	 *         will cause this method to return false.
	 */
	public static boolean isValidChain() {
		Block currentBlock;
		Block previousBlock;
		String targetHash = new String(new char[difficulty]).replace('\0', '0');
		/*
		 * On the bitcoin network nodes share their blockchains and the longest
		 * valid chain is accepted by the network. What’s to stop someone
		 * tampering with data in an old block then creating a whole new longer
		 * blockchain and presenting that to the network ?
		 * ********************************************************************
		 * Proof of work.
		 * ********************************************************************
		 * The hashcash proof of work system means it takes considerable time
		 * and computational power to create new blocks. Hence the attacker
		 * would need more computational power than the rest of the peers
		 * combined.
		 */
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			// check if hash is solved
			if (!currentBlock.hash.substring(0, difficulty).equals(targetHash)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;

	}
}
