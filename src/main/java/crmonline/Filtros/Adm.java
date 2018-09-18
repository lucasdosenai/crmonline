package crmonline.Filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmonline.MBean.LoginMB;

public class Adm implements Filter{
	
	private LoginMB login;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filtro adiministrador iniciado");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Encontreou um adiministrador com filtro!");
		login = (LoginMB) ((HttpServletRequest) request).getSession().getAttribute("loginMB");
		if(login == null || login.getUsuario().equals("")) {
			String diretorio = ((HttpServletRequest)request).getContextPath();
			((HttpServletRequest)request).getSession().setAttribute("msg", "Acesso Negado");
			((HttpServletResponse)response).sendRedirect(diretorio + "");
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
