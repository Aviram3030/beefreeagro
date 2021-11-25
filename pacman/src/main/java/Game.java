import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<String> findDistance(double[][] board) {
        Pacman pacman = findPacman(board);
        List<Ghost> ghosts = findGhosts(board);
        return FindDistance(board, pacman, ghosts);
    }

    public List<Ghost> findGhosts(double[][] board) {
        List<Ghost> ghosts = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2.0) {
                    ghosts.add(new Ghost(i, j));
                }
            }
        }

        return ghosts;
    }

    public Pacman findPacman(double[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 3.0) {
                    return new Pacman(i, j);
                }
            }
        }

        throw new IllegalArgumentException("Pacman not found");
    }

    private List<String> FindDistance(double[][] board, Pacman pacman, List<Ghost> ghosts) {
        List<String> results = new ArrayList<>();
        for (Ghost ghost : ghosts) {
            int[][] distances = calculateDistances(board, ghost.getX(), ghost.getY());
            int val = distances[pacman.getX()][pacman.getY()];
            if (val == -1) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("(").append(ghost.getX()).append(",");
            sb.append(ghost.getY()).append("),");
            sb.append(val);
            sb.append("]");
            results.add(sb.toString());
        }
        return results;
    }

    private int[][] calculateDistances(double[][] board, int x, int y) {
        int[][] distances = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                distances[i][j] = -1;
            }
        }

        distances[x][y] = 0;
        calculateDistances(board, distances, x, y, 0);

        return distances;
    }

    private void calculateDistances(double[][] board, int[][] distances, int x, int y, int val) {
        int colLength = distances.length;
        int rowLength = distances[0].length;

        if (board[x][y] == 1.0) {
            return;
        }
        if (distances[x][y] == -1 || distances[x][y] > val) {
            distances[x][y] = val;
        }

        int newVal = distances[x][y] + 1;
        if (x + 1 < colLength && (newVal < distances[x + 1][y] || distances[x + 1][y] == -1)) {
            calculateDistances(board, distances, x + 1, y, newVal);
        }
        if (x - 1 >= 0 && (newVal < distances[x - 1][y] || distances[x - 1][y] == -1)) {
            calculateDistances(board, distances, x - 1, y, newVal);
        }
        if (y + 1 < rowLength && (newVal < distances[x][y + 1] || distances[x][y + 1] == -1)) {
            calculateDistances(board, distances, x, y + 1, newVal);
        }
        if (y - 1 >= 0 && (newVal < distances[x][y - 1] || distances[x][y - 1] == -1)) {
            calculateDistances(board, distances, x, y - 1, newVal);
        }

    }
}
