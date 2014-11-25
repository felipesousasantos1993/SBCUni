package br.com.sbcuni.cep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CepService {

	public static CepServiceVO consultarCep(String cep) {
		String urlString = "http://cep.republicavirtual.com.br/web_cep.php";

		Properties parameters = new Properties();
		parameters.setProperty("cep", cep);
		parameters.setProperty("formato", "xml");

		Iterator i = parameters.keySet().iterator();
		int counter = 0;

		while (i.hasNext()) {
			String name = (String) i.next();
			String value = parameters.getProperty(name);
			urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;

		}

		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Request-Method", "GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.connect();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuffer newData = new StringBuffer();
			String s = "";
			while (null != ((s = br.readLine()))) {
				newData.append(s);
			}
			br.close();

			XStream xstream = new XStream(new DomDriver());
			Annotations.configureAliases(xstream, CepServiceVO.class);
			xstream.alias("webservicecep", CepServiceVO.class);
			return (CepServiceVO) xstream.fromXML(newData.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}