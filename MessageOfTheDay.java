import java.net.URL;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * Class to request the message of the day
 *
 * @author George Manning
 * @version 1.0
 */
public class MessageOfTheDay {


    /**
     * Gets the message of the day from the website.
     *
     * @return The message of the day.
     */
    public String getMessage() {
        String puzzleString = "";
        try {
            puzzleString = getPuzzleString();
        } catch (IOException e) {
            System.out.println("Website not found");
            System.exit(0);
        }


        String message = "";
        try {
            message = requestMessage(decryptPuzzleString(puzzleString));
        } catch (IOException e) {
            System.out.println("Website not found");
            System.exit(0);
        }
        return message;
    }

    //Gets the puzzle String from the website
    private String getPuzzleString() throws IOException {
        URL puzzleURL = new URL("http://cswebcat.swan.ac.uk/puzzle");

        HttpURLConnection conn = (HttpURLConnection) puzzleURL.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String puzzleString = br.readLine();

        br.close();

        return puzzleString;
    }

    //Decrypts the puzzle string.
    private String decryptPuzzleString(String encryptedText) {
        String decryptedText = "";
        for (int i = 0; i < encryptedText.length(); i++) {
            if (i % 2 == 0) { //code to shift charcter forwards so the 1st, 3rd etc characters
                //turns character into ots ascii value to allow for shift
                int ascii = (int) encryptedText.charAt(i);
                ascii++;
                //deals with overflow
                if (ascii > 90) {
                    ascii -= 26;
                }
                decryptedText += (char) ascii;
            } else { //code for the 2nd 4th etc characters
                int ascii = (int) encryptedText.charAt(i);
                ascii--;
                if (ascii < 65) {
                    ascii += 26;
                }
                decryptedText += (char) ascii;
            }
        }

        return decryptedText;
    }

    //Gets the actual message of the day
    private String requestMessage(String key) throws IOException {

        URL requestURL = new URL("http://cswebcat.swan.ac.uk/message?solution=" + key);
        HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        conn.setRequestMethod("GET");

        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String message = br.readLine();

        br.close();

        return message;
    }

}
