package com.wondersgroup.base.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.acegisecurity.intercept.web.FilterInvocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VirtualFilterChain implements FilterChain {
	Log logger = LogFactory.getLog(VirtualFilterChain.class);

	private FilterInvocation fi;
	private Filter[] additionalFilters;
	private int currentPosition = 0;

	public VirtualFilterChain(FilterInvocation filterInvocation, Filter[] additionalFilters) {
		this.fi = filterInvocation;
		this.additionalFilters = additionalFilters;
	}

	public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		if (currentPosition == additionalFilters.length) {
			if (logger.isDebugEnabled()) {
				logger.debug(fi.getRequestUrl()
						+ " reached end of additional filter chain; proceeding with original chain");
			}

			fi.getChain().doFilter(request, response);
		} else {
			currentPosition++;

			if (logger.isDebugEnabled()) {
				logger.debug(fi.getRequestUrl() + " at position " + currentPosition + " of " + additionalFilters.length
						+ " in additional filter chain; firing Filter: '" + additionalFilters[currentPosition - 1]
						+ "'");
			}

			additionalFilters[currentPosition - 1].doFilter(request, response, this);
		}
	}
}
