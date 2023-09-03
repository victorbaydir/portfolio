package controle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.connectionJDBC;
import model.PessoaVO;
import uteis.Uteis;

@ManagedBean(name = "pessoaControle")
@ViewScoped
public class PessoaControle {

	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private PessoaVO pessoaVO;

	private static Connection connection;
	
	public PessoaControle() {
		connection = connectionJDBC.getConnection();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		if (logradouro == null) {
			logradouro = "";
		}
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		if (bairro == null) {
			bairro = "";
		}
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		if (cidade == null) {
			cidade = "";
		}
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		if (estado == null) {
			estado = "";
		}
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
    public PessoaVO getPessoaVO() {
    	if (pessoaVO == null) {
    		pessoaVO = new PessoaVO();
		}
		return pessoaVO;
	}

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}
	/**
     * Busca um CEP no ViaCEP
     *
     * @param cep
	 * @throws MalformedURLException 
     * @throws br.com.parg.viacep.ViaCEPException caso ocorra algum erro
     */
    public void consultarCEP() throws MalformedURLException{
    	try {
    		String cep = "";
    		StringBuilder jsonCEP = new StringBuilder();
	    	String cepUser = Uteis.obtainOnlyNumbers(getPessoaVO().getCep());
	    	URL url = new URL("https://viacep.com.br/ws/"+cepUser+"/json/");
	    	URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		
			while ((cep = bufferedReader.readLine()) != null) {
				jsonCEP.append(cep);
			}
			
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonCEP.toString());

			
			getPessoaVO().setLogradouro(jsonNode.get("logradouro").asText());
			getPessoaVO().setBairro(jsonNode.get("bairro").asText());
			getPessoaVO().setCidade(jsonNode.get("localidade").asText());
			getPessoaVO().setEstado(jsonNode.get("uf").asText());
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    public void salvarPessoa() {
    	
    }
	
}
