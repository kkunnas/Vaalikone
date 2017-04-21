package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import persist.Kysymykset;
import persist.Vastaukset;
import java.util.List;
import persist.Ehdokkaat;

public final class tulokset_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Paras ehdokas</title>\r\n");
      out.write("        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div id=\"container\">\r\n");
      out.write("            <h1>Diginide kertoo sinulle, ketä pitää äänestää:</h1>\r\n");
      out.write("            ");

                List<Ehdokkaat> parhaatEhdokkaat = (List<Ehdokkaat>) request.getAttribute("parasEhdokas");
                List<Integer> kayttajanVastaukset = (List<Integer>) request.getAttribute("kayttajanVastaukset");
                List<Vastaukset> parhaanEhdokkaanVastaukset = (List<Vastaukset>) request.getAttribute("parhaanEhdokkaanVastaukset");
                List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");
                Double pisteet = (double) (Integer) request.getAttribute("pisteet");
                Double prosentit = (double) Math.round(pisteet / (3 * 19) * 100);
                Integer jarjestysnumero = (Integer) request.getAttribute("jarjestysnumero");

                if (jarjestysnumero > 0) {
      out.write("\r\n");
      out.write("            <a href=\"Vaalikone?func=haeEhdokas&numero=");
      out.print( jarjestysnumero - 1);
      out.write("\">Edellinen ehdokas</a>&nbsp; \r\n");
      out.write("            ");
 }
                if (jarjestysnumero < 18) {
      out.write("\r\n");
      out.write("            <a href=\"Vaalikone?func=haeEhdokas&numero=");
      out.print( jarjestysnumero + 1);
      out.write("\">Seuraavaksi paras ehdokas</a>\r\n");
      out.write("            ");
 }

                for (Ehdokkaat seParasEhdokas : parhaatEhdokkaat) {
            
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <h2>Numero: ");
      out.print( seParasEhdokas.getEhdokasId());
      out.write("</h2>\r\n");
      out.write("            <h3>Sinulle ");
      out.print( jarjestysnumero+1 );
      out.write(". paras ehdokas</h3>\r\n");
      out.write("            <h3>Yhteensopivuus: ");
      out.print( prosentit);
      out.write("%</h3>\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li><b>Nimi:</b>");
      out.print( seParasEhdokas.getEtunimi());
      out.write(' ');
      out.print( seParasEhdokas.getSukunimi());
      out.write("</li>\r\n");
      out.write("                <li><b>Ikä:</b>");
      out.print( seParasEhdokas.getIkä());
      out.write("</li>\r\n");
      out.write("                <li><b>Kotipaikkakunta:</b>");
      out.print( seParasEhdokas.getKotipaikkakunta());
      out.write("</li>\r\n");
      out.write("                <li><b>Ammatti:</b>");
      out.print( seParasEhdokas.getAmmatti());
      out.write("</li>\r\n");
      out.write("                <li><b>Puolue:</b>");
      out.print( seParasEhdokas.getPuolue());
      out.write("</li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <h2>Miksi haluat eduskuntaan?</h2>\r\n");
      out.write("            <p>");
      out.print( seParasEhdokas.getMiksiEduskuntaan());
      out.write("</p>\r\n");
      out.write("            <h2>Mitä asioita haluat edistää?</h2>\r\n");
      out.write("            <p>");
      out.print( seParasEhdokas.getMitaAsioitaHaluatEdistaa());
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("            ");
 }

                for (int i = 0; i < parhaanEhdokkaanVastaukset.size(); i++) {
            
      out.write("\r\n");
      out.write("            <b>Kysymys ");
      out.print( i + 1);
      out.write(':');
      out.write(' ');
      out.print( kaikkiKysymykset.get(i).getKysymys());
      out.write("</b><br>\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li>Sinun vastaus: ");
      out.print( kayttajanVastaukset.get(i + 1).toString());
      out.write("</li>\r\n");
      out.write("                <li>Ehdokkaan vastaus: ");
      out.print( parhaanEhdokkaanVastaukset.get(i).getVastaus());
      out.write("</li>\r\n");
      out.write("                <li>Ehdokkaan kommentti: ");
      out.print( parhaanEhdokkaanVastaukset.get(i).getKommentti());
      out.write("</li>\r\n");
      out.write("            </ul>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            ");

                }

            
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
