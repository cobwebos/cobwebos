package com.cobwebos.aaa.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	private Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	private static final HttpClientUtils client = new HttpClientUtils();

	private HttpClientUtils() {

	}

	public synchronized static HttpClientUtils getClientInstance() {
		return client;
	}

	public String doGet(String url, String input) {
		String response = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		log.info("getRequestLine:{}", httpGet.getRequestLine());
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		InputStream instrem = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			log.info("getStatusLine:{}", httpResponse.getStatusLine());
			int status = httpResponse.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					instrem = httpEntity.getContent();
					if (instrem != null) {
						String entity = inputStreamToString(instrem);
						log.info("httpEntity:{}", entity);
					}

					response = EntityUtils.toString(httpEntity, "UTF-8");
					log.info("httpEntity:{}", response);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}

		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				instrem.close();
				httpResponse.close();
				httpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public String doPost(String url, String input) {
		String response = null;
		InputStream instrem = null;
		StringEntity intputEntity = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		HttpPost httpPost = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();			
			httpPost = new HttpPost(url);			
			intputEntity = new StringEntity(input, ContentType.create("text/plain", "UTF-8"));
			httpPost.setEntity(intputEntity);			
			httpResponse = httpClient.execute(httpPost);
			log.info("getRequestLine:{}", httpPost.getRequestLine());
			log.info("getStatusLine:{}", httpResponse.getStatusLine());
			int status = httpResponse.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					instrem = httpEntity.getContent();
					if (instrem != null) {
						String entity = inputStreamToString(instrem);
						log.info("httpEntity:{}", entity);
					}

					response = EntityUtils.toString(httpEntity, "UTF-8");
					log.info("httpEntity:{}", response);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}

		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				instrem.close();
				httpResponse.close();
				httpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public String doPut(String url, String input) {
		String response = null;
		InputStream instrem = null;
		StringEntity intputEntity = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		HttpPut httpPut = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();			
			httpPut = new HttpPut(url);			
			intputEntity = new StringEntity(input, ContentType.create("text/plain", "UTF-8"));
			httpPut.setEntity(intputEntity);
			httpResponse = httpClient.execute(httpPut);
			log.info("getRequestLine:{}", httpPut.getRequestLine());
			log.info("getStatusLine:{}", httpResponse.getStatusLine());
			int status = httpResponse.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					instrem = httpEntity.getContent();
					if (instrem != null) {
						String entity = inputStreamToString(instrem);
						log.info("httpEntity:{}", entity);
					}

					response = EntityUtils.toString(httpEntity, "UTF-8");
					log.info("httpEntity:{}", response);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}

		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				instrem.close();
				httpResponse.close();
				httpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public String doPatch(String url, String input) {
		String response = null;
		InputStream instrem = null;
		StringEntity intputEntity = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		HttpPatch httpPatch = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();			
			httpPatch = new HttpPatch(url);
			
			intputEntity = new StringEntity(input, ContentType.create("text/plain", "UTF-8"));
			httpPatch.setEntity(intputEntity);
			httpResponse = httpClient.execute(httpPatch);
			log.info("getRequestLine:{}", httpPatch.getRequestLine());
			log.info("getStatusLine:{}", httpResponse.getStatusLine());
			int status = httpResponse.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					instrem = httpEntity.getContent();
					if (instrem != null) {
						String entity = inputStreamToString(instrem);
						log.info("httpEntity:{}", entity);
					}

					response = EntityUtils.toString(httpEntity, "UTF-8");
					log.info("httpEntity:{}", response);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}

		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				instrem.close();
				httpResponse.close();
				httpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public String doDelete(String url, String input) {
		String response = null;
		InputStream instrem = null;
		StringEntity intputEntity = null;
		CloseableHttpResponse httpResponse = null;
		HttpEntity httpEntity = null;
		HttpDelete httpDelete = null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();			
			httpDelete = new HttpDelete(url);
			
			intputEntity = new StringEntity(input, ContentType.create("text/plain", "UTF-8"));
//			httpDelete.setEntity(intputEntity);
			httpResponse = httpClient.execute(httpDelete);
			log.info("getRequestLine:{}", httpDelete.getRequestLine());
			log.info("getStatusLine:{}", httpResponse.getStatusLine());
			int status = httpResponse.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					instrem = httpEntity.getContent();
					if (instrem != null) {
						String entity = inputStreamToString(instrem);
						log.info("httpEntity:{}", entity);
					}

					response = EntityUtils.toString(httpEntity, "UTF-8");
					log.info("httpEntity:{}", response);
				}
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}

		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				instrem.close();
				httpResponse.close();
				httpClient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public String inputStreamToString(InputStream instrem) {
		BufferedReader in = new BufferedReader(new InputStreamReader(instrem));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return buffer.toString();
	}

	
}
