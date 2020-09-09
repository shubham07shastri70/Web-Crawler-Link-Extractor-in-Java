import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		UrlCollecter uc = new UrlCollecter();
		System.out.println("Enter the url here:");
		String address= sc.nextLine();
		URL bitmesra = new URL(address);
		String html = uc.UrlToString(bitmesra);
		HashSet<String> ll = uc.LinkExtracter(html);
	
		
        List<String> L= new ArrayList<String>(ll);
		
		uc.fileWriter();

		for (int i = 1; i < L.size(); i++) {
			
				   String ss= L.get(i);                 //it.next();
				if (UrlCollecter.isURL(ss)) {
					URL secondary = new URL(ss);
					String html1 = uc.UrlToString(secondary);
					uc.LinkExtracter(html1);
					uc.fileWriter();
				}
			
		}
		System.out.println("Crawling Done.. open links.txt file to see the links");
	}

}
