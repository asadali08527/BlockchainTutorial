package com.cryptoride.noobs.two;

import java.util.Date;

/**
 * 
 * @author Asad Ali
 * 
 *         Mining blocks !!!
 *         ******************************************************************
 *         Miners do proof-of-work by trying different variable values in the
 *         block until its hash starts with a certain number of 0’s.
 * 
 *         Proof of work.
 *         *******************************************************************
 *         The hashcash proof of work system means it takes considerable time
 *         and computational power to create new blocks. Hence the attacker
 *         would need more computational power than the rest of the peers
 *         combined.
 *
 */
public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	/*
	 * Lets add an int called nonce to be included in our calculateHash()
	 * method, and the much needed mineBlock() method
	 */
	private int nonce;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	/**
	 * 
	 * @return 
	 *         ******************************************************************
	 *         Now lets use our encrypt helper, in a new method in the Block
	 *         class, to calculate the hash. We must calculate the hash from all
	 *         parts of the block we don’t want to be tampered with. So for our
	 *         block we will include the previousHash, the data and timeStamp.
	 */
	public String calculateHash() {
		return SecurityUtils.encript(Integer.toString(nonce) + previousHash + Long.toString(timeStamp) + data);

	}

	/**
	 * 
	 * @param difficulty
	 * 
	 *            The mineBlock() method takes in an int called difficulty, this
	 *            is the number of 0’s they must solve for. Low difficulty like
	 *            1 or 2 can be solved nearly instantly on most computers, i’d
	 *            suggest something around 4–6 for testing. At the time of
	 *            writing Litecoin’s difficulty is around 442,592.
	 */
	public void mineBlock(int difficulty) {
		// Create a string with difficulty * "0"
		String target = new String(new char[difficulty]).replace('\0', '0');
		/*
		 * In reality each miner will start iterating from a random point. Some
		 * miners may even try random numbers for nonce. Also it’s worth noting
		 * that at the harder difficulties solutions may require more than
		 * integer.MAX_VALUE, miners can then try changing the timestamp.
		 */
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}