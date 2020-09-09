import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlCollecter {

	int i = 0;
	Scanner sc;
	final static String URL_REGEX = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";

	final static Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	HashSet<String> hs = new HashSet<String>();
	public String UrlToString(URL url) throws IOException {

		try {
			sc = new Scanner(new InputStreamReader(url.openStream()));
		} catch (Exception e) {
			e.getMessage();
		}
		StringBuffer line_join = new StringBuffer();
		String line;
		FileWriter fw = new FileWriter(new File("html.txt"));
		while (sc.hasNext()) {
			line = sc.nextLine();
			line_join.append(line+"\n");
			
		}
		fw.write(line_join.toString());
		fw.write("\n");
		return line_join.toString();
	}

	public HashSet<String> LinkExtracter(String html) {
		i=0;
		StringTokenizer tok = new StringTokenizer(html, " =\"");
		while (tok.hasMoreTokens()) {
			
			if (tok.nextToken().equals("href")) {
				String link = tok.nextToken().toString();
				hs.add(link);
				i++;
			}
		}
		
		return hs;

	}

	public void fileWriter() {
		
		try {
			File file = new File("links.txt");
			FileWriter fw = new FileWriter(file);
			Iterator<String> it = hs.iterator();
			for (int i = 0; i < hs.size(); i++) {
				int c = 0;
				if (it.hasNext()) {
					String ss=it.next();
					if(isURL(ss)) {
						c++;
					fw.append(ss);
					fw.write("\n");}
				}
			}
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static boolean isURL(String url) {

		if (url == null) {
			return false;
		}

		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}

}
