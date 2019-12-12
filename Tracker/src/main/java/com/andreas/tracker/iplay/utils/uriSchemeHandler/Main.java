package main.java.com.andreas.tracker.iplay.utils.uriSchemeHandler;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Andreas on 7/19/16.
 */
public class Main {

    public static void main(String[] args) throws URISyntaxException {
        String magnetLink = "magnet:?xt=urn:foobarbaz";
        URI magnetLinkUri = new URI(magnetLink);
        URISchemeHandler uriSchemeHandler = new URISchemeHandler();
        try {
            uriSchemeHandler.open(magnetLinkUri);
        } catch (CouldNotOpenUriSchemeHandler couldNotOpenUriSchemeHandler) {
            couldNotOpenUriSchemeHandler.printStackTrace();
        }
    }
}
