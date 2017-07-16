package org.dmytro.kovalyk.exception;

import org.springframework.boot.ExitCodeGenerator;

/** Helpful exception to return non successful exit code */
public class ExceptionWithExitCode extends RuntimeException implements ExitCodeGenerator {
    private static final long serialVersionUID = -7105312080174946661L;

    private final int _exitCode;

    public ExceptionWithExitCode() {
        _exitCode = 0;
    }

    public ExceptionWithExitCode(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace, int exitCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        _exitCode = exitCode;
    }

    public ExceptionWithExitCode(String message, Throwable cause, int exitCode) {
        super(message, cause);
        _exitCode = exitCode;
    }

    public ExceptionWithExitCode(String message, int exitCode) {
        super(message);
        _exitCode = exitCode;
    }

    public ExceptionWithExitCode(Throwable cause, int exitCode) {
        super(cause);
        _exitCode = exitCode;
    }

    /* @see org.springframework.boot.ExitCodeGenerator#getExitCode() */
    @Override
    public int getExitCode() {
        return _exitCode;
    }

}
