package com.cryptoride.noobs.one;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * 
 * @author Asad Ali
 * 
 *         ******************************************************************
 *         Let's create some blocks and print the hashes to the screen to see
 *         that everything is in working order.
 *
 */
public class BlockChain {

	public static List<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {
		/**
		 * The first block in a blockchain is called the genesis block, and
		 * because there is no previous block we will just enter “0” as the
		 * previous hash.
		 */
		Block genesisBlock = new Block("First block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		blockchain.add(genesisBlock);
		Block secondBlock = new Block("Second block", genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		blockchain.add(secondBlock);
		Block thirdBlock = new Block("Third block", secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
		blockchain.add(thirdBlock);
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
		System.out.println(isValidChain());
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
		}
		return true;

	}
}
