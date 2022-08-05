package Midterm_Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwitterUser implements Comparable<TwitterUser>, Cloneable {
	private int ID;
	private ArrayList<TwitterUser> TwitterFollowers = new ArrayList<TwitterUser>();
	private ArrayList<TwitterUser> TwitterFollowing = new ArrayList<TwitterUser>();
	private ArrayList<TwitterUser> TwtNeighborhood = new ArrayList<TwitterUser>();

	public TwitterUser(int ID) {
		this.ID = ID; // This sets the ID to the instanced variable of the current class.
	}

	public int getID() {
		return this.ID; // This returns the ID.
	}

	public void clearFollow() {
		TwitterFollowers.clear(); // This clears the Twitter Followers ArrayList.
	}

	public void setID(int id) {
		this.ID = id; // This sets the ID to the instanced variable of the current class.
	}

	public void addFollower(TwitterUser Follower) {
		if (!TwitterFollowers.contains(Follower)) // If TwitterFollowers doesn't contain the Follower...
			TwitterFollowers.add(Follower); // This adds the Follower since they would be missing from the
											// TwitterFollowers.

	}

	public void addFollowing(TwitterUser Follower) {
		if (!TwitterFollowing.contains(Follower)) // If TwitterFollowing doesn't contain Follower...
			TwitterFollowing.add(Follower); // Add the Follower to it.
	}

	public ArrayList<TwitterUser> getFollowing() {
		return new ArrayList<TwitterUser>(TwitterFollowing); // Returns the TwitterUser's followers.
	}

	public ArrayList<TwitterUser> getFollowers() { // This sets an ArrayList for the Twitter User's Followers that they
													// have.
		return new ArrayList<TwitterUser>(TwitterFollowers); // This returns a new ArrayList for the TwitterUser that is
																// comprised of the followers they had from the File.

	}

	public ArrayList<TwitterUser> getNeighborhood(int TwtDepth) { // This sets an ArrayList of the TwtDepth.
		this.TwtNeighborhood = new ArrayList<TwitterUser>(); // Sets up an ArrayList of TwitterUser.
		getNeighborhoodFor(this, TwtDepth, 0); // This called getNeighborhoodFor for this current User with a current
												// depth of 0.
		return new ArrayList<TwitterUser>(this.TwtNeighborhood); // This returns the ArrayList of the User's
																	// Neighborhood of Followers.
	}

	public void getNeighborhoodFor(TwitterUser TwtUser, int TwtDepth, int CurrentTwtDepth) {
		CurrentTwtDepth++; // This adds 1 to CurrentTwtDepth.
		for (TwitterUser TwtFollower : TwtUser.getFollowers()) {
			if (TwtFollower.equals(this)) // If TwtFollower equals this...
				continue; // Continue
			if (TwtNeighborhood.contains(TwtFollower)) // If the Neighborhood contains the TwtFollower themself...
				continue; // Continue
			TwtNeighborhood.add(TwtFollower); // This adds the Follower to the Neighborhood.
			if (CurrentTwtDepth <= TwtDepth) // This the current depth is less than or equal
				getNeighborhoodFor(TwtFollower, TwtDepth, CurrentTwtDepth); // Recurisively calling getNeighborhoodFor
																			// for this follower.
		}
	}

	public int compareTo(TwitterUser o) {
		return Integer.valueOf(ID).compareTo(Integer.valueOf(o.getID())); // This returns the ID of the User and
																			// compares it.

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TwitterUser ClonedUser = new TwitterUser(this.ID); // This creates a TwitterUser named ClonedUser based on the
															// User ID.
		ClonedUser.TwitterFollowers = this.getFollowers(); // Sets the Twitter Followers to a copied version of the
															// Followers list for the User.
		return ClonedUser; // Returns the ClonedUser.
	}

	@Override
	public String toString() {
		return "ID #" + ID;
	}

}
