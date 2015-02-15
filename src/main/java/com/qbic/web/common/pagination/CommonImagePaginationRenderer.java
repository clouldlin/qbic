/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qbic.web.common.pagination;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class CommonImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

    public CommonImagePaginationRenderer() {

    }

    public void initVariables(){

		String strWebDir    = servletContext.getContextPath()+"/images/common/btn";

		firstPageLabel 		= "<a href=\"#\" class=\"pre_end\" onclick=\"{0}({1}); return false;\" onkeypress=\"{0}({1}); return false;\"><img src=\""+strWebDir+"/btn_larrow.gif\" alt=\"첫페이지\" border=\"0\"/></a>";
        previousPageLabel 	= "<a href=\"#\" class=\"pre\" onclick=\"{0}({1}); return false;\" onkeypress=\"{0}({1}); return false;\"><img src=\""+strWebDir+"/btn_llarrow.gif\" alt=\"이전페이지\" border=\"0\"/></a>";
        currentPageLabel 	= "<strong>{0}</strong>";
        otherPageLabel 		= "<span onclick=\"{0}({1}); return false;\" onkeypress=\"{0}({1}); return false;\" class=\"cursor\"><a href=\"#\" onclick=\"return false;\">{2}</a></span>";
        nextPageLabel 		= "<a href=\"#\" class=\"next\" onclick=\"{0}({1}); return false;\" onkeypress=\"{0}({1}); return false;\"><img src=\""+strWebDir+"/btn_rrarrow.gif\" alt=\"다음페이지\" border=\"0\"/></a>";
        lastPageLabel 		= "<a href=\"#\" class=\"next_end\" onclick=\"{0}({1}); return false;\" onkeypress=\"{0}({1}); return false;\"><img src=\""+strWebDir+"/btn_rarrow.gif\" alt=\"끝페이지\" border=\"0\"/></a>";

	}

    @Override
	public void setServletContext(ServletContext servletContext) {
    	this.servletContext = servletContext;
    	   initVariables();
    }
}
