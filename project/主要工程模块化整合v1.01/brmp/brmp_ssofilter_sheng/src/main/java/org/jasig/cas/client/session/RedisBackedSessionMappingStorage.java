package org.jasig.cas.client.session;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;

@SuppressWarnings("deprecation")
public final class RedisBackedSessionMappingStorage implements SessionMappingStorage {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unused")
	private ServletContext servletContext;
	
	private RedisTemplate<String, String> ID_TO_SESSION_KEY_MAPPING;
	
	private SessionRepository<ExpiringSession> MANAGED_SESSIONS;
	
	private String getSessionIdByMappingId(String mappingId){
		return ID_TO_SESSION_KEY_MAPPING.opsForValue().get(mappingId);
	}

	@Override
	public HttpSession removeSessionByMappingId(String mappingId) {
		final ExpiringSession expiringSession = MANAGED_SESSIONS.getSession(getSessionIdByMappingId(mappingId));

        if (expiringSession != null) {
        	removeBySessionById(expiringSession.getId());
        }
        
        if (expiringSession != null) {
            String sessionID = expiringSession.getId();

            if (log.isDebugEnabled()) {
                log.debug ("Invalidating session [" + sessionID + "] for token [" + mappingId + "]");
            }
            try {
            	MANAGED_SESSIONS.delete(sessionID);
            } catch (final IllegalStateException e) {
                log.debug("Error invalidating session.", e);
            }
        }
        
        return null;
	}

	@Override
	public void removeBySessionById(String sessionId) {
		if (log.isDebugEnabled()) {
            log.debug("Attempting to remove Session=[" + sessionId + "]");
        }

        final String key = (String) ID_TO_SESSION_KEY_MAPPING.opsForValue().get(sessionId);

        if (log.isDebugEnabled()) {
            if (key != null) {
                log.debug("Found mapping for session.  Session Removed.");
            } else {
                log.debug("No mapping for session found.  Ignoring.");
            }
        }
        //MANAGED_SESSIONS.delete(sessionId);
        ID_TO_SESSION_KEY_MAPPING.delete(sessionId);
        if (key != null) {
        	ID_TO_SESSION_KEY_MAPPING.delete(key);
        }
	}

	@Override
	public void addSessionById(String mappingId, HttpSession session) {
		ID_TO_SESSION_KEY_MAPPING.opsForValue().set(session.getId(), mappingId);
		ID_TO_SESSION_KEY_MAPPING.opsForValue().set(mappingId, session.getId());
	}
	
	
	@SuppressWarnings("unused")
	private static final HttpSessionContext NOOP_SESSION_CONTEXT = new HttpSessionContext() {
		public HttpSession getSession(String sessionId) {
			return null;
		}

		public Enumeration<String> getIds() {
			return EMPTY_ENUMERATION;
		}
	};
	private static final Enumeration<String> EMPTY_ENUMERATION = new Enumeration<String>() {
		public boolean hasMoreElements() {
			return false;
		}

		public String nextElement() {
			throw new NoSuchElementException("a");
		}
	};
	
	
	public RedisTemplate<String, String> getID_TO_SESSION_KEY_MAPPING() {
		return ID_TO_SESSION_KEY_MAPPING;
	}

	public void setID_TO_SESSION_KEY_MAPPING(
			RedisTemplate<String, String> iD_TO_SESSION_KEY_MAPPING) {
		ID_TO_SESSION_KEY_MAPPING = iD_TO_SESSION_KEY_MAPPING;
	}

	public SessionRepository<ExpiringSession> getMANAGED_SESSIONS() {
		return MANAGED_SESSIONS;
	}

	public void setMANAGED_SESSIONS(
			SessionRepository<ExpiringSession> mANAGED_SESSIONS) {
		MANAGED_SESSIONS = mANAGED_SESSIONS;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
