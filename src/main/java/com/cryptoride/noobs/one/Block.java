package com.cryptoride.noobs.one;

import java.util.Date;

/**
 * 
 * @author Asad Ali
 * 
 *         Definition-
 *         *********************************************************************
 *         Chain of blocks are called as Blockchain. Each block in the
 *         blockchain will have its own digital signature, contain digital
 *         signature of the previous block, and have some data in it. Each block
 *         doesn’t just contain the hash of the block before it, but its own
 *         hash is in part, calculated from the previous hash.
 * 
 *         If the previous block’s data is changed then the previous block’s
 *         hash will change in turn affecting all the hashes of the blocks there
 *         after. Calculating and comparing the hashes allow us to see if a
 *         blockchain is invalid. In short changing any data in this list, will
 *         change the signature and break the chain.
 *
 */
public class Block {

	public String hash;// holds digital signature.
	public String previousHash;// holds the previous block’s hash
	private String data; // data could be a simple message.
	private long timeStamp; // as number of milliseconds since 1/1/1970.

	// Block Constructor.
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
		return SecurityUtils.encript(previousHash + Long.toString(timeStamp) + data);

	}
}