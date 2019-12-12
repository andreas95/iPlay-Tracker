package main.java.com.andreas.tracker.iplay.utils.uriSchemeHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Andreas on 7/19/16.
 */
public class MacOSURISchemeHandler implements RealURISchemeHandler {
    @Override
    public void open(URI uri) throws IOException {
       // Runtime.getRuntime().exec(new String[]{"OSX/Applications/Utilities/Terminal.app",uri.toString()});
       String command="open "+uri;
        executeCommand(command);
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    @Override
    public void register(final String protocol, final String handlerApplication) throws IOException {
    }

}
