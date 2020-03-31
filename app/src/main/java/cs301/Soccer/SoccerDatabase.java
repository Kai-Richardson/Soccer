package cs301.Soccer;

import android.util.Log;

import cs301.Soccer.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 */
public class SoccerDatabase implements SoccerDB {

	private HashMap<String, SoccerPlayer> name_to_obj_map = new HashMap<String, SoccerPlayer>();

	/**
	 * add a player
	 *
	 * @see SoccerDB#addPlayer(String, String, int, String)
	 */
	@Override
	public boolean addPlayer(String firstName, String lastName,
	                         int uniformNumber, String teamName) {

	    String our_key = nameToKey(firstName, lastName);

	    if (name_to_obj_map.containsKey(our_key)) return false; //Already present

        SoccerPlayer new_player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        name_to_obj_map.put(our_key, new_player);
        return true;
	}

	// Turns a first and last name into a hash map key
	private String nameToKey(String firstName, String lastName) {
	    return firstName + "##" + lastName;
    }

	/**
	 * remove a player
	 *
	 * @see SoccerDB#removePlayer(String, String)
	 */
	@Override
	public boolean removePlayer(String firstName, String lastName) {

		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.remove(playerKey);
			return true;
		}
		return false;
	}

	/**
	 * look up a player
	 *
	 * @see SoccerDB#getPlayer(String, String)
	 */
	@Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			return name_to_obj_map.get(playerKey);
		}
		return null;
	}

	/**
	 * increment a player's goals
	 *
	 * @see SoccerDB#bumpGoals(String, String)
	 */
	@Override
	public boolean bumpGoals(String firstName, String lastName) {

		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpGoals();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's assists
	 *
	 * @see SoccerDB#bumpAssists(String, String)
	 */
	@Override
	public boolean bumpAssists(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpAssists();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's shots
	 *
	 * @see SoccerDB#bumpShots(String, String)
	 */
	@Override
	public boolean bumpShots(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpShots();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's saves
	 *
	 * @see SoccerDB#bumpSaves(String, String)
	 */
	@Override
	public boolean bumpSaves(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpSaves();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's fouls
	 *
	 * @see SoccerDB#bumpFouls(String, String)
	 */
	@Override
	public boolean bumpFouls(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpFouls();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's yellow cards
	 *
	 * @see SoccerDB#bumpYellowCards(String, String)
	 */
	@Override
	public boolean bumpYellowCards(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpYellowCards();
			return true;
		}

		return false;
	}

	/**
	 * increment a player's red cards
	 *
	 * @see SoccerDB#bumpRedCards(String, String)
	 */
	@Override
	public boolean bumpRedCards(String firstName, String lastName) {
		String playerKey = nameToKey(firstName, lastName);

		if (name_to_obj_map.containsKey(playerKey)) {
			name_to_obj_map.get(playerKey).bumpRedCards();
			return true;
		}

		return false;
	}

	/**
	 * tells the number of players on a given team
	 *
	 * @see SoccerDB#numPlayers(String)
	 */
	@Override
	// report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

		if (teamName == null) return name_to_obj_map.size();

		AtomicInteger num_of_players = new AtomicInteger();

		name_to_obj_map.forEach((k, v) -> {
			if (v.getTeamName().equals(teamName)) {
				num_of_players.getAndIncrement();
			}
		});

		return num_of_players.intValue();
	}

	/**
	 * gives the nth player on a the given team
	 *
	 * @see SoccerDB#playerNum(int, String)
	 */
	// get the nTH player
	@Override
	public SoccerPlayer playerNum(int idx, String teamName) {

		int ourIndex = -1;
		int ourTeamIndex = -1;

		for (SoccerPlayer player : name_to_obj_map.values()) {
			ourIndex++;
			if (teamName == null) {
				ourTeamIndex++;
				if (ourTeamIndex == idx) return player;
			}
			if (idx == ourIndex) {
				assert teamName != null;
				if (teamName.equals(player.getTeamName())) return player;
			}
		}

		return null;
	}

	/**
	 * reads database data from a file
	 *
	 * @see SoccerDB#readData(java.io.File)
	 */
	// read data from file
	@Override
	public boolean readData(File file) {
		return file.exists();
	}

	/**
	 * write database data to a file
	 *
	 * @see SoccerDB#writeData(java.io.File)
	 */
	// write data to file
	@Override
	public boolean writeData(File file) {

		try {
			PrintWriter writer = new PrintWriter(file);

			for (String name : name_to_obj_map.keySet())
				writer.println(logString(name_to_obj_map.get(name).serialize()));

			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * helper method that logcat-logs a string, and then returns the string.
	 *
	 * @param s the string to log
	 * @return the string s, unchanged
	 */
	private String logString(String s) {
		Log.i("write string", s);
		return s;
	}

	/**
	 * returns the list of team names in the database
	 *
	 * @see cs301.Soccer.SoccerDB#getTeams()
	 */
	// return list of teams
	@Override
	public HashSet<String> getTeams() {
		return new HashSet<String>();
	}

}
