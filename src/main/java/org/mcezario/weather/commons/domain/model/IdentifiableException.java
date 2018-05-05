package org.mcezario.weather.commons.domain.model;

import java.io.Serializable;

public abstract class IdentifiableException extends RuntimeException {

	private static final String MESSAGE_FORMAT = "[%s] %s";

	private final Id id;
	private final String message;

	protected IdentifiableException(final Id id) {
		super(id.toString());
		this.id = id;
		this.message = null;
	}

	protected IdentifiableException(final Id id, final String message) {
		super(newMessage(id, message));
		this.id = id;
		this.message = message;
	}

	protected IdentifiableException(final Id id, final Throwable cause) {
		super(id.toString(), cause);
		this.id = id;
		this.message = null;
	}

	protected IdentifiableException(final Id id, final String message, final Throwable cause) {
		super(newMessage(id, message), cause);
		this.id = id;
		this.message = message;
	}

	private static String newMessage(final Id id, final String message) {
		return String.format(MESSAGE_FORMAT, id, message);
	}

	public String id() {
		return id.toString();
	}

	public int httpStatusCode() {
		return id.httpStatusCode();
	}

	public String message() {
		return message;
	}

	public static final class Id implements Serializable {

		private static final long serialVersionUID = -2167231257089167282L;

		private static final String REPRESENTATION_FORMAT = "%s-%04d";

		private final String prefix;
		private final int code;
		private final int httpStatusCode;

		private Id(final String prefix, final int code, final int httpStatusCode) {
			this.prefix = prefix;
			this.code = code;
			this.httpStatusCode = httpStatusCode;
		}

		public Id(final String prefix, final int code) {
			this(prefix, code, 0);
		}

		@Override
		public String toString() {
			return String.format(REPRESENTATION_FORMAT, prefix, code);
		}

		public Id withHttpStatusCode(final int httpStatusCode) {
			return new Id(prefix, code, httpStatusCode);
		}

		public String prefix() {
			return prefix;
		}

		public int code() {
			return code;
		}

		public int httpStatusCode() {
			return httpStatusCode;
		}

	}

}
