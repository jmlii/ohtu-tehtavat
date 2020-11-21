package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String score = "";
    private final int pointsNeededToWin = 4;
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }
    
    public String getScore() {
        if (player1Score == player2Score) {
            getEqualScores();
        } else if (player1Score >= pointsNeededToWin || player2Score >= pointsNeededToWin) {
            getAdvantageOrWinner();
        } else getSituation();
        return score;
    }

    private String getEqualScores() {
        switch (player1Score) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;
        }
        return score;
    }
    
    private String getAdvantageOrWinner() {
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            score = "Advantage " + player1Name;
        } else if (minusResult == -1) {
            score = "Advantage " + player2Name;
        } else if (minusResult >= 2) {
            score = "Win for " + player1Name;
        } else {
            score = "Win for " + player2Name;
        }
        return score;
    }
    
    private String getSituation() {
        int playerScore = 0;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                playerScore = player1Score;
            } else {
                score += "-";
                playerScore = player2Score;
            }
            switch (playerScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }
    
}
