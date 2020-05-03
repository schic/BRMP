package com.wondersgroup.base.login.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;


public class CASFilter extends AbstractConfigurationFilter {

	/** Represents the constant for where the assertion will be located in memory. */
	public static final String CONST_CAS_ASSERTION = "_const_cas_assertion_";

	/** Instance of commons logging for logging purposes. */
	protected final Log log = LogFactory.getLog(getClass());

	/** Defines the parameter to look for for the artifact. */
	private String artifactParameterName = "ticket";

	/** Defines the parameter to look for for the service. */
	private String serviceParameterName = "service";

	/** Sets where response.encodeUrl should be called on service urls when constructed. */
	private boolean encodeServiceUrl = true;

	/**
	 * The name of the server.  Should be in the following format: {protocol}:{hostName}:{port}.
	 * Standard ports can be excluded. */
	private String serverName;

	/** The exact url of the service. */
	private String service;

	/**
	 * The URL to the CAS Server login.
	 */
	private String casServerLoginUrl;


	/**
	 * Whether to send the renew request or not.
	 */
	private boolean renew = false;

	/**
	 * Whether to send the gateway request or not.
	 */
	private boolean gateway = false;

	private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

	/**
	 * The URL to the CAS Server logout.
	 */
	private String casServerLogoutUrl;



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (!isIgnoreInitConfiguration()) {
			initInternal(filterConfig);
		}
		init();

	}

	public CASFilter(String serverName, String service, String casServerLoginUrl, String casServerLogoutUrl) {
		this.serverName = serverName;
		this.service = service;
		this.casServerLoginUrl = casServerLoginUrl;
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public CASFilter() {

	}

	private void initInternal(FilterConfig filterConfig) throws ServletException {

		/*setServerName(getPropertyFromInitParams(filterConfig, "serverName", null));
		log.trace("Loading serverName property: " + this.serverName);
		setService(getPropertyFromInitParams(filterConfig, "service", null));
		log.trace("Loading service property: " + this.service);
		setArtifactParameterName(getPropertyFromInitParams(filterConfig, "artifactParameterName", "ticket"));
		log.trace("Loading artifact parameter name property: " + this.artifactParameterName);
		setServiceParameterName(getPropertyFromInitParams(filterConfig, "serviceParameterName", "service"));
		log.trace("Loading serviceParameterName property: " + this.serviceParameterName);
		setEncodeServiceUrl(parseBoolean(getPropertyFromInitParams(filterConfig, "encodeServiceUrl", "true")));
		log.trace("Loading encodeServiceUrl property: " + this.encodeServiceUrl);

		setCasServerLoginUrl(getPropertyFromInitParams(filterConfig, "casServerLoginUrl", null));
		log.trace("Loaded CasServerLoginUrl parameter: " + this.casServerLoginUrl);
		setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
		log.trace("Loaded renew parameter: " + this.renew);
		setGateway(parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")));
		log.trace("Loaded gateway parameter: " + this.gateway);*/

		final String gatewayStorageClass = getPropertyFromInitParams(filterConfig, "gatewayStorageClass", null);

		if (gatewayStorageClass != null) {
			try {
				this.gatewayStorage = (GatewayResolver) Class.forName(gatewayStorageClass).newInstance();
			} catch (final Exception e) {
				log.error(e, e);
				throw new ServletException(e);
			}
		}

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		String realCasPath = FilterChainProxy.getRealCasPath((HttpServletRequest) servletRequest)[0];


		String requestUri = httpRequest.getRequestURI();
		@SuppressWarnings("unused")
		String rootPath = httpRequest.getContextPath();

		this.serverName = httpRequest.getRequestURL().toString().replace(requestUri, "");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final HttpSession session = request.getSession(false);
		final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;
		String new_client_ip = FilterChainProxy.getRemoteHost(request);
		String CLIENT_IP = (String) request.getSession(true).getAttribute("CLIENT_IP");
		final String serviceUrl = constructServiceUrl(request, response);

		if (new_client_ip.equals(CLIENT_IP)) {
			if (assertion != null) {
				filterChain.doFilter(request, response);
				return;
			}

			final String ticket = CommonUtils.safeGetParameter(request, getArtifactParameterName());
			final boolean wasGatewayed = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);

			if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
				filterChain.doFilter(request, response);
				return;
			}
		} else {
			Cookie[] cookies = request.getCookies();//CASTGC,JSESSIONID
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if ("CASTGC".equals(cookie.getName())) {
						cookie.setValue(null);
					}
					if ("JSESSIONID".equals(cookie.getName())) {
						cookie.setValue(null);
					}
					if ("ticket".equals(cookie.getName())) {
						cookie.setValue(null);
					}
				}
			}
			System.out.print(cookies);
		}


		final String modifiedServiceUrl;

		log.debug("no ticket and no assertion found");
		if (this.gateway) {
			log.debug("setting gateway attribute in session");
			modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
		} else {
			modifiedServiceUrl = serviceUrl;
		}

		if (log.isDebugEnabled()) {
			log.debug("Constructed service url: " + modifiedServiceUrl);
		}


		String urlToRedirectTo = CommonUtils.constructRedirectUrl(realCasPath + this.casServerLoginUrl,
				getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);

		if (log.isDebugEnabled()) {
			log.debug("redirecting to \"" + urlToRedirectTo + "\"");
		}
		request.getSession(true).setAttribute("CLIENT_IP", new_client_ip);
		
		if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("xmlhttprequest"))
	    {
			response.setStatus(401);
			response.setContentType("application/Json");
	        response.getWriter().write("{\"sessionlost\": \"true\"}");
	    }
	    else
	    {
	    	urlToRedirectTo = cleanupUrl(urlToRedirectTo);
			response.sendRedirect(urlToRedirectTo);
	    }
	}

	@Override
	public void destroy() {
		// nothing to do

	}

	public void init() {
		CommonUtils.assertNotNull(this.artifactParameterName, "artifactParameterName cannot be null.");
		CommonUtils.assertNotNull(this.serviceParameterName, "serviceParameterName cannot be null.");
		CommonUtils.assertTrue(CommonUtils.isNotEmpty(this.serverName) || CommonUtils.isNotEmpty(this.service),
				"serverName or service must be set.");
		CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
	}

	protected final String constructServiceUrl(final HttpServletRequest request, final HttpServletResponse response) {
		return CommonUtils.constructServiceUrl(request, response, this.service, this.serverName,
				this.artifactParameterName, this.encodeServiceUrl);
	}

	public final void setServerName(final String serverName) {
		this.serverName = serverName;
	}

	public final void setService(final String service) {
		this.service = service;
	}

	public final void setArtifactParameterName(final String artifactParameterName) {
		this.artifactParameterName = artifactParameterName;
	}

	public final void setServiceParameterName(final String serviceParameterName) {
		this.serviceParameterName = serviceParameterName;
	}

	public final void setEncodeServiceUrl(final boolean encodeServiceUrl) {
		this.encodeServiceUrl = encodeServiceUrl;
	}

	public final String getArtifactParameterName() {
		return this.artifactParameterName;
	}

	public final String getServiceParameterName() {
		return this.serviceParameterName;
	}

	public final void setRenew(final boolean renew) {
		this.renew = renew;
	}

	public final void setGateway(final boolean gateway) {
		this.gateway = gateway;
	}

	public final void setCasServerLoginUrl(final String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
		this.gatewayStorage = gatewayStorage;
	}

	public String getCasServerLogoutUrl() {
		return casServerLogoutUrl;
	}

	public void setCasServerLogoutUrl(String casServerLogoutUrl) {
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public static final String cleanupUrl(final String url) {
	
		if (url == null) {

			return null;

		}
	
		final int jsessionPosition = url.indexOf("%3Bjsessionid");

		if (jsessionPosition == -1 ) {

			return url;

		}

		final int questionMarkPosition = url.indexOf("%3F");

		if (questionMarkPosition < jsessionPosition) {

			return url.substring(0, jsessionPosition);

		}else {
			String newUrl = "";
			newUrl += url.substring(0, jsessionPosition);
			newUrl += url.substring(questionMarkPosition);
			return newUrl;
		}
		
	}
}
