package uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.PessoaVO;

public class Uteis {

	public static String obtainOnlyNumbers(String input) {
		Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        
        StringBuilder result = new StringBuilder();
        
        while (matcher.find()) {
            result.append(matcher.group());
        }
        
        return result.toString();
	}
	
	public static void searchCEP(PessoaVO pessoa) throws IOException {}
	
	
}
