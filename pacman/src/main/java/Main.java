import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Input input = new Input();
        String data = input.getData("C:\\Users\\user\\IdeaProjects\\pacman\\src\\main\\resources\\board1.npy_array.json");

        ObjectMapper objectMapper = new ObjectMapper();
        var board = objectMapper.readValue(data, double[][].class);
        var result = game.findDistance(board);

        for (String s : result) {
            System.out.println(s);
        }

    }
}
