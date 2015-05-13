/**
 * 
 */
package net.iripple.imonggo;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.codec.binary.Base64;

import com.jcabi.http.Request;
import com.jcabi.http.Response;
import com.jcabi.http.request.JdkRequest;
/**
 * @author ChibeePatag
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Request request = new JdkRequest("https://chibeepatag.c3.imonggo.com/api/products.xml")
				.through(RetryWire.class) // Auto retry for crappy connections. :D
				.header(HttpHeaders.AUTHORIZATION, String.format("Basic %s",  Base64.encodeBase64String("6790e03a1d088c82b08c34f0f011c311d9bf55e7:X".getBytes())))						     
				.header(HttpHeaders.ACCEPT, "application/xml")
				.header(HttpHeaders.CONTENT_TYPE, "application/xml");
						
			Response response = request.fetch().as(XmlResponse.class)
					  .assertXPath("/data/name") // AssertionError if this XPath is absent
					  .xml().toString();
			int status = response.status();
			System.out.println(status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
