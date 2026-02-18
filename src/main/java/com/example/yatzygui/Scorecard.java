package com.example.yatzygui;

import java.util.*;

public class Scorecard {

    /** This class builds a scorecard
     * */

    private final String playerName;
    private Map<String, Integer> scorecard;

    // Scorecard constructor
    public Scorecard() {
        playerName = "Player 1";
        this.scorecard = new HashMap<>();
    }

    public Scorecard(String playerName) {
        this.playerName = playerName;
        this.scorecard = new HashMap<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Method that removes from all possible scores the categories that have already been used.
     *
     * @param dice  Integer array containing the dye from diceThrow() method.
     * @return A map containing available scores for a player.
     * */
    public Map<String, Integer> getAvailableScores(Integer[] dice) {
        Map<String, Integer> possibilities = possibleScores(dice);

        Iterator<String> keyIterator = possibilities.keySet().iterator();

        while (keyIterator.hasNext()) {

            String currentKey = keyIterator.next();
            if (scorecard.containsKey(currentKey)) {
                keyIterator.remove();
            }
        }

        return possibilities;

    }

    public boolean addScore(String category, int score) {
        if (scorecard.containsKey(category)) {
            return false;
        }
        scorecard.put(category, score);
        return true;
    }

    public Map<String, Integer> getScorecard() {
        return new HashMap<>(scorecard);
    }


    private int countTurnScore(Integer[] dice) { // Helper method to quickly calculate all dice score.

        int sum = 0;

        for (int i = 0; i < dice.length; i++) {
            sum += dice[i];
        }

        return sum;
    }

    public Map<String, Integer> possibleScores(Integer[] dice) {

        Integer score; // initialize score
        int chanceScore = countTurnScore(dice); // Chance score & full house score -> face value of dice.

        int pairCount = 0; // for two pairs, keep track of how many pairs
        int pairScore = 0; // scoring two pairs

        // Map of scoring possibilities for the current round.
        HashMap<String, Integer> possibilities = new HashMap<>();
        // Map dice from diceThrow() to dye frequency -> <Dye, Frequency>.
        HashMap<Integer, Integer> dyeCount = new HashMap<>();

        // Loop dice[] returned by diceThrow().
        for (int dye : dice) {
            // Add dye to map.
            dyeCount.put(dye, dyeCount.getOrDefault(dye, 0) + 1);
        }

        // Loop map entries and calculate possible scores.
        for (Map.Entry<Integer, Integer> entry : dyeCount.entrySet()) {

            if (entry.getValue() == 5) { // Yatzy!
                score = 50;
                possibilities.put("Yatzy", score);
                possibilities.put("Chance", chanceScore);
            }
            // Big Straight.
            if (dyeCount.containsKey(6) && dyeCount.containsKey(5) && dyeCount.containsKey(4) && dyeCount.containsKey(3) && dyeCount.containsKey(2)) {
                score = 20;
                possibilities.put("Big Straight", score);
                possibilities.put("Chance", chanceScore);
            }
            // Small Straight.
            if (dyeCount.containsKey(5) && dyeCount.containsKey(4) && dyeCount.containsKey(3) && countTurnScore(dice) == 15) {
                score = 15;
                possibilities.put("Small Straight", score);
                possibilities.put("Chance", chanceScore);
            }
            // Full House.
            if (dyeCount.containsValue(3) && dyeCount.containsValue(2)) {
                score = chanceScore;
                possibilities.put("Full House", score);
                possibilities.put("Chance", chanceScore);
            }
            // Four of a kind.
            if (entry.getValue() == 4) {
                score = entry.getKey() * entry.getValue();
                possibilities.put("Four of a kind", score);
                possibilities.put(entry.getKey().toString(), score);
                possibilities.put("Chance",chanceScore);
            }
            // Three of a kind.
            if (entry.getValue() == 3) {
                score = entry.getKey() * entry.getValue();
                possibilities.put("Three of a kind", score);
                possibilities.put(entry.getKey().toString(), score);
                possibilities.put("Chance", chanceScore);
            }
            // Pair.
            if (entry.getValue() == 2) {
                pairCount++;
                pairScore += entry.getKey() * entry.getValue();
                score = entry.getKey() * entry.getValue();
                possibilities.put("Pair", score);
                possibilities.put(entry.getKey().toString(), score);
                possibilities.put("Chance", chanceScore);
            }
            // two pairs
            if (pairCount == 2 && pairScore != 0) {
                score = pairScore;
                possibilities.put("Two Pair", score);
                possibilities.put("Chance", chanceScore);
            }
            // one
            if (entry.getValue() == 1) {
                score = entry.getKey();
                possibilities.put(entry.getKey().toString(), score);
                possibilities.put("Chance", chanceScore);
            }
        }
        return possibilities;
    }
}

