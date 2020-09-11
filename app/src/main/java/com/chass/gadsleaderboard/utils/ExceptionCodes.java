package com.chass.gadsleaderboard.utils;

public class ExceptionCodes {
  //region Network Response Codes

  /**
   * Apart from http status code returned from http request, a request could also throw other exception
   * We extend these codes to indicate the exception thrown and handle appropriately in calling classes.
   * According to RFC7231: The status-code element is a three-digit integer code ...
   * To not collide with a code already in use or reduce likelihood of being used in future, We will use one-digit
   * codes and only use two-digit if one-digits are exhausted.
   */

  /**
   * Exception did not match any of the known possible exceptions
   */
  public static final int EXCEPTION = 0;

  /**
   * Well if access token has not been obtained yet, We throw exception
   */
  public static final int TOKEN_INVALID = 1;

  /**
   * Exception did not match any known subclass of IOException that connections throw.
   * Some methods also throw a IOException directly
   * Known methods: getPermission(), getResponseCode (), getResponseMessage (),
   */
  public static final int IO_EXCEPTION = 2;

  /**
   * MalformedURLException was thrown
   */
  public static final int MALFORMED_URL_EXCEPTION = 3;

  /**
   * Known Methods: setChunkedStreamingMode(), setFixedLengthStreamingMode(), getCipherSuite(), getLocalCertificates(),
   * getLocalPrincipal(), getPeerPrincipal(), getServerCertificates()
   */
  public static final int ILLEGAL_STATE_EXCEPTION = 4;

  /**
   * Known Methods: setFixedLengthStreamingMode(),setDefaultHostnameVerifier(), setDefaultSSLSocketFactory(), setHostnameVerifier(),
   * setSSLSocketFactory()
   */
  public static final int ILLEGAL_ARGUMENT_EXCEPTION = 5;

  /**
   * Known Methods: setFollowRedirects(), setRequestMethod(),setDefaultHostnameVerifier(), setDefaultSSLSocketFactory(),
   * setSSLSocketFactory()
   */
  public static final int SECURITY_EXCEPTION = 6;

  /**
   * Known Methods: setRequestMethod(),
   */
  public static final int PROTOCOL_EXCEPTION = 7;

  /**
   * Known Methods: getPeerPrincipal(),getServerCertificates()
   */
  public static final int SSL_PEER_UN_VERIFIED_EXCEPTION = 8;

  //SSLPeerUnverifiedException

  //endregion
}
