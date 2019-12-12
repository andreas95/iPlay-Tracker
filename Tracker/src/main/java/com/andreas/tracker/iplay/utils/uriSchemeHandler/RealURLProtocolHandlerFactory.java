package main.java.com.andreas.tracker.iplay.utils.uriSchemeHandler;

public class RealURLProtocolHandlerFactory {

	public static RealURISchemeHandler produce(final String osName) {
		final String osLowerCase = osName.toLowerCase();

		if(osLowerCase.contains("linux")){
			return new LinuxURISchemeHandler();
		}

		if(osLowerCase.contains("windows")){
			return new WindowsURISchemeHandler();
		}

		if (osLowerCase.contains("mac")) {
			return new MacOSURISchemeHandler();
		}

		throw new RuntimeException("This OS not supported");
	}

}
