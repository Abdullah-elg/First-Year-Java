public class Counter {
    // initializes the set of cards and the starter card
    private PowerSet<Card> cardps;
    private Card starter;

    // assigns the set of cards with given hand of cards and assigns the starter card with the given starter card
    public Counter(Card[] hand, Card starter) {
        cardps = new PowerSet(hand);
        this.starter = starter;
    }

    // counts the total points that the set of cards has using helper methods and returns the total points
    public int countPoints() {
        int count = 0;
        count += getPairs() + getRuns() + getFifteen() + getFlush() + getKnobs();
        return count;
    }

    // checks if the hand of cards has any pairs and adds points if it does and then returns the total of points produced by pairs
    private int getPairs() { 
        int count = 0;
        for(int i = 0; i < cardps.getLength(); i++) {
            if(cardps.getSet(i).getLength() == 2) {
                if(cardps.getSet(i).getElement(0).getLabel() == cardps.getSet(i).getElement(1).getLabel()) { 
                    count += 2;
                }
            }
        }
        return count;
    }

    // checks if there is a run using isRun() method and calculates the points gained from run and returns the total points
    private int getRuns() {
        int count5 = 0;
        int count4 = 0;
        int count3 = 0;
        for(int i = 0; i < cardps.getLength(); i ++) {
            Set<Card> cards = cardps.getSet(i);
            if(isRun(cards) == true) {
                // checks the length of the run and adds points based the run length
                if(cards.getLength() > 4) {
                    count5 += 5;
                } else if(cards.getLength() > 3) {
                    count4 += 4;
                } else if(cards.getLength() > 2) {
                    count3 += 3;
                }
            }
        }
        return (count5 > 0) ? count5 : (count4 > 0) ? count4 : (count3 > 0) ? count3 : 0;
    }

    // checks if the sum of any of the powersets is 15 and gives points for however many sets have a sum of 15 and returns the total points
    private int getFifteen() { 
        int count = 0;
        for(int i = 0; i < cardps.getLength(); i ++) {
            int total = 0;
            for(int j = 0; j < cardps.getSet(i).getLength(); j++) {
                total += cardps.getSet(i).getElement(j).getFifteenRank();
            }
            if(total == 15) {
                count += 2;
            }
        }
        return count;
    }

    // checks if the card hand has all the same suit and gives returns points based on if they all have the same suit
    private int getFlush() { 
        for(int i = 0; i < cardps.getLength(); i++) {
            if(cardps.getSet(i).getLength() > 4) {
                for(int j = 1; j < cardps.getSet(i).getLength() - 1; j++) {
                    if(cardps.getSet(i).getElement(j).getSuit() != cardps.getSet(i).getElement(j + 1).getSuit()) {
                        return 0;
                    }
                }
                // if the starter card has the same suit as the rest of the hand gain an extra point
                if(cardps.getSet(i).getElement(1).getSuit() == starter.getSuit()) {
                    return 5;
                } else {
                    return 4;
                }
            }
        }
        return 0;
    }

    // checks if there is a jack in the hand and then checks if the jack's suit is the same as any of the other cards and returns 1 if it does
    private int getKnobs() {
        for(int i = 0; i < cardps.getLength(); i++) {
            if(cardps.getSet(i).getLength() == 1) {
                if(cardps.getSet(i).getElement(0).getLabel().equals("J")) {
                    if(cardps.getSet(i).getElement(0).getSuit() == starter.getSuit()) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		
		if (n <= 2) return false; // Run must be at least 3 in length.
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}
}
