/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.cas.client.util;

import org.jasig.cas.client.validation.Assertion;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Places the assertion in a ThreadLocal such that other resources can access it that do not have access to the web tier session.
 *
 * @author Scott Battaglia
 * @version $Revision: 7288 $ $Date: 2016-01-21 16:33:54 +0800 (Thu, 21 Jan 2016) $
 * @since 3.0
 */
public final class AssertionThreadLocalFilter implements Filter {

    public void init(final FilterConfig filterConfig) throws ServletException {
        // nothing to do here
    }

    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession(false);
        final Assertion assertion = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));

        try {
            AssertionHolder.setAssertion(assertion);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            AssertionHolder.clear();
        }
    }

    public void destroy() {
        // nothing to do
    }
}
