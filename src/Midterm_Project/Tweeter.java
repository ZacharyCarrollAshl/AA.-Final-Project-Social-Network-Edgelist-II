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

public class Tweeter {
	private ArrayList<TwitterUser> TweetUsers = new ArrayList<TwitterUser>(); // This sets up an ArrayList of
																				// TwitterUsers.

	public TwitterUser GetUserByIndex(int index) {
		return TweetUsers.get(index); // This returns the TwitterUsers by the Index.
	}

	public int getUserSize() {
		return TweetUsers.size(); // This returns the entire TweetUser's ArrayList.
	}

	public TwitterUser getUserByID(int ID) {
		for (int i = 0; i < TweetUsers.size(); i++) {
			TwitterUser TweetUser = TweetUsers.get(i); // This creates TweetUser as a TwitterUser consisting of
														// TweetUsers getting the Index.
			if (TweetUser.getID() == ID) // If TweetUsers ID equals ID...
				return TweetUser; // Return the TweetUser

		}
		return null; // This returns Null if the User doesn't exist.
	}

	public Collection<TwitterUser> getFollowing(TwitterUser user) {
		return user.getFollowing(); // Gets the User's followers AKA who is following them.
	}

	public TwitterUser GetByPopularity(int x) {
		return GetUserByIndex(x); // Grabs the User by their index as the list is sorted by popularity..

	}

	public ArrayList<TwitterUser> getNeighborhood(int ID, int TwtDepth) {
		TwitterUser TwtUser = getUserByID(ID); // This sets a TwtUser consisting of the User's ID.
		return TwtUser.getNeighborhood(TwtDepth); // This returns the Neighborhood for the TwtUser by the TwtDepth.
	}

	public void setUsers(ArrayList<TwitterUser> users) {
		TweetUsers = users;

	}

	public ArrayList<TwitterUser> readDataFile(String fileName) {
		final Map<Integer, TwitterUser> users = new HashMap<>(); // Makes new map of Users.
		File file = new File(fileName); // Creates file to be read.
		try {
			FileReader fileReader = new FileReader(file); // Creates file reader.
			BufferedReader bufferedReader = new BufferedReader(fileReader); // Reads the file.
			List<String> lines = bufferedReader.lines().collect(Collectors.toList()); // Grabs all of the lines from the
																						// file.
			for (String line : lines) { // Loops through lines.
				String[] fields = line.trim().split(" "); // Splits the lines.
				int userId = Integer.parseInt(fields[0]); // Sets the User's ID to 0 field.
				int friendId = Integer.parseInt(fields[1]); // Sets the Friend ID to 1'st field.

				TwitterUser user = users.get(userId); // Tries to get User if they exist.
				if (user == null) { // If User is Null...
					user = new TwitterUser(userId); // Creates the User.
					users.put(userId, user); // Puts the User in the map.
				}

				TwitterUser friend = users.get(friendId); // Tries to get Friend if they exist
				if (friend == null) { // If the Friend is null...
					friend = new TwitterUser(friendId); // Creates the Friend.
					users.put(friendId, friend); // Puts the Friend in the map.
				}
				// Creates a two-way link between User & Friend.
				user.addFollower(friend); // Add the Friend to the User's follower list.
				friend.addFollowing(user); // Adds the User to the Friend's following list.
			}
			bufferedReader.close(); // Closes the reader.
		} catch (FileNotFoundException e) { // Handling errors.
			System.out.print("File not found: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<TwitterUser>(users.values()); // Returns the Users.
	}
}
