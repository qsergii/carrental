package com.epam.carrental.controllers.filters;

import com.epam.carrental.LanguageBundle;
import com.epam.carrental.dao.DAOFactory;
import com.epam.carrental.dao.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@WebFilter("/*")
public class CommonFilter extends HttpFilter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        setEncoding(request);
        setLanguage(request);
        authorizeUser(request);

        chain.doFilter(request, response);
    }

    private void setEncoding(ServletRequest request) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }
    }

    private void setLanguage(ServletRequest req) {
        String language = null;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String languageFromRequest = request.getParameter("lang");
        if (languageFromRequest != null) {
            language = languageFromRequest;
        } else {
            String languageFromSession = (String) session.getAttribute("language");
            if (languageFromSession != null) {
                language = languageFromSession;
            }
        }
        if (language == null) {
            language = "en";
        }
//        request.setAttribute("language", language);
        session.setAttribute("language", language);
        LanguageBundle.setLanguage(language);

        Config.set(session, Config.FMT_LOCALE, new java.util.Locale(language, language.toUpperCase(Locale.ROOT)));
//        Config.set( session, Config.FMT_LOCALE, new java.util.Locale("en", "US") );
    }

    private void authorizeUser(ServletRequest request) {

        User user = null;

        HttpSession session = ((HttpServletRequest) request).getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            user = DAOFactory.getInstance().getUserDAO().getUserById(userId);
        }

        request.setAttribute("authUser", user);
    }

}
