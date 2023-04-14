
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTest2 {

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public static void main(String[] args) {

		try {
			// parsing할 url 지정(API 키 포함해서)
			String url = "http://www.kopis.or.kr/openApi/restful/pblprfr";
			String serviceKey = "ff629d936dc94d33aa9ad5d4166d25c9";
			String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
			String stdate = "20230423"; // 공시대상회사의 고유번호(8자리)
			String eddate = "20230430"; // 사업연도(4자리)
			String rows = "1000";
			String cpage = "1";
			String prfstate = "1";

			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(url + "?service=" + decodeServiceKey + "&stdate=" + stdate + "&eddate=" + eddate + "&rows="
							+ rows + "&cpage=" + cpage + "&prfstate" + prfstate);

			// root tag
			documentInfo.getDocumentElement().normalize();
			System.out.println("Root element :" + documentInfo.getDocumentElement().getNodeName());

			//List만들어서 넣어보기
			ArrayList<Object> play = new ArrayList();
			
			// 파싱할 tag
			NodeList nList = documentInfo.getElementsByTagName("db");
			System.out.println("파싱할 리스트 수 : " + nList.getLength());
			int count = 0;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("######################");
					System.out.println("아뒤  : " + getTagValue("mt20id", eElement));
					System.out.println("名  : " + getTagValue("prfnm", eElement));
					System.out.println("시작 : " + getTagValue("prfpdfrom", eElement));
					System.out.println("끄읕  : " + getTagValue("prfpdto", eElement));
					System.out.println("웨어  : " + getTagValue("fcltynm", eElement));
					System.out.println("포스타  : " + getTagValue("poster", eElement));
					System.out.println("쟁르 : " + getTagValue("genrenm", eElement));
					System.out.println("하는즁?: " + getTagValue("prfstate", eElement));
					count++;
					System.out.println(count);

					play.add(getTagValue("mt20id", eElement));
					play.add(getTagValue("prfnm", eElement));
					play.add(getTagValue("prfpdfrom", eElement));
					play.add(getTagValue("prfpdto", eElement));
					play.add(getTagValue("fcltynm", eElement));
					play.add(getTagValue("poster", eElement));
					play.add(getTagValue("genrenm", eElement));
					play.add(getTagValue("prfstate", eElement));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
