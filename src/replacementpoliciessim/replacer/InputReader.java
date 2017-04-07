/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replacementpoliciessim.replacer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author janoc
 */
public class InputReader {

    public static InputData read() {
        InputData data = new InputData();
        try (BufferedReader r = new BufferedReader(new FileReader("input"))) {
            data.frameSize = Integer.parseInt(r.readLine().trim());
            for (String p : r.readLine().trim().split(",")) {
                data.inputs.add(new Page(p));
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return data;
    }
}
