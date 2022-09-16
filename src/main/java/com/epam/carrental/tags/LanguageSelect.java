package com.epam.carrental.tags;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
public class LanguageSelect extends TagSupport{

    public int doStartTag()  {
        JspWriter out = pageContext.getOut();
        try {
            out.print(LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }
}