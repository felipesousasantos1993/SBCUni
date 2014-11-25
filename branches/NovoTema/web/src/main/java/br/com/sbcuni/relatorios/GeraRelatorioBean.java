package br.com.sbcuni.relatorios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GeraRelatorioBean implements Serializable {

	private static final long serialVersionUID = 4288913089814146972L;
	private static final Logger LOGGER = LoggerFactory.getLogger(GeraRelatorioBean.class);

	public static void gerarPDF(List<?> lista, Map vParams, String reportUrl) throws IOException {

		// Gera nome do relatorio
		String nomeArquivo = reportUrl.substring(reportUrl.lastIndexOf('/') + 1, reportUrl.lastIndexOf('.'));

		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		String reportUrlReal = context.getExternalContext().getRealPath(reportUrl);

		try {

			final JasperPrint jasperPrint = JasperFillManager.fillReport(reportUrlReal, vParams, (lista == null || lista.isEmpty()) ? new JREmptyDataSource() : new ExtDataSource(lista));
			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			final byte[] pdf = output.toByteArray();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\"");
			response.setContentLength(pdf.length);

			final ServletOutputStream ouputStream = response.getOutputStream();

			ouputStream.write(pdf, 0, pdf.length);
			ouputStream.flush();
			ouputStream.close();
			context.responseComplete();
			context.renderResponse();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
