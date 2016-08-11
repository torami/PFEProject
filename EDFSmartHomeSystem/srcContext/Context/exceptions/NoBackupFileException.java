package Context.exceptions;

@SuppressWarnings("serial")
public final class NoBackupFileException extends Exception {
	public NoBackupFileException(String msg) {
		super(msg);
	}
}
