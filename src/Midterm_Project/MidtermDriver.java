//Made by Zachary Carroll-Ashley
//CIS 2217.NR4 Java Software Development 2
//Finished on 4/30/2022
package Midterm_Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MidtermDriver {
	private static final String USER_DATA_FILE = "social_network.edgelist";

	public static void main(String[] args) {
		Tweeter Twitter = new Tweeter();
		System.out.println("Reading " + USER_DATA_FILE + ", Please wait....");
		final ArrayList<TwitterUser> users = Twitter.readDataFile(USER_DATA_FILE); // Sets Users from Data File.
		System.out.println("Total users read: " + users.size()); // Prints out the number of how many users there are.
		System.out.println("Sorting User list...");
		Collections.sort(users, (a, b) -> { // Sorts depending on the Followers size and ID and Followers size of the
											// User.
			int TwtDiff = b.getFollowing().size() - a.getFollowing().size(); // Gets following size.
			if (TwtDiff == 0) // If it's a tie...
				TwtDiff = b.getFollowers().size() - a.getFollowers().size(); // Then sorts by Followers.
			if (TwtDiff == 0) // If that's a tie again...
				TwtDiff = a.getID() - b.getID(); // Sorts by ID.
			return TwtDiff; // Returns sort value.
		});
		Twitter.setUsers(users); // Sets Users to users.

		// Testing getFollowing
		Random random = new Random();
		for (int x = 0; x < 10; x++) { // // Counts 1-10.
			int randomUserIndex = random.nextInt(Twitter.getUserSize()); // Gets a random User index.
			TwitterUser randomUser = Twitter.GetUserByIndex(randomUserIndex); // Sets randomUser from a random User's
																				// index.
			System.out.println("Followers of " + randomUser); // Prints out the random User's follower amount.
			for (TwitterUser Follower : Twitter.getFollowing(randomUser)) { // Loops through get Following.
				System.out.println("\t" + Follower); // Prints out the follower.
			}
			System.out.println("Total Followers: " + Twitter.getFollowing(randomUser).size()); // Gets the total
																								// following of the
																								// Random User's
																								// followers.
		}
		// Testing getByPopularity
		System.out.println("Top 10 Most Popular Users:");
		for (int x = 0; x < 10; x++) { // Counts 1-10.
			TwitterUser user = Twitter.GetByPopularity(x); // Gets the User's by popularity.
			System.out.println("\t" + user + ": " + user.getFollowing().size()); // Prints the most popular user.

		}
		int randomUserIndex = random.nextInt(Twitter.getUserSize()); // Gets a random User index.
		System.out.println("User of random popularity #" + randomUserIndex + ":"); // Prints a completely random User
																					// and shows their popularity.
		TwitterUser randomUser = Twitter.GetByPopularity(randomUserIndex); // Sets random User from a random User's
																			// index.
		System.out.println("\t" + randomUser + ": " + randomUser.getFollowing().size()); // Prints out the follower.
	}

}